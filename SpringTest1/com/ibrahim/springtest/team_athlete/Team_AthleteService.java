package com.ibrahim.springtest.team_athlete;

import com.ibrahim.springtest.athlete.Athlete;
import com.ibrahim.springtest.athlete.AthleteRepository;
import com.ibrahim.springtest.exception.NotFoundException;
import com.ibrahim.springtest.team.Team;
import com.ibrahim.springtest.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Team_AthleteService {

    @Autowired
    private Team_AthleteRepository team_athleteRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private AthleteRepository athleteRepository;

    public List<Team_AthleteResponseDTO> getAllTeamAthletes() {
        List<Team_AthleteResponseDTO> responseList = new ArrayList<>();
        team_athleteRepository.findAll().forEach((t) -> {
            responseList.add(new Team_AthleteResponseDTO(t.getTeam().getName(), t.getAthlete().getName()));
        });
        return responseList;
    }

    public Team_AthleteResponseDTO getTeamAthlete(Long id) {
        if (team_athleteRepository.findById(id).isEmpty())
            throw new NotFoundException();
        Team_Athlete team_athlete = team_athleteRepository.findById(id).get();
        return new Team_AthleteResponseDTO(team_athlete.getTeam().getName(), team_athlete.getAthlete().getName());
    }

    public void addTeamAthlete(Long teamId, Long athleteId) {
        Team_Athlete team_athlete = new Team_Athlete();
        Team team = teamRepository.findById(teamId).get();
        Athlete athlete = athleteRepository.findById(athleteId).get();
        team_athlete.setTeam(team);
        team_athlete.setAthlete(athlete);
        team_athleteRepository.save(team_athlete);
    }

    public void deleteTeamAthlete(Long id) {
        if (team_athleteRepository.findById(id).isEmpty())
            throw new NotFoundException();
        athleteRepository.deleteById(id);
    }
}
