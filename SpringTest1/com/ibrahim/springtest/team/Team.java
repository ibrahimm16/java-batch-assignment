package com.ibrahim.springtest.team;

import com.ibrahim.springtest.team_athlete.Team_Athlete;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "id")
    private List<Team_Athlete> team_athletes = new ArrayList<>();

    public Team() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) && Objects.equals(name, team.name) && Objects.equals(team_athletes, team.team_athletes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, team_athletes);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", team_athletes=" + team_athletes +
                '}';
    }
}
