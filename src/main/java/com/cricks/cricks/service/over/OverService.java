package com.cricks.cricks.service.over;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cricks.cricks.dto.over.GetOverDto;
import com.cricks.cricks.entity.*;
import com.cricks.cricks.entity.Match.*;
import com.cricks.cricks.exception.thrown_exception.match.MatchException;
import com.cricks.cricks.exception.thrown_exception.over.*;
import com.cricks.cricks.repository.*;
import com.cricks.cricks.util.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OverService {
  private final OverRepo overRepo;
  private final MatchRepo matchRepo;

  public ResponseEntity<Response<String>> createAnOver(Over over) throws Exception {
    // Check if an over with the same match ID and over number already exists
    // for the given match and team
    Over foundOver = overRepo.isOverAlreadyExist(over.getMatchId(), over.getOverNumber()).orElseGet(null);

    // If the existing over belongs to the same team, throw an exception
    if (foundOver.getTeamId().equals(over.getTeamId())) {
      throw new OverAlreadyExist("Over already exist with given match id and team id", 400);
    }

    // Fetch the match details using the match ID from the over
    Match foundMatch = matchRepo.findById(over.getMatchId()).orElseGet(null);

    // Ensure that the match is in progress before creating the over
    if (!foundMatch.getStatus().equals(MatchStatus.INPROGRESS)) {
      throw new MatchException("Match is in " + foundMatch.getStatus() + " state", 400);
    }

    // Check if the match type is T20 and the over number exceeds the limit of 20
    if (foundMatch.getMatch_type().equals(MatchType.T20) && over.getOverNumber() > 20) {
      throw new OverNotCreated("Over limit should be less than 20", 400);
    }

    // Check if the match type is ODI and the over number exceeds the limit of 50
    if (foundMatch.getMatch_type().equals(MatchType.ODI) && over.getOverNumber() > 50) {
      throw new OverNotCreated("Over limit should be less than 50", 400);
    }

    // Save the over as all conditions are satisfied
    overRepo.save(over);

    // Return a success response indicating the over has been created successfully
    return new Response<String>().sendSuccessResponse("Over created successfully done!", 201).sendResponseEntity();
  }

  public ResponseEntity<Response<Over>> getOver(GetOverDto overDto) throws Exception {
    // Attempt to find the over by match number and over number
    Over over = overRepo.isOverAlreadyExist(overDto.getMatchNumber(), overDto.getOverNumber()).orElseGet(null);

    // If the over does not exist, throw an exception
    if (over == null) {
      throw new OverNotFound("Over not found with match number: " + overDto.getMatchNumber() + " and over number: "
          + overDto.getOverNumber(), 404);
    }

    // Return a successful response with the found over
    return new Response<Over>().sendSuccessResponse("Over found", 200, over).sendResponseEntity();
  }

  

}
