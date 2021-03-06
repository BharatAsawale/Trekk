package TrekkProject.Fort;

import TrekkProject.city.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.persistence.Id;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fort {
    @Id
    private int id;
    private int cityId;
    @DocumentReference(lazy=true)
    private City city;
    private String fortName;
    private String history;
    private String features;
    private String height;
    private String typeOfFort;
    private String transportFacility;
    private String stayFacility;
    private String nearByPlaces;
    //    marathi fields
    private String fortNameMr;
    private String historyMr;
    private String featuresMr;
    private String heightMr;
    private String typeOfFortMr;
    private String transportFacilityMr;
    private String stayFacilityMr;
    private String nearByPlacesMr;

    private String mapUrl;
    private String img[];
    private Set<String> likes;
}
