package exercise;

/**
 * Created by weilan_wu on 3/19/16.
 */
public interface Player {

    /**
     *  An object that extends the <code>Player</code> interface implements
     *  this method.
     *
     *  @param currentSequence The current character sequence
     *
     *  @return A letter the player responds as next move, or
     *          "challenge" if the player challenges
     *
     */
    public String play(String currentSequence);

    /**
     * This the method a player needs to call when the opponent challenges
     *
     * @param currentSequence The current character sequence
     *
     * @return true if the player wins, false if the players loses
     *
     */
    public boolean takeChallenge(String currentSequence);
}
