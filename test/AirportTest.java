import org.junit.Test;

import static org.junit.Assert.*;

public class AirportTest {
    Airport a1 = new Airport("Sarajevo", 5, 12);
    Airport a2 = new Airport("Mostar", 4, 2);

    @Test
    public void compareTo() {
        var result  = -1;
        if(a1.compareTo(a2) > 0){
            result = 1;
        }else if (a1.compareTo(a2) == 0) {
            result = 0;
        }
        assertEquals(1, result);
    }

    @Test
    public void setName() {
        var result = a1.setName("");
        assertFalse(result);
    }

    @Test
    public void setGateNumber() {
        assertFalse(a1.setGateNumber(-3));
    }
    @Test
    public void setGateNumberT() {
        assertTrue(a1.setGateNumber(3));
    }


    @Test
    public void setRunwayNumber() {
        assertFalse(a1.setGateNumber(-3));
    }

    @Test
    public void finalCheck() {
        assertTrue(a1.finalCheck());
    }
}