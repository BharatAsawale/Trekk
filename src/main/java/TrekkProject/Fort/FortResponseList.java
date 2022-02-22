package TrekkProject.Fort;

import lombok.Data;

@Data
public class FortResponseList {
    private int id;
    private String fortName;
    private String img;

    public String setImg(String[] img) {
        return img[0];
    }
}
