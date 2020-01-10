import org.junit.Test;

import static org.junit.Assert.*;

public class BusinessTicketTest {

    @Test

    public void generateSeatNumber() {
        //for various tests, limit i is changed
        Ticket t = new BusinessTicket();
        //the seats will be given taking into consideration balans
        //first seat is A0, then J0, A1,J1 ...
        //function tests first 20 seats
        //to test this function comment in Ticket constr this. seatNumber = this.generateSeatNumber();
        for (int i = 0; i <=6; i++) {
            System.out.println(t.generateSeatNumber(i, 2, 'D'));
            //t.printInfo();
        }
        t.getSeatMap();
    }
    @Test
    public void generateSeatNumber2() {
        //for various tests, limit i is changed
        Ticket t = new BusinessTicket();
        //the seats will be given taking into consideration balans
        //first seat is A0, then J0, A1,J1 ...
        //function tests first 20 seats
        //to test this function comment in Ticket constr this. seatNumber = this.generateSeatNumber();
        int result = 0;
        for (int i = 1; i <=20; i++) {
            System.out.println(i);
            if(!t.generateSeatNumber(i, 2, 'D')){
                result = i-1;
                break;

            }
        }
        t.getSeatMap();
        assertEquals(8, result);
    }
}