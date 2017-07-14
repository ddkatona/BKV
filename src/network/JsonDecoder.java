/**
 * Created by ddkatona on 7/13/2017.
 */

package network;

import entity.Route;
import entity.StopTime;
import entity.Trip;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.*;

public class JsonDecoder {


    public String diveInJson(String json, String levels) {

        String[] s = levels.split("\\.");
        String level = "";

        try {
            JSONParser parser = new JSONParser();


            JSONObject obj = (JSONObject) parser.parse(json);
            level = obj.get(s[0]).toString();

            for (int i = 1; i < s.length; i++) {
                obj = (JSONObject) parser.parse(level);
                level = obj.get(s[i]).toString();
            }

        } catch(Exception ex) {

        } finally {
            return level;
        }

    }

    public String findInArrayInJson(String json, String attr, String value) {

        JSONParser parser = new JSONParser();


        try {
            JSONObject obj = (JSONObject) parser.parse(json);
            Object[] array = obj.values().toArray();

            for(int i = 0; i < array.length; i++) {
                JSONObject obj2 = (JSONObject)array[i];
                String atb = obj2.get(attr).toString();
                if(attr.equals(value)) {
                    return obj2.toString();
                }
            }

        } catch (Exception e) {
            System.out.println("No element found!");
        }

        return "";
    }

    public ArrayList<Route> getRoutes(String json) {

        JSONParser parser = new JSONParser();
        ArrayList<Route> buses = new ArrayList<>();

        try{
            JSONObject obj = (JSONObject)parser.parse(json);
            String data = obj.get("data").toString();

            obj = (JSONObject)parser.parse(data);
            String references = obj.get("references").toString();

            obj = (JSONObject)parser.parse(references);
            String routes = obj.get("routes").toString();

            obj = (JSONObject)parser.parse(routes);

            Object[] array = obj.values().toArray();


            for(int i = 0; i < array.length; i++) {
                JSONObject obj2 = (JSONObject)array[i];
                String shortName = obj2.get("shortName").toString();
                String id = obj2.get("id").toString();
                buses.add(new Route(id, shortName));
            }

        } catch(ParseException pe){
            System.out.println(pe);
        } finally {
            return buses;
        }
    }

    public ArrayList<Trip> getTrips(String json, ArrayList<Route> routes) {

        JSONParser parser = new JSONParser();
        ArrayList<Trip> buses = new ArrayList<>();

        try{
            JSONObject obj = (JSONObject)parser.parse(json);
            String data = obj.get("data").toString();

            obj = (JSONObject)parser.parse(data);
            String references = obj.get("references").toString();

            obj = (JSONObject)parser.parse(references);
            String trips = obj.get("trips").toString();

            obj = (JSONObject)parser.parse(trips);


            Object[] array = obj.values().toArray();


            for(int i = 0; i < array.length; i++) {
                JSONObject obj2 = (JSONObject)array[i];
                String routId = obj2.get("routeId").toString();
                String id = obj2.get("id").toString();

                for(Route r : routes) {

                    if(routId.equals(r.getId())) {
                        buses.add(new Trip(id, r));
                    }
                }

            }

        } catch(ParseException pe){
            System.out.println(pe);
        } finally {
            return buses;
        }
    }

    public ArrayList<StopTime> getStopTimes(String json, ArrayList<Trip> trips) {

        JSONParser parser = new JSONParser();
        ArrayList<StopTime> buses = new ArrayList<>();

        try{
            JSONObject obj = (JSONObject)parser.parse(json);
            String data = obj.get("data").toString();

            obj = (JSONObject)parser.parse(data);
            String entry = obj.get("entry").toString();

            obj = (JSONObject)parser.parse(entry);
            String stopTimes = obj.get("stopTimes").toString();

            JSONArray array = (JSONArray) parser.parse(stopTimes);

            for(int i = 0; i < array.size(); i++) {
                JSONObject obj2 = (JSONObject)array.get(i);
                String tripId = obj2.get("tripId").toString();
                String at = obj2.get("predictedArrivalTime").toString();
                for(Trip t : trips) {
                    if(tripId.equals(t.getId())) {
                        buses.add(new StopTime(at, t));
                    }
                }

            }

        } catch(ParseException pe){
            System.out.println(pe);
        } finally {
            return buses;
        }
    }

    public int arriveForStopAndRoute(String json, String route) {

        String stopTimes = diveInJson(json, "data.entry.stopTimes");


        System.out.println(stopTimes);

        return 0;
    }

}


