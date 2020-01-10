import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.io.*;

//no refund policy
//flight is the main class, contains everything
public class Flight{
    private String flightNumber;
    private int ticketPriceFromTable;
    //just to have values and compare in final check function
    private LocalDateTime arrival = LocalDateTime.now();
    private LocalDateTime departure = LocalDateTime.now();
    private Airport arrivalAirport;
    private Airport departureAirport;
    //private static int maxNumOfPassengers;//this field is redundant because it is necessary to track business class, economy class and member crew separately
    private ArrayList<CrewMember> crewMembers = new ArrayList<CrewMember>();
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    private ArrayList<String> cities = new ArrayList<String>();
    private int[][] ticketPrices = new int[][]{{-1, 100, 80, 150, 500},
                                    {100, -1, 50, 20, 600},
                                    {80, 50, -1, 100, 400},
                                    {150, 20, 100, -1, 800},
                                    {500, 600, 400, 800, -1}};
    //private ArrayList<Passenger> passengers;

    //----------------Constructors--------------------------
    public Flight() throws MyException {
        //first goes departure time than arrival
        this(0, LocalDateTime.of(2020,3,3,3,5,6), LocalDateTime.of(2020,3,4,4,3,5), new Airport(), new Airport());
    }
    public Flight(int flightNumber,  LocalDateTime departure, LocalDateTime arrival, Airport arrivalAirport, Airport departureAirport) throws MyException {
        cities.add("Dublin");
        cities.add("Sarajevo");
        cities.add("Vienna");
        cities.add("Mostar");
        cities.add("Cape Town");
        if (!this.setArrivalAndDeparture(arrival, departure))
            throw new MyException("Arrival cannot be before departure");

        if(!this.setAirports(departureAirport, arrivalAirport))
            throw new MyException("Airports are the same");
        this.generateFlightNumber(arrivalAirport.getName(),arrivalAirport.getGateNumber(), departureAirport.getName(), departureAirport.getGateNumber(), flightNumber);
        if(!this.generatePrice())
            throw new MyException("Airports are not in the database");
        //Commented in purpose of testing
        /*for(int i=0; i<3; i++){
            this.addETicket();
            this.addBTicket();
        }

        for(int i=0; i<4; i++)
        this.addCrewMember();
        //{"Dublin", "Sarajevo", "Vienna","Mostar", "Cape Town"};*/
    }
    public Flight(int flightNumber,  LocalDateTime departure, LocalDateTime arrival, Airport arrivalAirport, Airport departureAirport, ArrayList<CrewMember> crewMembers, ArrayList<Ticket> tickets) throws MyException {
            this(flightNumber,departure,arrival,arrivalAirport,departureAirport);
        this.tickets = tickets;
        this.crewMembers = crewMembers;
    }
private boolean generatePrice(){

    var arrAir = this.arrivalAirport.getName();
    var depAir = this.departureAirport.getName();
    int col;
    int row;
    if(cities.contains(depAir) && cities.contains(arrAir) ){
        row = cities.indexOf(depAir);
        col = cities.indexOf(arrAir);
        this.ticketPriceFromTable = ticketPrices[row][col];
        return true;

    }else {
        return false;
    }
}

    public void generateFlightNumber(String arrival, int gate1,  String departure, int gate2, int number){
        this.flightNumber = arrival.charAt(0)+Integer.toString(gate1) + departure.charAt(0) + gate2 + number;
    }
    /*private void addETicket(){
        Ticket t = new EconomyTicket();
        this.tickets.add(t);

    }*/

    public String getFlightNumber() {
        return this.flightNumber;
    }

    public void addBTicket(){
        Ticket tb = new BusinessTicket(this.ticketPriceFromTable);
        this.tickets.add(tb);
    }
    public void addETicket(){
        Ticket tb = new EconomyTicket(ticketPriceFromTable);
        //System.out.println("Ticket added");
        this.tickets.add(tb);
    }
    public boolean addTicket(Ticket t){
        if(t.spaceAvailable() && !this.tickets.contains(t)) {
            this.tickets.add(t);
            return true;
        }
        return false;
    }

    public void addCrewMember(){
        this.crewMembers.add(new CrewMember());
    }
    public void addCrewMember(CrewMember c){
        if(!this.crewMembers.contains(c))
            this.crewMembers.add(c);
    }

