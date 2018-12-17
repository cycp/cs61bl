// This is a SUGGESTED skeleton file.  Throw it away if you don't use it.
package enigma;

/** Class that represents a complete enigma machine.
 *  @author Changyeon Park
 */
class Machine {

    // This needs other methods or constructors.


    //Instance variable machineRotors is an array of 5 rotors.
    private Rotor[] machineRotors = new Rotor[5];

    /** Set my rotors to (from left to right) ROTORS.  Initially, the rotor
     *  settings are all 'A'. */
    void replaceRotors(Rotor[] rotors) {
        machineRotors = rotors;
    }

    /** Set my rotors according to SETTING, which must be a string of four
     *  upper-case letters. The first letter refers to the leftmost
     *  rotor setting. */
    void setRotors(String setting) {
        if (setting.length() != 4) {
            throw new EnigmaException("Setting must be a string of 4 letters");
        }
        setting = setting.toUpperCase();
        for (int i = 1, j = 0; i < 5; i++, j++) {
            int posn = machineRotors[i].toIndex(setting.charAt(j));
            machineRotors[i].set(posn);
        }
    }

    /** Set my rotors according to configuration line. Assume rotor names are
     *  valid and converts them to upper case if they weren't already.
     *  Set permutation index to the index of the appropriate entry
     *  in PermutationData.java. */
    void nameRotors(String config) {
        String[] names = (config.substring(0, config.length() - 4)).split("\\s+");

        for (int i = 0; i < 5; i++) {
            machineRotors[i].setName(names[i].toUpperCase());
        }
    }

    /** Returns the encoding/decoding of MSG, updating the state of
     *  the rotors accordingly. */
    String convert(String msg) {
        String result = "";
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            if (machineRotors[3].atNotch()) {
                machineRotors[2].advance();
                machineRotors[3].advance();
            } else if (machineRotors[4].atNotch()) {
                machineRotors[3].advance();
            }
            machineRotors[4].advance();

            for (int j = 4; j >= 0; j--) {
                c = Rotor.toLetter(machineRotors[j].convertForward(Rotor.toIndex(c)));
            }

                //start a 1 because reflector doesn't convert backward
            for (int k = 1; k < 5; k++) {
                c = Rotor.toLetter(machineRotors[k].convertBackward(Rotor.toIndex(c)));
            }
            result += result.valueOf(c);

        }
        return result;
    }
}

