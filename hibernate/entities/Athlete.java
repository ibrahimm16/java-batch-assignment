package hibernate.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "athlete")
@NoArgsConstructor
@AllArgsConstructor
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "athlete_id")
    private long id;

    @Column(name = "athlete_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Override
    public String toString() {
        return "Athlete{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", team=" + team +
                '}';
    }
}
