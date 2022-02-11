package com.ibrahim.springtest.team;

import com.ibrahim.springtest.athlete.Athlete;
import com.ibrahim.springtest.athlete.AthleteRepository;
import com.ibrahim.springtest.athlete.AthleteResponseDTO;
import com.ibrahim.springtest.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private AthleteRepository athleteRepository;

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
        Team team = teamRepository.findById(id).get();
        return new TeamResponseDTO(team.getName());
    }

    public List<AthleteResponseDTO> getAthletes(Long id) {
        if (teamRepository.findById(id).isEmpty())
            throw new NotFoundException();
        Team team = teamRepository.findById(id).get();
        List<AthleteResponseDTO> responseList = new ArrayList<>();
        team.getAthletes().forEach((a) -> responseList.add(new AthleteResponseDTO(a.getName())));
        return responseList;
    }

    public void addTeam(Team team) {
        teamRepository.save(team);
    }

    public void addAthlete(Long id, Long athleteId) {
        if (teamRepository.findById(id).isEmpty() || athleteRepository.findById(athleteId).isEmpty())
            throw new NotFoundException();
        Team team = teamRepository.findById(id).get();
        Athlete athlete = athleteRepository.findById(athleteId).get();
        team.addAthlete(athlete);
        athlete.addTeam(team);
        teamRepository.save(team);
        athleteRepository.save(athlete);
    }

    public void updateTeam(Long id, Team team) {
        if (teamRepository.findById(id).isPresent()) {
            Team loadedTeam = teamRepository.findById(id).get();
            loadedTeam.setName(team.getName());
            loadedTeam.setAthletes(team.getAthletes());
            teamRepository.save(loadedTeam);
        } else teamRepository.save(team);
    }

    public void deleteTeam(Long id) {
        if (teamRepository.findById(id).isEmpty())
            throw new NotFoundException();
        teamRepository.deleteById(id);
    }
}
