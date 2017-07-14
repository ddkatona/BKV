package entity;

import java.text.SimpleDateFormat;

/**
 * Created by ddkatona on 2017.07.14..
 */
public class StopTime {

    private String predictedArrivalTime;
    private Trip trip;

    public StopTime(String pAT, Trip t) {
        predictedArrivalTime = pAT;
        trip = t;
    }

    public void printInfo() {
        System.out.print("Time(" + predictedArrivalTime + "): "); trip.printInfo();
    }

    public void printLineTimeLeft() {
        System.out.println(trip.getRoute().getShortName() + ": " + secsUntil());
    }

    public void printLineTimeLeft_mmss() {
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");
        String time = df.format(secsUntil()*1000);

        System.out.println(trip.getRoute().getShortName() + ": " + time);
    }

    public long secsUntil() {
        long pAT = Integer.parseInt(predictedArrivalTime);

        long curr = System.currentTimeMillis()/1000;

        if(pAT - curr < 0) {
            return 0;
        }

        return pAT - curr;
    }

}
