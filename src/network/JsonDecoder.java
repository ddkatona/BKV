/**
 * Created by ddkatona on 7/13/2017.
 */

package network;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.util.*;

public class JsonDecoder {

    public ArrayList<String> routesForStop(String json) {

        JSONParser parser = new JSONParser();
        ArrayList<String> buses = new ArrayList<>();

        try{
            System.out.println(json);

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
                buses.add(shortName);
            }

        } catch(ParseException pe){
            System.out.println(pe);
        } finally {
            return buses;
        }
    }

}


