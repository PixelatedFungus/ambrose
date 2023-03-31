package lusaris;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * My own personal assistant.
 */
public class Main {

    public static void main(String[] args) {
        switch (args[0])
        {
            case "rent":
                if (AionUtils.checkNInputs(2, args)) {
                    Basics.calculate_rent(Float.parseFloat(args[1]));
                }
                break;
            case "wordle":
                Wordle newWordle = new Wordle();
                ArrayList<Character> characterArrayList = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
                System.out.println(newWordle.get_filteredWords());
                break;
            case "schedule":
                Aion aion = new Aion();
                aion.action(Arrays.copyOfRange(args, 1, args.length));
                break;
            default:
                System.out.println("The given command is invalid.");
        }
    }
}