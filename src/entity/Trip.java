package entity;

/**
 * Created by ddkatona on 2017.07.14..
 */
public class Trip {

    private String id;
    private Route route;

    public Trip(String i, Route r) {
        id = i;
        route = r;
    }

    public String getId() {
        return id;
    }

    public Route getRoute() {
        return route;
    }

    public void printInfo() {
        System.out.print("Trip(" + id + "): "); route.printInfo();
    }

}
