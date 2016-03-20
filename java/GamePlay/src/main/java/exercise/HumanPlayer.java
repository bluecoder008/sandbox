package exercise;

import java.util.Scanner;

/**
 * Created by weilan_wu on 3/19/16.
 */
public class HumanPlayer implements Player {

    @Override
    public String play(String currentSequence) {
        System.out.print("\n[human player]: Please enter next letter (or 'challenge' to challenge): ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public boolean takeChallenge(String currentSequence) {
        System.out.print("\n[human player]: Please enter the challenged word: ");
        Scanner scanner = new Scanner(System.in);
        return !scanner.nextLine().isEmpty();
    }
}
