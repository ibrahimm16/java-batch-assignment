package hibernate.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "team_athlete")
@NoArgsConstructor
@AllArgsConstructor
public class Team_Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "athlete_id")
    private Athlete athlete;

    @Override
    public String toString() {
        return "Team_Athlete{" +
                "id=" + id +
                ", team=" + team +
                ", athlete=" + athlete +
                '}';
    }
}
