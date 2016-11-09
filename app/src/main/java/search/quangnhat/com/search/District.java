package search.quangnhat.com.search;

/**
 * Created by Me on 11/2/2016.
 */

public class District {
    private int id;
    private String District;

    public District(int id, String district) {
        this.id = id;
        District = district;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }
}
