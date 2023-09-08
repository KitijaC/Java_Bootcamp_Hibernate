package owner;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor()
@NoArgsConstructor()
@Data()
@Entity(name = "owners")
public class Owner {

    @Id @GeneratedValue
    private Long id;
    @Column(name = "ownerName") // name to use for the column in database
    private String name;
    private int age;
    private Timestamp createdAt;
    private Timestamp lastUpdated;
}
