package database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity(name = "Player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name="points")
    private Double points;

    public Player(String name){
        this.name = name;
        this.points = new Double(0.0);
    }

}
