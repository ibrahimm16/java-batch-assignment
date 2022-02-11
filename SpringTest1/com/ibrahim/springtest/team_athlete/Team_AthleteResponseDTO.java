package com.ibrahim.springtest.team_athlete;

import lombok.Data;

@Data
public class Team_AthleteResponseDTO {

    private String teamName, athleteName;

    public Team_AthleteResponseDTO(String teamName, String athleteName) {
        this.teamName = teamName;
        this.athleteName = athleteName;
    }
}
