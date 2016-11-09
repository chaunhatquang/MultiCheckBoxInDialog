package search.quangnhat.com.search;

/**
 * Created by Me on 11/2/2016.
 */

public class City {
    private int id;
    private String name;
    private String nameFirst;

    public City(int id, String name, String nameFirst) {
        this.id = id;
        this.name = name;
        this.nameFirst = nameFirst;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }
}
