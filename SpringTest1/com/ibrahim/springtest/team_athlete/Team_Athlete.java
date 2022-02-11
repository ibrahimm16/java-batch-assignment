package com.ibrahim.springtest.team_athlete;

import com.ibrahim.springtest.athlete.Athlete;
import com.ibrahim.springtest.team.Team;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "team_athlete")
public class Team_Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "athlete_id")
    private Athlete athlete;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team_Athlete that = (Team_Athlete) o;
        return Objects.equals(id, that.id) && Objects.equals(team, that.team) && Objects.equals(athlete, that.athlete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, team, athlete);
    }

    @Override
    public String toString() {
        return "Team_Athlete{" +
                "id=" + id +
                ", team=" + team +
                ", athlete=" + athlete +
                '}';
    }
}
