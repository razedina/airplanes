import java.util.Comparator;

public abstract class Ticket {
    private int number;//should be disabled for test function
    private Passenger passenger;
    private double price = 100;
    private final static int  maxLuggage = 23;
    private String seatNumber;
    private int luggage;
    public static Comparator<Ticket> ticketComparator = new Comparator<Ticket>(){
        @Override
        public int compare(Ticket a, Ticket b){
            return a.getPassenger().getSurname().compareTo(b.getPassenger().getSurname());
        }
    };


    //using first 2 constructors, discount option is disabled
    public Ticket(){
        this(new Passenger(), 23,99);
    }
    public Ticket(Passenger passenger, int luggage, int price){
        //different check functions are valid for Pass and CrewMem
        //Different rules for passport and card validity
        this.setPassenger(passenger);
        this.setLuggage(luggage);//luggage must be under maxluggage value
        this.price = price;
    }
    //this constructor enables possible discount
    public Ticket(int number, Passenger passenger, int luggage, int price){
        //different check functions are valid for Pass and CrewMem
        //Different rules for passport and card validity
        this.setPassenger(passenger);
        this.setLuggage(luggage);//luggage must be under maxluggage value
        this.price = price;
        if(this.passenger.possibleDiscount(number))
            this.price = 0.7*this.price;

    }


    //Ticket info
    public void printInfo(){
        System.out.println(this.getPassenger());
        System.out.println("Ticket Number: " + this.getNumber());
        System.out.println("Seat Number: " + this.getSeatNumber());
        System.out.println("Available Luggage: " + this.getMaxLuggage()+"kg");
        System.out.println("Customer's Luggage: " + this.luggage+"kg");
    }

    public abstract boolean generateSeatNumber(int number, int type, char finalLetter);
    //public abstract void seatingPosition();
    public abstract void getSeatMap();
    public abstract boolean spaceAvailable();
    public abstract boolean finalCheck();



    //getters and setters
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
   }

    public int getMaxLuggage() {
        return maxLuggage;
    }
    public String getSeatNumber() {
        return seatNumber;
    }

    //abstr
    //Available Luggage: 23kgact method since there is different distribution of a seats for E and B ticket
    public  void setSeatNumber(String number){
        this.seatNumber = number;
    }

    public int getLuggage() {
        return this.luggage;
    }
    public void setLuggage(int luggage) {
        if(luggage <= this.getMaxLuggage())
            this.luggage = luggage;
        else {
            System.out.println(this.getPassenger().getName() + this.getPassenger().getSurname()+"Maximum luggage is exceeded, please remove " + (luggage-this.getMaxLuggage()) + "kg");
            this.luggage = this.getMaxLuggage();
        }
    }
    public Passenger getPassenger() {
        return passenger;
    }

    public boolean setPassenger(Passenger passenger) {

        if(passenger.checkPassportDate(90) && passenger.checkValidationCardDate()){
            this.passenger = passenger;
            return true;
        }
        else {
            //System.out.println(passenger.getName() + " "+passenger.getSurname()+" does not satisfy flight conditions");
            this.passenger = passenger;
            return false;
        }
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return number +"*  "+ passenger +
                ", price=" + price +
                ", seatNumber='" + seatNumber + '\'' +
                ", luggage=" + luggage;
    }
}
