package com.cricks.cricks.service.player;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cricks.cricks.entity.Player;
import com.cricks.cricks.repository.PlayerRepo;
import com.cricks.cricks.util.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerService {
  private final PlayerRepo playerRepo;

  public ResponseEntity<Response<String>> createPlayer(Player player){
    Response<String> response = new Response<String>(); 

    if(playerRepo.isPlayerAlreadyExist(player.getPhone()).isPresent()){
      return response.sendErrorResponse("Player with this phone number already exist" , 400).sendResponseEntity();
    }
    playerRepo.save(player);
    return response.sendSuccessResponse("Player created successfully" , 200).sendResponseEntity();
    
  }

}
