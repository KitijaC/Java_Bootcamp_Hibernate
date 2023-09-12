package pets;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import owner.Owner;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "pets")
public class Pet {
    @Id
    // You can choose how it generates sand assign id using the sstrategy
    // AUTO, SEQUENCE and TABLE
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue
    private Long id;
    private String petName;
    private int age;
    private float weight;
    @OneToOne
    private Owner owner;
    @OneToOne
    private PetTypes petType;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
