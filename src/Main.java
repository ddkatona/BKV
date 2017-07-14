import entity.Route;
import entity.StopTime;
import entity.Trip;
import network.JsonDecoder;
import network.Request;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ddkatona on 2017.07.13..
 */
public class Main {
    public static void main(String[] args) {

        while(true) {
            Request r = new Request();
            String json = r.get("BKK_F01323"); //F01323 Hungaria tram
            JsonDecoder jd = new JsonDecoder();

            ArrayList<Route> routes = jd.getRoutes(json);
            ArrayList<Trip> trips = jd.getTrips(json, routes);
            ArrayList<StopTime> times = jd.getStopTimes(json, trips);

            for (StopTime st : times) {
                st.printLineTimeLeft_mmss();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }
}
