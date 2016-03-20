package exercise;

/**
 * Created by weilan_wu on 3/19/16.
 */
public class Game {

    public static final String CHALLENGE = "challenge";

    /**
     * Constructor
     */
    public Game() {
        machine = new MachinePlayer();
        human = new HumanPlayer();
        players = new Player[2];

        // Trigger data loading from disk
        Dictionary.getInstance();

        // Randomly pick the first player
        long currentMS = System.currentTimeMillis();
        if (currentMS % 2 == 0) {
            players[0] = machine;
            players[1] = human;
        } else {
            players[1] = machine;
            players[0] = human;
        }
        System.out.println();
        System.out.println("player[0] : " + players[0]);
        System.out.println("player[1] : " + players[1]);
        System.out.println();
    }

    /**
     * The game executing in a loop, each iteration, the players take turn to play,
     * until one wins and the player who wins is returned.
     *
     * @return The winner player
     */
    public Player run() {

        String currentString = new String();
        int n = 0;
        while (true) {
            System.out.println("current string: '" + currentString + "'");
            Player currentPlayer = players[n % 2];
            Player opponant = players[(n + 1) % 2];
            String response = currentPlayer.play(currentString);
            System.out.println("player[" + (n%2) + "]: " + response);
            if (CHALLENGE.equals(response)) {
                if (opponant.takeChallenge(currentString)) {
                    return opponant;
                } else {
                    return currentPlayer;
                }
            } else {
                currentString += response;
                if (Dictionary.getInstance().checkWord(currentString)) {
                    return opponant;
                }
            }
            n++;
        }
    }

    public static void main(String[] args) {
        Game newGame = new Game();
        Player winner = newGame.run();
        System.out.println("Game ended with " + winner + " won.");
    }

    private Player machine;
    private Player human;
    private Player players[];
}
