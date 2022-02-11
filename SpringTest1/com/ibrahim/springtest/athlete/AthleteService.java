package com.ibrahim.springtest.athlete;

import com.ibrahim.springtest.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

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
        return new AthleteResponseDTO(athleteRepository.findById(id).get().getName());
    }

    public void addAthlete(Athlete athlete) {
        athleteRepository.save(athlete);
    }

    public void updateAthlete(Long id, Athlete athlete) {
        if (athleteRepository.findById(id).isPresent()) {
            Athlete loadedAthlete = athleteRepository.findById(id).get();
            loadedAthlete.setName(athlete.getName());
            loadedAthlete.setTeam_athletes(athlete.getTeam_athletes());
            athleteRepository.save(loadedAthlete);
        } else athleteRepository.save(athlete);
    }

    public void deleteAthlete(Long id) {
        if (athleteRepository.findById(id).isEmpty())
            throw new NotFoundException();
        athleteRepository.deleteById(id);
    }
}
