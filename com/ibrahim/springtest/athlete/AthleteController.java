package com.ibrahim.springtest.athlete;

import com.ibrahim.springtest.team.TeamResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    @GetMapping("/athletes")
    public ResponseEntity<List<AthleteResponseDTO>> getAllAthletes() {
        return new ResponseEntity<>(athleteService.getAllAthletes(), HttpStatus.OK);
    }

    @GetMapping("/athletes/{id}")
    public ResponseEntity<AthleteResponseDTO> getAthlete(@PathVariable Long id) {
        return new ResponseEntity<>(athleteService.getAthlete(id), HttpStatus.OK);
    }

    @GetMapping("/athletes/{id}/teams")
    public ResponseEntity<List<TeamResponseDTO>> getTeams(@PathVariable Long id) {
        return new ResponseEntity<>(athleteService.getTeams(id), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/athletes")
    public void addAthlete(@RequestBody Athlete athlete) {
        athleteService.addAthlete(athlete);
    }

    @Transactional
    @PutMapping("/athletes/{id}/{teamId}")
    public void addTeam(@PathVariable Long id, @PathVariable Long teamId) {
        athleteService.addTeam(id, teamId);
    }

    @Transactional
    @PutMapping("/athletes/{id}")
    public void updateAthlete(@PathVariable Long id, @RequestBody Athlete athlete) {
        athleteService.updateAthlete(id, athlete);
    }

    @Transactional
    @DeleteMapping("/athletes/{id}")
    public void deleteAthlete(@PathVariable Long id) {
        athleteService.deleteAthlete(id);
    }
}
