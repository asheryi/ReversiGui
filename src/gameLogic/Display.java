package gameLogic;

import java.util.List;

public interface Display {
    /**
     * Shows the current status of the board and moves .
     *
     * @param board           - board to display
     * @param moves           - moves to show on the display .
     * @param currPlayer        - the current player's index .
     * @param passTurn        - true if the situation is that the current player have no moves and the other one does have .
     */
    void show(Board board, List<Path> moves, int currPlayer, boolean passTurn, int blacks, int whites);

    /**
     * Shows the error given.
     *
     * @param errorType - enum of error type .
     */
    void showError(TypesOf.Error errorType);

    /**
     * Displays the game status given .
     *
     * @param gameStatus
     */
    void showEndGameStatus(TypesOf.GameStatus gameStatus);

    /**
     * Show this move in the display .(this color made the turn).
     *
     * @param cell
     * @param playerColor
     */
    void showMoveDone(Cell cell, TypesOf.Color playerColor);
}
