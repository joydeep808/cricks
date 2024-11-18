package com.cricks.cricks.service.batsman;



import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cricks.cricks.entity.BatsmanStats;
import com.cricks.cricks.entity.Match.MatchStatus;
import com.cricks.cricks.exception.thrown_exception.batsman.BatsmanStatsNotFound;
import com.cricks.cricks.exception.thrown_exception.match.*;
import com.cricks.cricks.repository.BatsmanStatsRepo;
import com.cricks.cricks.repository.MatchRepo;
import com.cricks.cricks.util.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BatsmanService {
  
  private final BatsmanStatsRepo batsmanStatsRepo;
  private final MatchRepo matchRepo;

  /**
   * This method creates a new batsman stats in the database.
   * Before creating it, it first checks if the match with the given id is in progress.
   * If it's not in progress, it throws a MatchNotStarted exception.
   * @param batsmanStats The batsman stats to be created.
   * @return A response entity that contains a success response with a message.
   * @throws MatchNotStarted If the match is not in progress.
   */
  public ResponseEntity<Response<String>> createBatsmanStats(BatsmanStats batsmanStats) throws Exception{
    // First, find the match with the given id and get its status.
    MatchStatus foundMatch = matchRepo.getMatchStatus(batsmanStats.getMatchId()).orElse(null);;
    // If the match is not found, throw an exception.
    if (foundMatch == null) {
      throw new MatchNotFound("Match not found with the given id", 404);
    }
    // If the match is not in progress, throw an exception.
    if (!foundMatch.equals(MatchStatus.INPROGRESS)) {
      throw new MatchNotStarted("Match is not in " + foundMatch + " state", 400);
    }
    // Finally, create the batsman stats in the database.
    batsmanStatsRepo.save(batsmanStats);
    // Return a success response with a message.
    return new Response<String>().sendSuccessResponse("Batsman stats created successfully done!", 200).sendResponseEntity();
  }

  

  /**
   * This method updates a batsman stats in the database if it exists.
   * <p>
   * It first tries to find the batsman stats in the database with the given match id and batsman id.
   * If the batsman stats is found, it updates the batsman stats in the database.
   * If the batsman stats is not found, it does nothing and returns true.
   * </p>
   * @param batsmanStats The batsman stats to be updated.
   * @return A boolean indicating whether the batsman stats was updated or not.
   */
  public Boolean updateBatsmanStats(BatsmanStats batsmanStats){
    // Try to find the batsman stats in the database with the given match id and batsman id.
    batsmanStatsRepo.findByMatchIdAndBatsmanId(batsmanStats.getMatchId(),batsmanStats.getBatsmanId())
        // If the batsman stats is found, update it in the database.
        .ifPresent(batsmanStatsRepo::save);
    // Return true whether the batsman stats was updated or not.
    return true;
  }

  /**
   * This method retrieves a batsman stats from the database by match id and batsman id.
   * @param matchId The id of the match.
   * @param batsmanId The id of the batsman.
   * @return A response entity that contains a success response with a message and the batsman stats if found.
   * If the batsman stats is not found, it throws a MatchNotStarted exception.
   */
  public ResponseEntity<Response<BatsmanStats>> getBatsmanStats(Integer matchId, Integer batsmanId) throws Exception{
    // Try to find the batsman stats in the database with the given match id and batsman id.
    BatsmanStats foundBatsmanStats = batsmanStatsRepo.findByMatchIdAndBatsmanId(matchId, batsmanId).orElse(null);
    // If the batsman stats is not found, throw an exception.
    if (foundBatsmanStats == null) {
      throw new MatchNotStarted("Batsman stats not found", 404);
    }
    // If the batsman stats is found, return a success response with a message and the batsman stats.
    return new Response<BatsmanStats>().sendSuccessResponse("Batsman stats found successfully done!", 200, foundBatsmanStats)
        .sendResponseEntity();
  }

  /**
   * This method retrieves a list of batsman stats from the database by match id.
   * <p>
   * It first creates a pageable object with the given page number and the number of elements per page as 11.
   * Then, it tries to find all the batsman stats in the database with the given match id by using the pageable object.
   * If the batsman stats is not found, it throws a MatchNotFound exception.
   * If the batsman stats is found, it returns a success response with a message and the batsman stats.
   * </p>
   * @param matchId The id of the match.
   * @param pageNumber The number of the page to retrieve.
   * @return A response entity that contains a success response with a message and the batsman stats if found.
   * If the batsman stats is not found, it throws a MatchNotFound exception.
   */
  public ResponseEntity<Response<Page<BatsmanStats>>> getBatsmanStatsByMatchId(Integer matchId , Integer pageNumber) throws Exception{
    // Create a pageable object with the given page number and the number of elements per page as 11.
    Pageable pageable = PageRequest.of(pageNumber <= 0 ? 0 : pageNumber - 1, 11 , Sort.by("createdAt"));
    // Try to find all the batsman stats in the database with the given match id by using the pageable object.
    Page<BatsmanStats> allBatsmanStats = batsmanStatsRepo.findAllBatsmanStats(matchId , pageable);
    // If the batsman stats is not found, throw an exception.
    if (allBatsmanStats.isEmpty()) {
        throw new MatchNotFound("Match not found ", 404);
    }
    // If the batsman stats is found, return a success response with a message and the batsman stats.
    return new Response<Page<BatsmanStats>>().sendSuccessResponse("Successfully found", 200 , allBatsmanStats).sendResponseEntity();
  }


  /**
   * This method retrieves a batsman stats from the database by match id and batsman id.
   * @param matchId The id of the match.
   * @param batsmanId The id of the batsman.
   * @return A response entity that contains a success response with a message and the batsman stats if found.
   * If the batsman stats is not found, it throws a BatsmanStatsNotFound exception.
   */
  public ResponseEntity<Response<BatsmanStats>> getBatsmanStatsByMatchIdAndBatsmanId(Integer matchId , Integer batsmanId)throws Exception{
    // First, try to find the batsman stats in the database with the given match id and batsman id.
    BatsmanStats foundBatsmanStats = batsmanStatsRepo.findByMatchIdAndBatsmanId(matchId, batsmanId).orElse(null);
    // If the batsman stats is not found, throw an exception.
    if (foundBatsmanStats == null)
      throw new BatsmanStatsNotFound("Batsman stats not found", 404);
    // If the batsman stats is found, return a success response with a message and the batsman stats.
    return new Response<BatsmanStats>().sendSuccessResponse("Batsman stats found successfully done!", 200, foundBatsmanStats)
        .sendResponseEntity();
  }
   
  /**
   * This method retrieves a page of batsman stats from the database by batsman id.
   * <p>
   * It first creates a pageable object with the given page number and the number of elements per page as 5.
   * The pageable object is sorted in descending order by the created date.
   * Then, it tries to find all the batsman stats in the database with the given batsman id by using the pageable object.
   * If the batsman stats is not found, it throws a BatsmanStatsNotFound exception.
   * If the batsman stats is found, it returns a success response with a message and the batsman stats.
   * </p>
   * @param batsmanId The id of the batsman.
   * @param pageNumber The number of the page to retrieve.
   * @return A response entity that contains a success response with a message and the batsman stats if found.
   * If the batsman stats is not found, it throws a BatsmanStatsNotFound exception.
   */
  public ResponseEntity<Response<Page<BatsmanStats>>> getBatsmanStatsByBatsmanId(Integer batsmanId , Integer pageNumber) throws Exception{
    // Create a pageable object with the given page number and the number of elements per page as 5.
    Pageable pageable = PageRequest.of(pageNumber <= 0 ? 0 : pageNumber - 1, 5 , Sort.by("createdAt").descending());
    // Try to find all the batsman stats in the database with the given batsman id by using the pageable object.
    Page<BatsmanStats> allBatsmanStats = batsmanStatsRepo.findAllBatsmanStatsByBatsmanId(batsmanId , pageable);
    // If the batsman stats is not found, throw an exception.
    if (allBatsmanStats.isEmpty() && allBatsmanStats.getTotalElements() == 0) {
        throw new BatsmanStatsNotFound("Batsman stats not found", 404);
    }
    // If the batsman stats is found, return a success response with a message and the batsman stats.
    return new Response<Page<BatsmanStats>>().sendSuccessResponse("Successfully found", 200 , allBatsmanStats).sendResponseEntity();
    }
}
