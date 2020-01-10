import java.lang.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class BusinessTicket extends Ticket  {
    static int numberOfTickets = 0;
    static final int maxNumberOfTickets = 20;
    private ArrayList<String> meals = new ArrayList<String>();
    private static final int type = 2; //by default 4 seats in a row
    private static final char finalLetter = 'D';//rows are from A to D by default
    private static Matrix seatingMap = new Matrix((int)finalLetter - 65 + 1, type);
    private static ArrayList<String> notAv = new ArrayList<String>();
    private static int[] rShuffle = new int[]{0,1,2,3,4,5,6,7};


    public BusinessTicket(){
        this(new Passenger(), (int) Math.round( Math.random()*23), new ArrayList<String>(), 199);
    }
    public BusinessTicket(int price){
        this(new Passenger(), (int) Math.round( Math.random()*23), new ArrayList<String>(), price);
    }
    public BusinessTicket(Passenger passenger, int luggage, ArrayList<String> meals, int price){
        super(numberOfTickets, passenger, luggage, 2*price);///super key methods
        this.meals = meals;
        this.setNumber(numberOfTickets);
        numberOfTickets = numberOfTickets + 1;
//        for(int i=0; i<type*((int)-65+1); i++){
//            rShuffle[i] = i;
//        }
        rShuffle = RandomizeArray(rShuffle);

        //commented in purpose of testing, uncomment when finished
        //this.generateSeatNumber(this.getNumber(), this.type, this.finalLetter);
    }
    private static int[] RandomizeArray(int[] array){
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }
    public boolean generateSeatNumber(int number, int type, char finalLetter){

        if(number>0 && number <= type*((int)finalLetter-65+1) ){
            System.out.println(rShuffle[number-1]+" "+number+ " * ");
            var seat = rShuffle[number-1];
            var row = seat % ((int) finalLetter - 65 +1);
            var column = (seat - row) / ((int) finalLetter - 65 + 1);

            var str = Character.toString((char) row + 65) + column;
            this.setSeatNumber(str);
            seatingMap.data[row][column] =this.getSeatNumber()+ this.getPassenger().getSurname();
            return true;
        }else {
            return false;
        }
    }

    public boolean generateSeatNumber2(int number, int type, char finalLetter){
        var placed = false;

        if(number <= type*((int)finalLetter-65+1) && number >0) {
            System.out.println(number + " "+type*((int)finalLetter-65+1));
            while (placed == false) {
                var seat = (Math.random() * (type * ((int) finalLetter - 65+1)));
                seat = Math.round(seat);//seat represent linear index seat = col*row;

                //since we don't know row and column, we need to calculate
                var row = seat % ((int) finalLetter - 65 );
                var column = (seat - row) / ((int) finalLetter - 65 + 1);
                //System.out.println(((int) finalLetter - 65)+' ' +'-'+seat +" -"+ row+'-'+column);
/*              System.out.println("B"+seat);
                System.out.println("R"+row);
                System.out.println("C"+column);*/
                //System.out.println(seatingMap.data[(int) row][(int) column]);
                var str = Character.toString((char) row + 65) + (int) column;
                if (!notAv.contains(str)){
                    notAv.add(str);
                    this.setSeatNumber(str);
                    seatingMap.data[(int) row][(int) column] =this.getSeatNumber()+ this.getPassenger().getSurname();
                    placed = true;
                    System.out.println();
                }

//                if (seatingMap.data[(int) row][(int) column] == 'null') {
//                    var str = Character.toString((char) row + 65);
//                    this.setSeatNumber(str + (int) column);
//                    seatingMap.data[(int) row][(int) column] =this.getSeatNumber()+ this.getPassenger().getSurname();
//                    placed = true;
//                }
            }
        }else{
            return false;
        }
        return  true;
    }

    public void printInfo() {
        System.out.println("-------Business ticket info-------\r\n");
        System.out.println("Meal: " + meals);
        super.printInfo();//super key methods
    }

    @Override
    public String toString() {
        return "BusinessTicket" + super.toString()+
                " meals: " + meals;
    }

    //public abstract void seatingPosition();
    public void getSeatMap(){
        System.out.println("***Business Part of a Plane***");
        seatingMap.show();
    }
    public boolean spaceAvailable(){
        //multiplied by 2 since there are 2*type seats in a row
        //slightly different than economy part arrangement
        return type * ((int) finalLetter - (int) 'A') != numberOfTickets; //all seats taken

    }
    public boolean finalCheck(){
        //we can also check for array of meals to be a non-zero length
        //System.out.println("Check passenger " + this.getPassenger().getName()+this.getPassenger().getSurname());
        return this.getPassenger().finalCheck() && this.getSeatNumber() != "";
    }

}
