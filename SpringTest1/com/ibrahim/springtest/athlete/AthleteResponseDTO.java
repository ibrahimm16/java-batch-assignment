package com.ibrahim.springtest.athlete;

import lombok.Data;

@Data
public class AthleteResponseDTO {

    private String name;

    public AthleteResponseDTO(String name) {
        this.name = name;
    }
}
