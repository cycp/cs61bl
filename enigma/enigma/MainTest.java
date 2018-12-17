package enigma;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by changyeonclarapark on 6/30/16.
 */
public class MainTest {

    Main m = new Main();

    @Test
    public void isConfigurationLine() {
        //assertFalse(m.isConfigurationLine("ASDLFJ"));
        //assertTrue(m.isConfigurationLine("* b beta I II III AAAA"));
    }

    @Test
    public void standardize() {
        //assertEquals(m.standardize("aslk   "), "ASLK");
        //assertEquals(m.standardize(" Hello      there"), "HELLOTHERE");
    }

}
