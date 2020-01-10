import org.junit.Test;

import static org.junit.Assert.*;

public class EconomyTicketTest {

    @Test
    public void generateSeatNumber() {
        //for various tests, limit i is changed
        Ticket t = new EconomyTicket();
        //the seats will be given taking into consideration balans
        //first seat is A0, then J0, A1,J1 ...
        //function tests first 20 seats
        //to test this function comment in Ticket constr this. seatNumber = this.generateSeatNumber();
        for (int i = 0; i < 20; i++) {
            t.generateSeatNumber(i, 4, 'J');
            //t.printInfo();
        }
        t.getSeatMap();
    }

    @Test
    public void generateSeatNumberOverload() {
        Ticket t = new EconomyTicket();
        //the seats will be given taking into consideration balans
        //first seat is A0, then J0, A1,J1 ...
        //function tests first 20 seats
        //to test this function comment in Ticket constr this. seatNumber = this.generateSeatNumber();
        int result = -1;
        for (int i = 0; i < 50; i++) {
            if (!t.generateSeatNumber(i, 4, 'J')) {
                result = i;
                //break;
            }
            //t.printInfo();
        }
        int expected = 40;

        t.getSeatMap();
        assertEquals(expected, result);
    }
}