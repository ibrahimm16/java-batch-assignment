package com.ibrahim.springtest.team;

import com.ibrahim.springtest.athlete.Athlete;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "team_athlete", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "athlete_id"))
    private List<Athlete> athletes = new ArrayList<>();

    public Team() {}

    public void addAthlete(Athlete athlete) {
        athletes.add(athlete);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) && Objects.equals(name, team.name) && Objects.equals(athletes, team.athletes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, athletes);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
