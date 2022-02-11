package com.ibrahim.springtest.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/teams")
    public ResponseEntity<List<TeamResponseDTO>> getAllTeams() {
        return new ResponseEntity<>(teamService.getAllTeams(), HttpStatus.OK);
    }

    @GetMapping("teams/{id}")
    public ResponseEntity<TeamResponseDTO> getTeam(@PathVariable Long id) {
        return new ResponseEntity<>(teamService.getTeam(id), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/teams")
    public void addTeam(@RequestBody Team team) {
        teamService.addTeam(team);
    }

    @Transactional
    @PutMapping("/teams/{id}")
    public void updateTeam(@PathVariable Long id, @RequestBody Team team) {
        teamService.updateTeam(id, team);
    }

    @Transactional
    @DeleteMapping("/teams/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }
}
