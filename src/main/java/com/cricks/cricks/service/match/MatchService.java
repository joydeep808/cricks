package com.cricks.cricks.service.match;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cricks.cricks.dto.match.TeamsFromMatchDto;
import com.cricks.cricks.dto.match.UpdateMatchDetailsDto;
import com.cricks.cricks.entity.*;
import com.cricks.cricks.entity.Match.MatchStatus;
import com.cricks.cricks.entity.Series.SeriesStatus;
import com.cricks.cricks.exception.thrown_exception.match.*;
import com.cricks.cricks.exception.thrown_exception.series.*;
import com.cricks.cricks.mapper.match.GetMatchInfoMapper;
import com.cricks.cricks.repository.*;
import com.cricks.cricks.util.Response;

import lombok.*;

@Service
@RequiredArgsConstructor
public class MatchService {

  private final MatchRepo matchRepo;
  private final SeriesRepo seriesRepo;

  
  public ResponseEntity<Response<Match>> createMatch(Match match) throws Exception {
    // Before creating a match, first check if the series for the given match exists in the database.
    // If not, throw an exception of SeriesNotFound.
    Series series = seriesRepo.findById(match.getSeriesId())
        .orElseThrow(() -> new SeriesNotFound("Series not found with the given id", 404));

    // Next, check if the status of the series is END.
    // If yes, throw an exception of SeriesAlreadyEnd.
    // This is because we are not allowed to create a new match if the series is already ended.
    if (series.getStatus().equals(SeriesStatus.END)) {
      throw new SeriesAlreadyEnd(
          "Series Already end you are not able to create a match. The status of the series is: " + series.getStatus(),
          400);
    }

    // Now, check if the match with the same series id and match number already exists in the database.
    // If yes, throw an exception of MatchAlreadyExist.
    // This is because we are not allowed to create a new match with the same series id and match number.
    Integer isMatchAlreadyExist = matchRepo.isMatchAlreadyExist(match.getSeriesId(), match.getMatchNumber())
        .orElseGet(null);
    if (isMatchAlreadyExist != null)
      throw new MatchAlreadyExist(
          "Match already exist with given series id and match number. The id of the match is: " + isMatchAlreadyExist,
          400);

    // Finally, save the match in the database.
    matchRepo.save(match);

    // Return a successful response with the created match.
    return new Response<Match>().sendSuccessResponse("Match created successfully done!", 201, match)
        .sendResponseEntity();

  }

  
  public ResponseEntity<Response<String>> updateMatchDetails(UpdateMatchDetailsDto updateMatchDetailsDto)
      throws Exception {
  
    // First, attempt to find the match in the database that we want to update.
    // If the match does not exist in the database, throw an exception of MatchNotFound with a status code of 404.
    Match foundMatch = matchRepo.findById(updateMatchDetailsDto.getId().get()).orElseGet(null);
    if (foundMatch == null) {
     
      // If the match does not exist, then throw an exception of MatchNotFound.
      throw new MatchNotFound("Match not found with the given id", 404);
    } else {
     
      // If the match exists, then update the fields of the match based on what is provided in the UpdateMatchDetailsDto.
      // Note that we are using the Optional class to update the fields of the match.
      // This is because the fields of the UpdateMatchDetailsDto may not be present, and we want to avoid a NullPointerException.
      updateMatchDetailsDto.getId().ifPresent(foundMatch::setId);
      updateMatchDetailsDto.getMatchNumber().ifPresent(foundMatch::setMatchNumber);
      // updateMatchDetailsDto.getMatchStatus().ifPresent(foundMatch::setStatus);
      updateMatchDetailsDto.getTeamA().ifPresent(foundMatch::setTeamA);
      updateMatchDetailsDto.getTeamB().ifPresent(foundMatch::setTeamB);
      updateMatchDetailsDto.getVenueId().ifPresent(foundMatch::setVenueId);
      updateMatchDetailsDto.getTeamB().ifPresent(foundMatch::setTeamB);
    }
    
    // Finally, save the match in the database.
    matchRepo.save(foundMatch);
    
    // Return a successful response with a message indicating that the update was successful.
    return new Response<String>().sendSuccessResponse("Update successfully done!", 200).sendResponseEntity();
  }

  public ResponseEntity<Response<TeamsFromMatchDto>> getTeamsFromMatch(Integer id) throws Exception {
    TeamsFromMatchDto foundMatch = matchRepo.getTeamsFromMatch(id).orElseGet(null);
    if (foundMatch == null)
      throw new MatchNotFound("Match not found with the given id", 404);
    return new Response<TeamsFromMatchDto>().sendSuccessResponse("Update successfully done!", 200, foundMatch)
        .sendResponseEntity();

  }



  public ResponseEntity<Response<String>> updateMatchStatus(Integer matchId , String status )throws Exception{
    Match foundMatch = matchRepo.findById(matchId).orElse(null);
    if (foundMatch == null) throw new MatchNotFound("Match not found with this id", 404);
    foundMatch.setStatus(MatchStatus.valueOf(status));
    matchRepo.save(foundMatch);
    return new Response<String>().sendSuccessResponse("Match status update successfully done!", 200 ).sendResponseEntity();
  }

  public ResponseEntity<Response<GetMatchInfoMapper>> getMatchInfo(Integer id) throws Exception{

    GetMatchInfoMapper foundMatch = matchRepo.getMatchInfo(id).orElse(null);
    if (foundMatch == null) throw new MatchNotFound("Match not found with this id", 404);
    return new Response<GetMatchInfoMapper>().sendSuccessResponse("Update successfully done!", 200, foundMatch)
        .sendResponseEntity();
  }
  
  
}
