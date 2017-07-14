import network.JsonDecoder;
import network.Request;

import java.util.ArrayList;

/**
 * Created by ddkatona on 2017.07.13..
 */
public class Main {
    public static void main(String[] args) {

        Request r = new Request();
        String json = r.get();
        JsonDecoder jd = new JsonDecoder();

        ArrayList<String> buses = new ArrayList<>();
        buses = jd.routesForStop(json);

        for(String s : buses) {
            System.out.println(s);
        }



        //System.out.println(jd.findInJson(json, "data.entry"));
        jd.arriveForStopAndRoute(json, "990");
    }
}
