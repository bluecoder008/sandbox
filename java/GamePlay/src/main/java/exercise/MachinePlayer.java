package exercise;

import java.util.Random;

/**
 * Created by weilan_wu on 3/19/16.
 */
public class MachinePlayer implements Player {

    public MachinePlayer() {
        randomGenerator = new Random();
    }

    @Override
    public String play(String currentSequence) {

        if (shouldChallenge(currentSequence)) {
            return Game.CHALLENGE;
        }

        for(char ch='a'; ch <= 'z'; ch++) {

            String newSequence = currentSequence + ch;
            // If a letter gives a new sequence which is not a word but also
            // a prefix exists in the Dictionary (backed by a Trie), return it
            if (!Dictionary.getInstance().checkWord(newSequence) &&
                  Dictionary.getInstance().checkPrefix(newSequence) ) {
                String returnStr = new String();
                returnStr += ch;
                return returnStr;
            }
        }
        // If no letter is available for next move, challenge!
        return Game.CHALLENGE;
    }

    @Override
    public boolean takeChallenge(String currentSequence) {
        return Dictionary.getInstance().getWord(currentSequence) != null;
    }

    // Randomly makes the computer to challenge -- We assume 30% of time computer attempts to challenge
    private static final int CHALLENGE_PERCENTAGE = 30;
    private Random randomGenerator;
    private boolean shouldChallenge(String currentString) {
        return ( !currentString.isEmpty() && (randomGenerator.nextInt(100) <= CHALLENGE_PERCENTAGE));
    }
}
