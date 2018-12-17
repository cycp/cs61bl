package enigma;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by changyeonclarapark on 6/29/16.
 */
public class RotorTest {
    Rotor r = new Rotor();

    @Test
    public void toLetter() throws Exception {
        assertTrue(r.toLetter(0) == 'A');
        assertTrue(r.toLetter(12) == 'M');
    }

    @Test
    public void toIndex() throws Exception {
        assertTrue(r.toIndex('B') == 1);
        assertTrue(r.toIndex('Z') == 25);
    }


    /** @Test
    public void getSetting() throws Exception {
        assertEquals(r.getSetting(), 0);
    } */

    @Test
    public void set() throws Exception {
        r.set(5);
        assertEquals(r.getSetting(), 5);
    }

    @Test
    public void convert() throws Exception {
        r.set(0);

        FixedRotor f = new FixedRotor();
        f.setName("BETA");
        assertEquals(f.convertForward(9), 22);

        Reflector ref = new Reflector();
        ref.setName("B");
        assertEquals(ref.convertForward(22), 7);

        assertEquals(f.convertBackward(7), 23);

        Rotor r3 = new Rotor();
        r3.setName("III");
        r3.set(23);
        assertEquals(r3.convertBackward(23), 25);
    }

    @Test
    public void atNotch() throws Exception {
        Reflector ref = new Reflector();
        assertFalse(ref.atNotch());

        Rotor r3 = new Rotor();
        r3.setName("III");
        r3.set(21);
        assertTrue(r3.atNotch());
    }

    /*@Test
    public void advances() throws Exception {
        Reflector ref = new Reflector();
        assertFalse(ref.advances());

        Rotor r3 = new Rotor();
        r3.setName("III");
        r3.set(21);
        assertTrue(r3.advances());

    } */

    @Test
    public void advance() throws Exception {

    }

}
