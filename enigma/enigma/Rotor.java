// This is a SUGGESTED skeleton file.  Throw it away if you don't use it.
package enigma;

import static enigma.PermutationData.ROTOR_SPECS;

/** Class that represents a rotor in the enigma machine.
 *  @author Changyeon Park
 */
class Rotor {
    // This needs other methods, fields, and constructors.

    Rotor() {
        _setting = 0;
        _name = null;
    }

    /** Size of alphabet used for plaintext and ciphertext. */
    static final int ALPHABET_SIZE = 26;

    /** Assuming that P is an integer in the range 0..25, returns the
     *  corresponding upper-case letter in the range A..Z. */
    static char toLetter(int p) {
        return (char) (p + 'A');
    }

    /** Assuming that C is an upper-case letter in the range A-Z, return the
     *  corresponding index in the range 0..25. Inverse of toLetter. */
    static int toIndex(char c) {
        return c - 'A';
    }

    /** Return my current rotational setting as an integer between 0
     *  and 25 (corresponding to letters 'A' to 'Z').  */
    int getSetting() {
        return _setting;
    }

    /** Set getSetting() to POSN.  */
    void set(int posn) {
        assert 0 <= posn && posn < ALPHABET_SIZE;
        _setting = posn;
    }

    /** Set rotor name to NAME and set permutationIndex to the
     * corresponding permutation array entry index based on NAME. */
    void setName(String name) {
        this._name = name;
        for (int i = 0; i < 12; i++) {
            if (ROTOR_SPECS[i][0].equals(this._name)) {
                _permutationIndex = i;
            }
        }
    }

    /** Return the conversion of P (an integer in the range 0..25)
     *  according to my permutation. */
    int convertForward(int p) {
        String forwardConversion = ROTOR_SPECS[_permutationIndex][1];
        int mod = Math.floorMod(getSetting() + p, 26);
        int result = toIndex(forwardConversion.charAt(mod)) - getSetting();
        return Math.floorMod(result, 26);
    }

    /** Return the conversion of E (an integer in the range 0..25)
     *  according to the inverse of my permutation. */
    int convertBackward(int e) {
        String backwardConversion = ROTOR_SPECS[_permutationIndex][2];
        int mod = Math.floorMod(getSetting() + e, 26);
        int result = toIndex(backwardConversion.charAt(mod)) - getSetting();
        return Math.floorMod(result, 26);
    }

    /** Returns true iff I am positioned to allow the rotor to my left
     *  to advance. */
    boolean atNotch() {
        String notch = ROTOR_SPECS[_permutationIndex][3];
        if (_permutationIndex > 7) { //if rotor is a reflector or fixed rotor
            return false;
        } else if (notch.length() == 1) {
            return toLetter(getSetting()) == notch.charAt(0);
        } else if (notch.length() > 1) {
            return (toLetter(getSetting()) == notch.charAt(0)
                    || toLetter(getSetting()) == notch.charAt(1));
        }
        return false;
    }

    /** Advance me one position iff it has a ratchet and can advance. */
    void advance() {
        _setting++;
        _setting = Math.floorMod(_setting, 26);
    }

    /** My current setting (index 0..25, with 0 indicating that 'A'
     *  is showing). Name (I..VII, BETA/GAMMA, B/C) and permutation
     *  index (index of the corresponding entry in the permutation
     *  data array) are declared. */
    private int _setting;
    private String _name;
    private int _permutationIndex;
}
