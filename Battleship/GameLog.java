package Project3;
/**
 *
 *
 * HW-03 Challenge -- VIN Generator
 *
 * This program accepts user input, does some processing, then outputs to the console.
 *
 * @author Shivli Agrawal, lab sec 10
 *
 * @version February 3, 2022
 *
 */
public class GameLog {
    private int winningPlayer;
    private int losingPlayerHits;
    private int numTurns;
    private String boardPatternOne;
    private String boardPatternTwo;

    public GameLog(int winningPlayer, int losingPlayerHits, int numTurns, String boardPatternOne,
                   String boardPatternTwo) {
        this.winningPlayer = winningPlayer;
        this.losingPlayerHits = losingPlayerHits;
        this.numTurns = numTurns;
        this.boardPatternOne = boardPatternOne;
        this.boardPatternTwo = boardPatternTwo;
    }

    public String toString() {
        int i;
        int j;
        if (winningPlayer == 2) {
            i = 17;
        } else {
            i = losingPlayerHits;
        } if (winningPlayer == 1) {
            j = 17;
        } else {
            j = losingPlayerHits;
        }
        return String. format("Battleship Game Log:\nWinning Player: Player %d\nHits: %d - %d\nNumber " +
                        "of Turns To Win: %d\nPlayer 1 Board Pattern: %s\nPlayer 2 Board Pattern: %s\n",
                winningPlayer, j, i, numTurns,
                boardPatternOne, boardPatternTwo);
    }
}
