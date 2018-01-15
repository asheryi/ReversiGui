package gameLogic;


import java.util.List;

public interface GameLogic {
    /**
     * Return valid moves , each move with it's path - it's trajectories to "eat" on the board .
     *
     * @param board - the board to check the player in the given color's moves.
     * @param color - of the player .
     * @return all of the valid moves available for the player which color was given .
     */
    List<Path> validMovePaths(Board board, TypesOf.Color color);

    /**
     * Returns the current Game Status which could be : noValidMoves , whitwWon , blackWon , passTurn(the player cannot
     * play but the other one can play ) and tie .
     *
     * @param currPlayerColor .
     * @param blacks          - how many blacks are there on the board .
     * @param whites          - how many whites are there on the board .
     * @return the curr game status as described .
     */
    TypesOf.GameStatus currGameStatus(Board board, boolean currPlayerhasMoves, TypesOf.Color currPlayerColor, int blacks,
                                      int whites);
}
