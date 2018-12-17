package enigma;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by changyeonclarapark on 6/30/16.
 */
public class MachineTest {

    Machine m = new Machine();

    @Test
    public void replaceRotors() {
        Rotor[] r = new Rotor[5];
        Reflector ref = new Reflector();
        r[0] = ref;
        r[1] = new FixedRotor();
        for (int i = 2; i < 5; i++) {
            r[i] = new Rotor();
        }
        m.replaceRotors(r);

        //testing...
        //assertTrue(m.getRotors()[0] == ref);

    }

    @Test
    public void setandNameRotors() {
        Rotor[] r = new Rotor[5];
        Reflector ref = new Reflector();
        r[0] = ref;
        r[1] = new FixedRotor();
        for (int i = 2; i < 5; i++) {
            r[i] = new Rotor();
        }
        m.replaceRotors(r);

        //testing....
        m.setRotors("aBCD");
       // assertTrue(m.getRotors()[0].getSetting() == r[0].toIndex('A'));

        m.nameRotors("B     GAMMA I II III ABCD");
        //for (int i = 0; i < 5; i++) {
          //  System.out.println(r[i].getName());
        //}
        //assertEquals(r[0].getName(), "B");
        //assertEquals(r[4].getName(), "III");

    }

    @Test
    public void convert() {
        Rotor[] r = new Rotor[5];
        Reflector ref = new Reflector();
        r[0] = ref;
        r[1] = new FixedRotor();
        for (int i = 2; i < 5; i++) {
            r[i] = new Rotor();
        }
        m.replaceRotors(r);
        m.setRotors("aBCD");
        m.nameRotors("B GAMMA I II III ABCD");

        //testing...
        m.convert("hello");
    }
}
