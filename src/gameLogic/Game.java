package gameLogic;

import java.util.List;


public class Game {
    private GameLogic gameLogic;
    private Player[] players;
    private Display[] displays;
    private Board board;
    private int currPlayer;
    private List<Path> currPlayerValidMoves;

    /**
     * Game constructor.
     *
     * @param rows     - #of rows in the board .
     * @param columns  - #of columns in the board .
     * @param display1 - 1'st player display .
     * @param display2 - 2'nd display .
     */
    public Game(int rows, int columns, Display display1, Display display2, Board board) {

        players = new Player[2];
        displays = new Display[2];

        board.setCellAs(rows / 2, columns / 2 + 1, TypesOf.Color.black);
        board.setCellAs(rows / 2 + 1, columns / 2, TypesOf.Color.black);

        board.setCellAs(rows / 2, columns / 2, TypesOf.Color.white);
        board.setCellAs(rows / 2 + 1, columns / 2 + 1, TypesOf.Color.white);

        this.board = board;
        this.gameLogic = new StdGameLogic();

        this.displays[0] = display1;
        this.displays[1] = display2;

        createPlayers(2, 2);

        this.currPlayerValidMoves = gameLogic.validMovePaths(board, TypesOf.Color.black);
    }

    /**
     * create the players.
     *
     * @param blacks-int - black disks amount.
     * @param whites-int - white disks amount.
     */
    private void createPlayers(int blacks, int whites) {
        Counter blacksCounter = new Counter(blacks);
        Counter whitesCounter = new Counter(whites);


        HumanPlayer humanPlayer = new HumanPlayer(null, blacksCounter, TypesOf.Color.black);
        this.players[0] = humanPlayer;
        this.players[0].updateScore(2);


        this.players[1] = new HumanPlayer(null, whitesCounter, TypesOf.Color.white);
        this.players[1].updateScore(whites);

        this.currPlayer = 0;
    }

    public void makeMove(Cell move) {
        TypesOf.Color currPlayerColor = (players[currPlayer].getColor());

        Path currPathOfLandingPoint;
        currPathOfLandingPoint = pathOfLandingPoint(currPlayerValidMoves, move);
        if (currPathOfLandingPoint == null) {
            displays[currPlayer].showError(TypesOf.Error.notValidMove);
            return;
        }
        this.attackThose(currPathOfLandingPoint, currPlayerColor);

        currPlayerColor = nextPlayer();
        boolean currPlayerhasMoves = !currPlayerValidMoves.isEmpty();
        TypesOf.GameStatus gameStatus = gameLogic.currGameStatus(board, currPlayerhasMoves, currPlayerColor,
                players[0].getScore(),
                players[1].getScore());

        boolean passTurnState = gameStatus == TypesOf.GameStatus.passTurn;

        if (passTurnState) {
            currPlayerColor = nextPlayer();
        }

        // Show board
        this.displays[currPlayer].show(board, currPlayerValidMoves, currPlayerColor == TypesOf.Color.black ? 0 : 1
                , passTurnState, players[0].getScore(), players[1].getScore());

        // If end game :
        if (gameStatus != TypesOf.GameStatus.noOneWon && !passTurnState) {
            this.displays[currPlayer].showEndGameStatus(gameStatus);
        }
    }

    /**
     * Update the scores of the players.
     *
     * @param curr-Player  - the active player now.
     * @param other-Player - the rival of the active player.
     * @param score-int    - score to update.
     */
    private void updateScores(Player curr, Player other, int score) {
        curr.updateScore(curr.getScore() + score);
        other.updateScore(other.getScore() - score + 1);
    }

    /**
     * Switching the player .
     */
    private TypesOf.Color nextPlayer() {
        this.currPlayer = (this.currPlayer + 1) % 2;
        this.currPlayerValidMoves = gameLogic.validMovePaths(board, players[this.currPlayer].getColor());
        return players[this.currPlayer].getColor();
    }

    /**
     * @param point - to check .
     * @return true if the point is out of bounds , false otherwise.
     */
    private boolean isOutOfBounds(Cell point) {
        return point.getColumn() < 1 || point.getColumn() > board.getColumns()
                || point.getRow() < 1 || point.getRow() > board.getColumns();
    }

    /**
     * checks if the vector contains the point .
     *
     * @param paths - vector.
     * @param point - point to check.
     * @return true if the vector contains the point , false otherwise .
     */
    private Path pathOfLandingPoint(List<Path> paths, Cell point) {
        for (Path currPath : paths) {
            if (currPath.getLanding().compareTo(point) == 0) {
                return currPath;
            }
        }
        return null;
    }

    /**
     * Flips the  cells given on the board.
     *
     * @param path            - positions to flip .
     * @param currPlayerColor - the current player's color .
     */
    private void attackThose(Path path, TypesOf.Color currPlayerColor) {
        Attack attack = new Attack(path);
        while (attack.hasNext()) {
            Cell c = attack.getNext();
            this.board.setCellAs(c.getRow(), c.getColumn(), currPlayerColor);
        }
        updateScores(players[currPlayer], players[1 - currPlayer], path.length());
    }

    public List<Path> getCurrPlayerValidMoves() {
        return this.currPlayerValidMoves;
    }

    public int getCurrPlayerIndex() {
        return currPlayer;
    }
}
