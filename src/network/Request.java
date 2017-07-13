package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ddkatona on 2017.07.13..
 */
public class Request {

    public String get() {

        String result = "";

        try {
            URL url = new URL("http://futar.bkk.hu/bkk-utvonaltervezo-api/ws/otp/api/where/arrivals-and-departures-for-stop.json?stopId=BKK_F00979");
            URLConnection yc = null;
            yc = url.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                result += inputLine;
            in.close();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }

    }
}
