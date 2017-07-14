package entity;

/**
 * Created by ddkatona on 2017.07.14..
 */
public class Route {

    private String id;
    private String shortName;

    public Route(String i, String sN) {
        id = i;
        shortName = sN;
    }

    public String getId() {
        return id;
    }

    public String getShortName() {
        return shortName;
    }

    public void printInfo() {
        System.out.println("Route(" + id + "): " + shortName);
    }

}
