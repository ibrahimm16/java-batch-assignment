package com.ibrahim.springtest.athlete;

import com.ibrahim.springtest.exception.NotFoundException;
import com.ibrahim.springtest.team.Team;
import com.ibrahim.springtest.team.TeamRepository;
import com.ibrahim.springtest.team.TeamResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired private TeamRepository teamRepository;

    public List<AthleteResponseDTO> getAllAthletes() {
        List<AthleteResponseDTO> responseList = new ArrayList<>();
        athleteRepository.findAll().forEach((a) -> {
            responseList.add(new AthleteResponseDTO(a.getName()));
        });
        return responseList;
    }

    public AthleteResponseDTO getAthlete(Long id) {
        if (athleteRepository.findById(id).isEmpty())
            throw new NotFoundException();
        Athlete athlete = athleteRepository.findById(id).get();
        return new AthleteResponseDTO(athlete.getName());
    }

    public List<TeamResponseDTO> getTeams(Long id) {
        if (athleteRepository.findById(id).isEmpty())
            throw new NotFoundException();
        Athlete athlete = athleteRepository.findById(id).get();
        List<TeamResponseDTO> responseList = new ArrayList<>();
        athlete.getTeams().forEach((t) -> responseList.add(new TeamResponseDTO(t.getName())));
        return responseList;
    }

    public void addAthlete(Athlete athlete) {
        athleteRepository.save(athlete);
    }

    public void addTeam(Long id, Long teamId) {
        if (athleteRepository.findById(id).isEmpty() || teamRepository.findById(teamId).isEmpty())
            throw new NotFoundException();
        Athlete athlete = athleteRepository.findById(id).get();
        Team team = teamRepository.findById(teamId).get();
        athlete.addTeam(team);
        team.addAthlete(athlete);
        athleteRepository.save(athlete);
        teamRepository.save(team);
    }

    public void updateAthlete(Long id, Athlete athlete) {
        if (athleteRepository.findById(id).isPresent()) {
            Athlete loadedAthlete = athleteRepository.findById(id).get();
            loadedAthlete.setName(athlete.getName());
            loadedAthlete.setTeams(athlete.getTeams());
            athleteRepository.save(loadedAthlete);
        } else athleteRepository.save(athlete);
    }

    public void deleteAthlete(Long id) {
        if (athleteRepository.findById(id).isEmpty())
            throw new NotFoundException();
        athleteRepository.deleteById(id);
    }
}
