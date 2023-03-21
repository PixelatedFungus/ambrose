package lusaris;

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
                newWordle.invalidLetter('a');
                newWordle.invalidLetter('u');
                newWordle.invalidLetter('d');
                newWordle.characterExists('i', 3);
                newWordle.invalidLetter('o');
                newWordle.characterExists('r', 0);
                newWordle.invalidLetter('e');
                newWordle.characterAt('i', 2);
                newWordle.characterExists('g', 3);
                newWordle.characterExists('n', 4);
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