import java.time.LocalDate;
import java.time.LocalDateTime;

public class Test {
    public void contructorFlight() throws MyException {
        Flight f = new Flight();
    }

    public static void settingPassenger() {
        Passenger p1 = new Passenger();
        Passenger c1 = new CrewMember();
        System.out.println(p1);
        System.out.println(c1);

    }

    public static void constructorEconomyT() {
        Ticket t = new EconomyTicket();
        t.printInfo();
        Ticket t2 = new EconomyTicket(new Passenger(), 25, "Salad", 100);
        t2.printInfo();
    }

    public static void generateSeatNumberTest() {
        Ticket t = new EconomyTicket();
        //the seats will be given taking into consideration balans
        //first seat is A0, then J0, A1,J1 ...
        //function tests first 20 seats
        //to test this function comment in Ticket constr this. seatNumber = this.generateSeatNumber();
        for (int i = 0; i < 20; i++) {
            t.generateSeatNumber(i, 4, 'J');
            t.printInfo();
        }
        t.getSeatMap();
    }

    //generate 20 tickets
    //test of both parameterless and parameter constructor in the same time
    //because parameterless calls constructor with parameters
    public static void economyTicketTest() {
        Ticket t = new EconomyTicket();
        for (int i = 0; i < 20; i++) {
            t.printInfo();
            t = new EconomyTicket();
        }

        t.getSeatMap();
    }

    public static void businessTicketTest() {
        Ticket t = new BusinessTicket();
        for (int i = 0; i < 5; i++) {
            t.printInfo();
            t = new BusinessTicket();
        }

        t.getSeatMap();
    }


    public static Flight settingFlight() throws MyException {
        //constructin an airport
        Airport aAirport = new Airport("Dublin", 9, 3);
        Airport dAirport = new Airport("Sarajevo", 1, 12);


        LocalDateTime aTime = LocalDateTime.of(2020, 11, 11, 17, 00);
        LocalDateTime dTime = LocalDateTime.of(2020, 11, 11, 16, 00);

        var flightNumber = 1;
        Flight f1 = new Flight(flightNumber, dTime, aTime, aAirport, dAirport);
/*        try {

             f1 = new Flight(flightNumber, dTime, aTime, aAirport, dAirport);
        }catch(MyException e){
            throw e;
        }*/
        //flight number is made up airport info + flightnumber (1 in this case)
        //System.out.println(f1);
        f1.show();
        return f1;

    }

    public static void flightPassCrew() throws MyException {
        //construction of airport
        Airport aAirport = new Airport("Dublin", 9, 3);
        Airport dAirport = new Airport("Sarajevo", 1, 12);


        LocalDateTime aTime = LocalDateTime.of(2020, 11, 11, 17, 00);
        LocalDateTime dTime = LocalDateTime.of(2020, 11, 11, 19, 00);

        var flightNumber = 1;

        Flight f1 = new Flight(flightNumber, dTime, aTime, aAirport, dAirport);
        //flight number is made up airport info + flightnumber (1 in this case)
        //System.out.println(f1);
        f1.show();


    }

    public static void testIterator() throws MyException {
        //Flight class, line code 215
        Flight f = settingFlight();
        Passenger p = new Passenger("Edina", "Razanica", LocalDate.of(2021, 11, 11),
                LocalDate.of(2021, 11, 11), "Bosnia");
        Passenger p1 = new Passenger("Emina", "Razanica", LocalDate.of(2021, 11, 11),
                LocalDate.of(2021, 11, 11), "Bosnia");

        var ticketPrice = f.getTicketPriceForFlight();
        Ticket t = new EconomyTicket(p, 12, "Pizza", ticketPrice);
        f.addTicket(t);
        System.out.println("Number of tickets: "  + f.getTickets().size());
        System.out.println(f.findPassenger(0, f.getTickets().size() - 1, p1));

    }

    public static Flight addTicketToFlight(Flight f) {
        Passenger p = new Passenger();
        if (p.checkPassportDate(90) && p.checkValidationCardDate()) {
            Ticket newT = new EconomyTicket(p, 30, "Salad", 100);
            f.addTicket(newT);
            p.checkValidationCardDate();
        }


        CrewMember c = new CrewMember("ETFSarajevo", 2, 2, 4000);
        if (c.checkPassportDate(90) && c.checkValidationCardDate()) {
            f.addCrewMember(c);
            c.checkValidationCardDate();
        }
        //f.show();
        return f;

    }


    public static void finalCheck1() throws MyException {
        Airport aAirport = new Airport("Dublin", 9, 3);
        Airport dAirport = new Airport("Sarajevo", 1, 12);
        //problem i a arrival time. aTime passed
        //LocalDateTime aTime = LocalDateTime.now();
        LocalDateTime aTime = LocalDateTime.of(2020, 11, 11, 21, 00);
        LocalDateTime dTime = LocalDateTime.of(2020, 11, 11, 19, 00);

        var flightNumber = 1;

        Flight f1 = new Flight(flightNumber, dTime, aTime, aAirport, dAirport);
        if (f1.finalCheck())
            System.out.println("Ready to go!");
        else System.out.println("Not ready to go!");
    }

