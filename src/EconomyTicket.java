public class EconomyTicket extends Ticket {
    private String meal;
    //set method not available for numberOfTicket
    private static int numberOfTickets = 0;
    private static final int type = 4; //by default 4 seats in a row
    private static final char finalLetter = 'J';//rows are from A to J by default
    //setting map will have type columns and int(A-J+1) rows
    private static Matrix seatingMap = new Matrix((int)finalLetter - 65 + 1, type);

    public EconomyTicket(){
        this(new Passenger(), (int) Math.round( Math.random()*23), "Sandwich", 99);
        }
        public EconomyTicket(int price){
        this(new Passenger(), (int) Math.round( Math.random()*23), "Sandwich", price);
    }
    public EconomyTicket(Passenger passenger, int luggage, String meal, int price){
        super(numberOfTickets, passenger, luggage, price);///super key methods
        this.meal = meal;
        this.setNumber(numberOfTickets);
        numberOfTickets = numberOfTickets + 1;
        //commented in purpose of testing, uncomment later
        //this.generateSeatNumber(this.getNumber(), this.type, this.finalLetter);
    }
    public void printInfo(){
        System.out.println("-------Economy ticket info-------\r\n");
        System.out.println("Meal: " + meal);
        super.printInfo();//super key methods
}

    public boolean generateSeatNumber(int number, int type, char finalLetter){
        var row = (number - number%(2*type) )/(2*type);
        var remainder = number%(2*type);
        //System.out.println(number + "***"+type*((int) finalLetter-65+1) );
        if (number < type*((int) finalLetter-65+1)) {
            if (number % 2 == 0) {
                var str = Character.toString((char) row + 65);
                this.setSeatNumber(str + remainder / 2);
                seatingMap.data[row][remainder / 2] = str + remainder / 2 + this.getPassenger().getSurname();
            } else {
                var str = Character.toString((int) finalLetter - (char) row);
                this.setSeatNumber(str + Math.round(remainder / 2));
                seatingMap.data[(int) finalLetter - 65 - row][Math.round(remainder / 2)] = str + remainder / 2 + " " + this.getPassenger().getSurname();
            }
        }else{
            return false;
        }
        return true;

    }
    public void getSeatMap(){
        //System.out.println(seatingMap);
        seatingMap.show();
    }

    //used when adding new tickets in Flight Class
    //must be an abstract method because it is checked on object ticket
    public boolean spaceAvailable(){
        return type * ((int) finalLetter - (int) 'A') != numberOfTickets; //all seats taken
    }
    public  boolean finalCheck(){
        //System.out.println(!this.getPassenger().finalCheck() + this.getSeatNumber());
        //System.out.println("Check passenger " + this.getPassenger().getName()+this.getPassenger().getSurname());
        return this.getPassenger().finalCheck() && this.getSeatNumber() != "";
    }
    //Setters and Getters
    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        if (meal.length() > 0){
            this.meal = meal;
        }else{
            this.meal = "Snack";
        }

    }

    @Override
    public String toString() {
        return "EconomyTicket" + super.toString() +
                " meal='" + meal;
    }

    public static int getNumberOfTickets() {
        return numberOfTickets;
    }

    //\type and final letter only have getters
    public int getType() {
        return type;
    }
    public char getFinalLetter() {
        return finalLetter;
    }


    //public boolean assistanceNeeded(){    }
    //

}
