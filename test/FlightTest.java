import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FlightTest {
    //constructin an airport
    private Airport aAirport = new Airport("Dublin", 9, 3);
    private Airport dAirport = new Airport("Sarajevo", 1, 12);


    private LocalDateTime aTime = LocalDateTime.of(2020, 11, 11, 17, 00);
    private LocalDateTime dTime = LocalDateTime.of(2020, 11, 11, 16, 00);

    private int flightNumber = 1;
    private Passenger p1 = new Passenger("Edina", "Razanica", LocalDate.of(2021, 11, 11),
            LocalDate.of(2021, 11, 11), "Bosnia");
    private Passenger p2 = new Passenger("Emina", "Okanovic", LocalDate.of(2021, 11, 11),
            LocalDate.of(2021, 11, 11), "Bosnia");
    private Passenger p3 = new Passenger("Zehra", "Beslija", LocalDate.of(2021, 11, 11), LocalDate.of(2021, 11, 11), "Bosnia");
    private Passenger p4 = new Passenger("Dinno", "Koluh", LocalDate.of(2021, 11, 11),
            LocalDate.of(2021, 11, 11), "Bosnia");
    private Passenger p5 = new Passenger("Marco", "Parigi", LocalDate.of(2021, 11, 11),
            LocalDate.of(2021, 11, 11), "Bosnia");
    private Passenger p6 = new Passenger("Kemal", "Altwlakny", LocalDate.of(2021, 11, 11),
            LocalDate.of(2021, 11, 11), "Bosnia");
    private ArrayList<Ticket> testTicket= new ArrayList<Ticket>();
    private ArrayList<CrewMember> testCrew= new ArrayList<CrewMember>();
    @Test
    public void findPassenger(){
        testTicket.add(new EconomyTicket(p1, 12, "Pizza", 100));
        testTicket.add(new EconomyTicket(p2, 12, "Pizza", 100));
        testTicket.add(new EconomyTicket(p3, 12, "Pizza", 100));
        testTicket.add(new EconomyTicket(p4, 12, "Pizza", 100));
        testTicket.add(new EconomyTicket(p5, 12, "Pizza", 100));
        int mid = 1 + 1/2;
        System.out.println(mid);
        Flight f1;
        int result = -3;
        try
        {
             f1= new Flight(flightNumber, dTime, aTime, aAirport, dAirport,testCrew,testTicket);
             System.out.println(f1);
            result = f1.findPassenger(0,testTicket.size()-1,p6);
        }catch(MyException e){
            System.out.println(e);
        }finally {
            // Zehra, Dinno, Emina, Edina
            assertEquals(-1, result);
        }


    }

}