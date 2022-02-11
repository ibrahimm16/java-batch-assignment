package com.ibrahim.springtest.athlete;

import com.ibrahim.springtest.team.Team;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "athlete")
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "athletes")
    private List<Team> teams = new ArrayList<>();

    public Athlete() {
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Athlete athlete = (Athlete) o;
        return Objects.equals(id, athlete.id) && Objects.equals(name, athlete.name) && Objects.equals(teams, athlete.teams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, teams);
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
