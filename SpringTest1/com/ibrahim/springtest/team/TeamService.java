package com.ibrahim.springtest.team;

import com.ibrahim.springtest.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public List<TeamResponseDTO> getAllTeams() {
        List<TeamResponseDTO> responseList = new ArrayList<>();
        teamRepository.findAll().forEach((t) -> {
            responseList.add(new TeamResponseDTO(t.getName()));
        });
        return responseList;
    }

    public TeamResponseDTO getTeam(Long id) {
        if (teamRepository.findById(id).isEmpty())
            throw new NotFoundException();
        return new TeamResponseDTO(teamRepository.findById(id).get().getName());
    }

    public void addTeam(Team team) {
        teamRepository.save(team);
    }

    public void updateTeam(Long id, Team team) {
        if (teamRepository.findById(id).isPresent()) {
            Team loadedTeam = teamRepository.findById(id).get();
            loadedTeam.setName(team.getName());
            loadedTeam.setTeam_athletes(team.getTeam_athletes());
            teamRepository.save(loadedTeam);
        } else teamRepository.save(team);
    }

    public void deleteTeam(Long id) {
        if (teamRepository.findById(id).isEmpty())
            throw new NotFoundException();
        teamRepository.deleteById(id);
    }
}