    public static void finalCheck2() throws MyException {
        Airport aAirport = new Airport("Paris", 9, 3);
        Airport dAirport = new Airport("Sarajevo", 1, 12);

        //problem i a arrival time. aTime passed
        LocalDateTime aTime = LocalDateTime.of(2020, 11, 11, 21, 00);
        LocalDateTime dTime = LocalDateTime.of(2020, 11, 11, 20, 00);

        var flightNumber = 1;

        Flight f1 = new Flight(flightNumber, dTime, aTime, aAirport, dAirport);

        f1 = addTicketToFlight(f1);

        if (f1.finalCheck())
            System.out.println("Ready to go!");
        else System.out.println("Not ready to go!");

/*
        //passenger is ok, change atributes values for different outcomes
        Passenger p2 = new Passenger("", "Razanica", LocalDate.of(2222,2,2),LocalDate.of(2022,4,4), "Germany");
        Ticket newT = new EconomyTicket(p2, 30,"");
        f1.addTicket(newT);

        if(f1.finalCheck())
            System.out.println("Ready to go!");
        else System.out.println("Not ready to go!");


        //after setting proper name
        f1.getTickets().get(f1.getTickets().size()-1).getPassenger().setName("Edina");

        if(f1.finalCheck())
            System.out.println("Name changed, Ready to go!");
        else System.out.println("Not ready to go!");

        //crew member is ok
        CrewMember c = new CrewMember("ETFSarajevo", 2, 2, 4000);
        if(c.checkPassportDate(90) && c.checkValidationCardDate()){
            f1.addCrewMember(c);
        }

        if(f1.finalCheck())
            System.out.println("Ready to go!");
        else System.out.println("Not ready to go!");

        //rules for crew members are different, not strict wiht passport and credit card
        CrewMember c1 = new CrewMember("Emina", "Okanovic", LocalDate.of(2019, 11,28), LocalDate.of(2000,2,2),"Austria", "UNSA", 4, 3,1000);
        f1.addCrewMember(c1);

        if(f1.finalCheck())
            System.out.println("Ready to go!");
        else System.out.println("Not ready to go!");
        */
    }


    public static void polymorphism() {
        //var f1 = settingFlight();
        Ticket t1 = new BusinessTicket();
        Ticket t2 = new EconomyTicket();

        System.out.println(t1);
        System.out.println(t2);

    }

    public static void comparatorTest() throws MyException {
        //testing compare
        var f = settingFlight();
        Airport a1 = new Airport("Sarajevo", 123, 1);
        Airport a2 = new Airport("Amsterdam", 123, 1);

        if (f.setAirports(a1, a1)) {
            System.out.println("Successful! " + a1.getName() + " to " + a1.getName());
        } else {
            System.out.println("Try again! " + "Invalid " + a1.getName() + " to " + a1.getName());
        }
        if (f.setAirports(a1, a2)) {
            System.out.println("Successful! " + a1.getName() + " to " + a2.getName());
        } else {
            System.out.println("Try again!");
        }
    }


    public static void main(String[] args) {
        //settingPassenger();
        //constructorEconomyT();
        ////generateSeatNumberTest();
        //economyTicketTest();
        //settingFlight();

        //------this line important
        // flightPassCrew();

/*
        var f1 = settingFlight();
        //f1.show();
        f1.getTickets().get(0).getSeatMap();
        var f2 = addTicketToFlight(f1);
        //f2.show();
        f2.getTickets().get(0).getSeatMap();
        //to add passengers and crew members

        Passenger p2 = new Passenger("Edina", "Razanica", LocalDate.of(2022,2,2),LocalDate.of(2022,4,4), "Germany");
        Ticket newT = new EconomyTicket(p2, 30,"");
        f2.addTicket(newT);
        f2.getTickets().get(0).getSeatMap();


/*
        //irregularFlight();
        //int apple = 0;
        //System.out.println(apple);

        /*

        //correct final check
        var f1 = settingFlight();
        f1 = addTicketToFlight(f1);
        if(f1.finalCheck())
            System.out.println("Ready to go!");
        else
            System.out.println("Not ready to go, check flight information");

         */
        //finalCheck1();
        //finalCheck2();

        //checkOverridenMethods();


//        -----2nd part------
        //businessTicketTest();
        //polymorphism();
        //flightPassCrew(); //sorting crews members, comparable test
        //testIterator();
        //comparatorTest();

        //overridden methods
/*       var f = settingFlight();
        addTicketToFlight(f);*/

        try {
            var f = settingFlight();
            //testIterator();
            //finalCheck1();
        } catch (MyException e) {
            System.out.println(e);

        }
    }
}