    //callded by function generate number
    public void setFlightNumber(String number) {
        this.flightNumber = number;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public boolean setArrival(LocalDateTime arrival) {
        if (arrival.isAfter(this.getDeparture())){
            this.arrival = arrival;
            return true;
        }
        else {
            //System.out.println("Arrival time cannot be before departure time");
            return false;
        }
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public boolean setDeparture(LocalDateTime departure) {
        if(departure.isBefore(this.getArrival())){
            this.departure = departure;
            return true;
        }
        else {
            //System.out.println("Departure time cannot be after arrival time");
            return false;
        }

    }
    public boolean setArrivalAndDeparture(LocalDateTime arrival, LocalDateTime departure ){
        if(arrival.isAfter(departure)){
            this.arrival = arrival;
            this.departure = departure;
            return true;
        }
        else {
            //System.out.println("Departure time cannot be after arrival time");
            return false;
        }
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    //no checks for airport since final check is made
    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Airport getDeparatureAirport() {

        return this.departureAirport;
    }

    public void setDeparatureAirport(Airport deparatureAirport) {
        this.departureAirport = deparatureAirport;
    }

    public boolean setAirports(Airport arrivalAirport, Airport departureAirport){
//        if(arrivalAirport.compareTo(departureAirport) == 1){
        if(Airport.airportComparator.compare(arrivalAirport, departureAirport) >=   1){
            //if(cities.contains(arrivalAirport.getName()) && cities.contains(departureAirport.getName())) {
                this.arrivalAirport = arrivalAirport;
                this.departureAirport = departureAirport;
                return true;
            //}
        }
        return false;

    }


    public ArrayList<Ticket> getTickets() {
        return this.tickets;
    }

    @Override
    public String toString() {
        return "--------------Flight Information---------------"+
                "\r\n Flight Number:'" + flightNumber + '\'' +
                "\r\n Arrival time: " + arrival +
                "\r\n Departure time: " + departure +
                "\r\n\r\n ***Arrival Airport Info***" + arrivalAirport +
                "\r\n\r\n ***Departure Airport Info***" + departureAirport;

    }

    public void show(){
        System.out.println("--------------Flight Information---------------"+
                "\r\n Flight Number:'" + flightNumber + '\'' +
                "\r\n Arrival time: " + arrival +
                "\r\n Departure time: " + departure +
                "\r\n\r\n ***Arrival Airport Info***" + arrivalAirport +
                "\r\n\r\n ***Departure Airport Info***" + departureAirport);

        for(Ticket t:this.tickets){
            System.out.println(t);
        }
        //sort before printing
        this.sortCrewMembers();
        System.out.println("---Crew Members---");
        Iterator it = this.crewMembers.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
/*        for(CrewMember t:this.crewMembers){
            System.out.println(t);
        }*/
    }
    //implement additional luggage
    public boolean finalCheck(){
        //check all fields for flight
        if(this.flightNumber != null){
            System.out.println("Flight Number generated");
        }
        if(this.departureAirport.finalCheck()){
            System.out.println("Departure Airport set correctly");
        }
        if(this.arrivalAirport.finalCheck()){
            System.out.println("Arrival Airport set correctly");
        }
        if(this.flightNumber != null && this.departureAirport.finalCheck() && this.arrivalAirport.finalCheck()){
            if(this.arrival.isBefore(LocalDateTime.now()) || this.departure.isBefore(LocalDateTime.now())){
                //System.out.println(arrival);
                //System.out.println(departure);
                System.out.println("Set arrival and departure time correctly");
                return false;
            }
            var sumLugg = 0;
            for(Ticket t: this.tickets){
                {
                    if (!t.finalCheck())
                        return false;

                    sumLugg = sumLugg + (t.getMaxLuggage()-t.getLuggage());
                }
            }
            System.out.println("Plane can bring additional " + sumLugg +" kg.");
            for(CrewMember c: this.crewMembers){
                if(!c.finalCheck())
                    return false;
            }

        }else{
            return false;
        }

        return true;

    }

    public void sortCrewMembers(){
        Collections.sort(crewMembers);
        System.out.println("Crew Members sorted by level");
/*        for (CrewMember mem:crewMembers){
            System.out.println(mem);
        }*/
/*        Iterator it = this.crewMembers.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }*/
    }

/*   public void findPassenger(Passenger pas){
        Iterator it = this.tickets.iterator();
        var a = true;
         while (it.hasNext() && a) {
             Ticket t1 =  (Ticket) it.next();
             if (t1.getPassenger() == pas) {
                 System.out.println("Passenger is in this flight");
                 System.out.println(t1);
                 a = false;
             }
         }
         if(a)
             System.out.println("Passenger is not in this flight");

    }*/
    //binary search implemented in purpose of better understanding structures
    public int findPassenger(int l, int r, Passenger p){
        Collections.sort(this.tickets, Ticket.ticketComparator);
        if(r >=0  && l < this.tickets.size()-1) {
            int mid = l+(r-1)/2;
            if(this.tickets.get(mid).getPassenger() == p){
                return mid;
            }
            System.out.println(this.tickets.get(mid).getPassenger().getSurname() + p.getSurname());

            if(this.tickets.get(mid).getPassenger().getSurname().compareTo(p.getSurname()) > 0){
                //System.out.println("Dinno"+this.tickets.get(mid).getPassenger().getSurname() + p.getSurname());
                return this.findPassenger(l,mid-1,p);

            }else return this.findPassenger(mid+1,r,p);
        }
        return -1;
    }
    public int getTicketPriceForFlight(){
        return this.ticketPriceFromTable;
    }
}

