package com.cricks.cricks.service.player;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cricks.cricks.entity.Player;
import com.cricks.cricks.exception.thrown_exception.team.TeamNotFound;
import com.cricks.cricks.repository.PlayerRepo;
import com.cricks.cricks.repository.TeamRepo;
import com.cricks.cricks.util.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerService {
  private final PlayerRepo playerRepo;
  private final TeamRepo teamRepo;
  public ResponseEntity<Response<String>> createPlayer(Player player)throws Exception{
    Response<String> response = new Response<String>(); 

    if(playerRepo.isPlayerAlreadyExist(player.getPhone()).isPresent()){
      return response.sendErrorResponse("Player with this phone number already exist" , 400).sendResponseEntity();
    }
    if (!teamRepo.isTeamExist(player.getTeam_id()).isPresent()) {
      throw new TeamNotFound("Team is not found with the given id ", 404);
    }
    playerRepo.save(player);
    return response.sendSuccessResponse("Player created successfully" , 200).sendResponseEntity();
    
  }

}
