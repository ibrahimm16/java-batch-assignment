package com.ibrahim.springtest.athlete;

import com.ibrahim.springtest.team_athlete.Team_Athlete;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "athlete")
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "id")
    private List<Team_Athlete> team_athletes = new ArrayList<>();

    public Athlete() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Athlete athlete = (Athlete) o;
        return Objects.equals(id, athlete.id) && Objects.equals(name, athlete.name) && Objects.equals(team_athletes, athlete.team_athletes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, team_athletes);
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", team_athletes=" + team_athletes +
                '}';
    }
}
