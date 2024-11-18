package com.cricks.cricks.service.player;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cricks.cricks.dto.player.PlayerUpdateDto;
import com.cricks.cricks.entity.Player;
import com.cricks.cricks.entity.Player.PlayerRoles;
import com.cricks.cricks.exception.thrown_exception.player.PlayerNotFound;
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
  
  public ResponseEntity<Response<String>> createPlayer(Player player) throws Exception {
    Response<String> response = new Response<String>();

    // Check if a player with the same phone number already exists
    if (playerRepo.isPlayerAlreadyExist(player.getPhone()).isPresent()) {
      // If yes, return a 400 error response
      return response.sendErrorResponse("Player with this phone number already exist", 400)
          .sendResponseEntity();
    }

    // Check if the team with the given team id exists
    if (!teamRepo.isTeamExist(player.getTeamId()).isPresent()) {
      // If not, throw a TeamNotFound exception
      throw new TeamNotFound("Team is not found with the given id ", 404);
    }

    // Save the player to the database
    playerRepo.save(player);

    // Return a 200 success response
    return response.sendSuccessResponse("Player created successfully", 200).sendResponseEntity();

  }


 
  public ResponseEntity<Response<Player>> getPlayer(Integer id)throws Exception{
    // Find a player in the database with the given id
    Player player = playerRepo.findById(id).orElseGet(null);
    
    // If the player is not found, throw a PlayerNotFound exception
    if (player == null) {
      throw new PlayerNotFound("Player not found with given id", 404);
    }
    
    // Return a 200 success response with the player object
    return new Response<Player>().sendSuccessResponse("Player found" , 200 , player).sendResponseEntity();
  }

  public ResponseEntity<Response<String>> updatePlayerDetails(PlayerUpdateDto player) throws Exception {
    // Attempt to find the player in the database using the provided player ID
    // If the player does not exist, throw a PlayerNotFound exception
    Player foundPlayer = playerRepo.findById(player.getId()).orElseGet(null);
    if (foundPlayer == null) throw new PlayerNotFound("Player not found with given id", 404);
    
    // Update the player's name if a new name is provided
    player.getName().ifPresent(foundPlayer::setName);
    
    // Update the player's phone number if a new number is provided
    player.getPhone().ifPresent(foundPlayer::setPhone);
    
    // Update the player's profile URL if a new URL is provided
    player.getProfileUrl().ifPresent(foundPlayer::setProfileUrl);
    
    // If a team ID is provided, check if the team exists in the database
    if (player.getTeamId().isPresent()) {
      // If the team does not exist, throw a TeamNotFound exception
      if (!teamRepo.isTeamExist(player.getTeamId().get()).isPresent()) {
        throw new TeamNotFound("Team is not found with the given id ", 404);
      }
      // Update the player's team ID
      foundPlayer.setTeamId(player.getTeamId().get());
    }
    
    // Update the player's ranking if a new ranking is provided
    player.getRanking().ifPresent(foundPlayer::setRanking);
    
    // Update the player's role if a new role is provided
    if (player.getRole().isPresent()) { 
      foundPlayer.setRole(PlayerRoles.valueOf(player.getRole().get()));
    }
    
    // Save the updated player information to the database
    playerRepo.save(foundPlayer);
    
    // Return a success response indicating the player was updated successfully
    return new Response<String>().sendSuccessResponse("Player updated successfully", 200).sendResponseEntity();
  }





}
