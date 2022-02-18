package TrekkProject.state;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "state")
public class State {
    @Id
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private String stateNameEn;
    @Column(nullable = false)
    private String stateNameMr;
    @Column(nullable = false)
    private String img;
}
