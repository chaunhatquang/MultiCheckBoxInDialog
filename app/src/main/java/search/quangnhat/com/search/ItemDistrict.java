package search.quangnhat.com.search;

/**
 * Created by Me on 11/4/2016.
 */

public class ItemDistrict {
    private String nameDistrict = null;
    private boolean selected = false;

    public ItemDistrict(String nameDistrict, boolean selected) {
        this.nameDistrict = nameDistrict;
        this.selected = selected;
    }

    public String getNameDistrict() {
        return nameDistrict;
    }

    public void setNameDistrict(String nameDistrict) {
        this.nameDistrict = nameDistrict;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
