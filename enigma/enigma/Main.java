// This is a SUGGESTED skeleton file.  Throw it away if you want.
package enigma;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Enigma simulator.
 *  @author Changyeon Park
 */
public final class Main {

    // WARNING: Not all methods that have code in them are complete!

    private static Rotor[] rotors = new Rotor[5]; //never changes once built

    /** Process a sequence of encryptions and decryptions, as
     *  specified in the input from the standard input.  Print the
     *  results on the standard output. Exits normally if there are
     *  no errors in the input; otherwise with code 1. */
    public static void main(String[] args) {
        boolean onFirstLine = true; //always start the program on the first line

        Machine M;
        BufferedReader input = null;
        try {
            input = new BufferedReader(
                    new InputStreamReader(new FileInputStream(args[0])));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No such file found.");
        }

        String outputFilename;
        if (args.length >= 2) {
            outputFilename = args[1];
        } else {
            outputFilename = "output.txt";
        }

        buildRotors();

        M = new Machine();

        try {
            while (true) {
                String line = input.readLine();
                if (line == null) {
                    break;
                }
                if (onFirstLine) {
                    if (!line.startsWith("*")) {
                        throw new EnigmaException("Input must start with a configuration.");
                    } else onFirstLine = false;
                }
                if (isConfigurationLine(line)) {
                    line = line.substring(2); //removes * from config line
                    configure(M, line);
                } else {
                    writeMessageLine(M.convert(standardize(line)),
                                     outputFilename);
                }
            }
        } catch (IOException excp) {
            System.err.printf("Input error: %s%n", excp.getMessage());
            System.exit(1);
        }
    }

    /** Return true iff LINE is an Enigma configuration line.
     *  Check for any invalid inputs. */
    private static boolean isConfigurationLine(String line) {
        if (!line.startsWith("*")) {
            return false;
        }
        line = line.substring(2);
        String[] configuration = line.split("\\s+");

        if (!line.matches("^[ A-z]+$")) {
            throw new EnigmaException("Configuration must only contain alphabetic characters");
        }

        if (configuration.length != 6) { //correct config length
            throw new EnigmaException("Wrong number of arguments");
        }

        List<String> reflectors = Arrays.asList("B", "C");
        List<String> nonmovingRotors = Arrays.asList("BETA", "GAMMA");
        ArrayList<String> normalRotors = new ArrayList<String>(Arrays.asList
                ("I", "II", "III", "IV", "V", "VI", "VII", "VIII"));

        //checks for valid rotors, no repeats. converts config to upper case if not already.
        if (!reflectors.contains(configuration[0].toUpperCase())
                || !nonmovingRotors.contains(configuration[1].toUpperCase())) {
            throw new EnigmaException("Invalid rotors!");
        }
        for (int i = 2; i < 5; i++) {
            if (!normalRotors.contains(configuration[i].toUpperCase())) {
                throw new EnigmaException("Invalid rotors!");
            } else normalRotors.remove(configuration[i]);
        }
        return true;
    }

    /** Configure M according to the specification given on CONFIG,
     *  which must have the format specified in the assignment. */
    private static void configure(Machine M, String config) {
        String[] configuration = config.split("\\s+");
        M.replaceRotors(rotors);
        M.nameRotors(config); //give each rotor a name & permutation index
        M.setRotors(configuration[5]); //sets each rotor to specified starting position
    }

    /** Return the result of converting LINE to all upper case,
     *  removing all blanks and tabs.  It is an error if LINE contains
     *  characters other than letters and blanks. */
    private static String standardize(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (!Character.isLetter(line.charAt(i)) && line.charAt(i) != ' ') {
                throw new EnigmaException("Message must only contain letters and blanks");
            }
        }
        line = line.toUpperCase();
        line = line.replaceAll("\\s", "");
        return line;
    }

    /** Write MSG in groups of five to out file (except that the last
     *  group may have fewer letters). */
    private static void writeMessageLine(String msg, String filename) {
        try {
            FileWriter writer = new FileWriter(filename, true);
            String outputString = ""; 
            for (int i = 0; i < msg.length(); i += 5) {
                outputString += msg.substring(i, Math.min(i + 5, msg.length()));
                if (i + 5 < msg.length()) {
                    outputString += " ";
                }
            }

            writer.write(outputString + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException when writing: " + e);
        }
    }

    /** Create all the necessary rotors. */
    private static void buildRotors() {
        rotors[0] = new Reflector();
        rotors[1] = new FixedRotor();
        for (int i = 2; i < 5; i++) {
            rotors[i] = new Rotor();
        }
    }
}
