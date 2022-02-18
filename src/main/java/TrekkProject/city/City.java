package TrekkProject.city;

import TrekkProject.state.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "city")
public class City {
    @Id
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private String cityNameEn;
    @Column(nullable = false)
    private String cityNameMr;
    @DocumentReference(lazy=true)
    private State state;
}