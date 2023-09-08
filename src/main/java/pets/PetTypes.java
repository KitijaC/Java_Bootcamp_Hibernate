package pets;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "petTypes")
public class PetTypes {
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
