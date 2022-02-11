package com.ibrahim.springtest.team;

import lombok.Data;

@Data
public class TeamResponseDTO {

    private String name;

    public TeamResponseDTO(String name) {
        this.name = name;
    }
}
