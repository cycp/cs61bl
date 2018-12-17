package enigma;

/** Class that represents a rotor that has no ratchet and does not advance.
 *  @author Changyeon Park
 */
class FixedRotor extends Rotor {

    // This needs other methods or constructors.

    /** Constructs a fixed rotor. */
    FixedRotor() {
        super();
    }

    @Override
    boolean atNotch() {
        return false;
    } //fixed rotors do not have a notch.

}
