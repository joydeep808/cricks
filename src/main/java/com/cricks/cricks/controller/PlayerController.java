package com.cricks.cricks.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cricks.cricks.annotation.rawannotation.AuthenticationAnnotation;
import com.cricks.cricks.annotation.rawannotation.AuthenticationAnnotation.Role;
import com.cricks.cricks.dto.player.PlayerUpdateDto;
import com.cricks.cricks.entity.Player;
import com.cricks.cricks.service.player.PlayerService;
import com.cricks.cricks.util.Response;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/player")
public class PlayerController {

  private final PlayerService playerService;

  @AuthenticationAnnotation(role = Role.ADMIN)
  @PostMapping("/create")
  public ResponseEntity<Response<String>> createPlayer(@RequestBody Player player)throws Exception {
    return playerService.createPlayer(player);
  }
  @PostMapping("/get")
  public ResponseEntity<Response<Player>> getPlayer(@RequestBody Integer id) throws Exception{
    return playerService.getPlayer(id);
  }
  @AuthenticationAnnotation(role = Role.ADMIN)
  @PutMapping("/update")
  public ResponseEntity<Response<String>> updatePlayerDetails(@RequestBody PlayerUpdateDto player) throws Exception{
    return playerService.updatePlayerDetails(player);
  }
  
}