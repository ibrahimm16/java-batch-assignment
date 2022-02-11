package com.ibrahim.springtest.team_athlete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class Team_AthleteController {

    @Autowired
    private Team_AthleteService team_athleteService;

    @GetMapping("/team_athletes")
    public ResponseEntity<List<Team_AthleteResponseDTO>> getAllTeamAthletes() {
        return new ResponseEntity<>(team_athleteService.getAllTeamAthletes(), HttpStatus.OK);
    }

    @GetMapping("/team_athletes/{id}")
    public ResponseEntity<Team_AthleteResponseDTO> getTeamAthlete(@PathVariable Long id) {
        return new ResponseEntity<>(team_athleteService.getTeamAthlete(id), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/team_athletes/{teamId}/{athleteId}")
    public void addTeamAthlete(@PathVariable long teamId, @PathVariable Long athleteId) {
        team_athleteService.addTeamAthlete(teamId, athleteId);
    }

    @Transactional
    @DeleteMapping("/team_athletes/{id}")
    public void deleteTeamAthlete(@PathVariable Long id) {
        team_athleteService.deleteTeamAthlete(id);
    }
}
