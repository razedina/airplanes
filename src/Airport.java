import java.time.LocalDateTime;
import java.util.*;
import java.io.*;

public class Airport implements Comparable<Airport> {
    private String name;
    private int gateNumber = -1;
    private int runwayNumber = -1;

    //used when setting both airports
    public static Comparator<Airport> airportComparator= new Comparator<Airport>(){
        @Override
        public int compare(Airport a, Airport b){
            return a.compareTo(b);
        }
    };
    //used to compare previously set airport
    public int compareTo(Airport a){
        return this.getName().compareTo(a.getName());
/*        if(this.getName() == a.getName())
            return 0;
        return 1;*/
    }

    public Airport(){
        this("SetName", 9999, 9999);
    }
    public Airport(String name, int gateNumber, int runwayNumber){
        this.setGateNumber(gateNumber);
        this.setRunwayNumber(runwayNumber);
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if(name.length() != 0){
            this.name = name;
            return true;
        }
        return false;
    }

    public int getGateNumber() {
        return gateNumber;
    }

    public boolean setGateNumber(int gateNumber) {
        if (gateNumber > -1) {
            this.gateNumber = gateNumber;
            return true;
        }
        return false;
    }

    public int getRunwayNumber() {
        return runwayNumber;
    }

    public boolean setRunwayNumber(int runwayNumber) {
        if (runwayNumber > -1) {
            this.runwayNumber = runwayNumber;
            return true;
        }
        return false;
    }

    public boolean finalCheck(){
        //System.out.println("Airport information are not complete");
        return this.getGateNumber() > -1 && this.getName() != null && this.getRunwayNumber() > -1;
    }
    @Override
    public String toString() {
        return
                "\r\n Airport Name: '" + name + '\'' +
                "\r\n gateNumber: " + gateNumber +
                "\r\n runwayNumber: " + runwayNumber;
    }

}
