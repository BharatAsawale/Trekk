package TrekkProject.Fort;

import lombok.Data;

@Data
public class FortResponse {
    private int id;
    private String fortName;
    private String history;
    private String features;
    private String height;
    private String typeOfFort;
    private String transportFacility;
    private String stayFacility;
    private String mapUrl;
    private String img[];
}
