package com.cricks.cricks.controller;

import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cricks.cricks.dto.match.UpdateMatchDetailsDto;
import com.cricks.cricks.entity.Match;
import com.cricks.cricks.mapper.match.GetMatchInfoMapper;
import com.cricks.cricks.service.match.MatchService;
import com.cricks.cricks.util.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/match")
public class MatchController {

  private final MatchService matchService;

  @Transactional
  @PostMapping("/create")
  public ResponseEntity<Response<Match>> createMatch(@RequestBody Match match) throws Exception{
    return matchService.createMatch(match);
  }

  @Transactional
  @PutMapping("/update")
  public ResponseEntity<Response<String>> updateMatchDetails(@RequestBody UpdateMatchDetailsDto match) throws Exception{
    return matchService.updateMatchDetails(match);
  }

  @GetMapping("/get")
  public ResponseEntity<Response<GetMatchInfoMapper>> getMatchInfo(@RequestParam("matchId") Integer id) throws Exception{
    return matchService.getMatchInfo(id);
  }
}