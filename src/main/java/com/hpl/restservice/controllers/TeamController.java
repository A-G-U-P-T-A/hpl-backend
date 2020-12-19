package com.hpl.restservice.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hpl.restservice.helpers.TeamHelper;
import com.hpl.restservice.objects.Teams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController @RequestMapping("/api") public class TeamController {

    @Autowired TeamHelper teamHelper;

    @CrossOrigin
    @GetMapping("/teams") public ResponseEntity<Map<String, Object>> getAllTeamPages(
            @RequestParam(required = false, defaultValue = "") String sortElement,
            @RequestParam(required = false, defaultValue = "") String sortOrder,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(defaultValue = "0") int score,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        try {
            return teamHelper.getTeams(sortElement, sortOrder, name, score, page, size);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping("/teambyname") public ResponseEntity<List<Teams>> getTeamsByName(@RequestParam (required = true) String name) {

        try {
            return teamHelper.getTeamsByName(name);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping("/createteam") public ResponseEntity<String> createNewTeam(
            @RequestBody Teams teams) {
        try {
            if(teams==null)
                return new ResponseEntity<>("request is empty", HttpStatus.BAD_REQUEST);
            return teamHelper.createTeam(teams);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PutMapping("/updatematchresults") public ResponseEntity<String> updateMatchResults(@RequestBody List<Teams> teams) {
        try {
            if(teams==null)
                return new ResponseEntity<>("request is empty", HttpStatus.BAD_REQUEST);
            return teamHelper.updateTeams(teams);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
