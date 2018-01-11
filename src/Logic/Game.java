package Logic;

import java.util.ArrayList;
import java.util.List;

/**
 * ${class}
 */
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

        List<Cell> blacks = new ArrayList<>(2);
        List<Cell> whites = new ArrayList<>(2);

        board.setCellAs(rows / 2, columns / 2 + 1, TypesOf.Color.black);
        board.setCellAs(rows / 2 + 1, columns / 2, TypesOf.Color.black);

        board.setCellAs(rows / 2, columns / 2, TypesOf.Color.white);
        board.setCellAs(rows / 2 + 1, columns / 2 + 1, TypesOf.Color.white);

        this.board = board;
        this.gameLogic = new StdGameLogic();

        this.displays[0] = display1;
        this.displays[1] = display2;

        createPlayers(blacks.size(), whites.size());

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


        PlayerInput pc1 = new ConsoleController();
        PlayerInput pc2 = new ConsoleController();
        HumanPlayer humanPlayer = new HumanPlayer(pc1, blacksCounter, TypesOf.Color.black);
        this.players[0] = humanPlayer;
        this.players[0].updateScore(2);


        this.players[1] = new HumanPlayer(pc2, whitesCounter, TypesOf.Color.white);
        this.players[1].updateScore(whites);

        this.currPlayer = 0;
    }

    /**
     * Starting the actual game .
     */
    public void start() {
        TypesOf.Color currPlayerColor = (players[currPlayer].getColor());

        List<Path> movePaths = this.gameLogic.validMovePaths(board, currPlayerColor);
        boolean currPlayerhasMoves = !movePaths.isEmpty();

        TypesOf.GameStatus gameStatus = gameLogic.currGameStatus(board, currPlayerhasMoves, currPlayerColor,
                players[0].getScore(),
                players[1].getScore());

        while (gameStatus == TypesOf.GameStatus.noOneWon || gameStatus == TypesOf.GameStatus.passTurn) {

            boolean passTurnState = gameStatus == TypesOf.GameStatus.passTurn;
            this.displays[currPlayer].show(board, movePaths, currPlayerColor, passTurnState, players[0].getScore(),
                    players[1].getScore());

            if (!passTurnState) {
                Cell move = players[currPlayer].chooseAndReturnMove(movePaths);
                Path currPathOfLandingPoint;

                while (true) {
                    if (move == null) {
                        displays[currPlayer].showError(TypesOf.Error.notIntegers);
                    } else if (isOutOfBounds(move)) {
                        displays[currPlayer].showError(TypesOf.Error.outOfBounds);
                    } else {
                        currPathOfLandingPoint = pathOfLandingPoint(movePaths, move);
                        if (currPathOfLandingPoint == null) {
                            displays[currPlayer].showError(TypesOf.Error.notValidMove);
                        } else {
                            displays[currPlayer].showMoveDone(move, currPlayerColor);
                            break;
                        }
                    }
                    move = players[currPlayer].chooseAndReturnMove(movePaths);
                }
                this.attackThose(currPathOfLandingPoint, currPlayerColor);

            }

            currPlayerColor = nextPlayer();
            movePaths = this.gameLogic.validMovePaths(board, currPlayerColor);
            currPlayerhasMoves = !movePaths.isEmpty();

            gameStatus = gameLogic.currGameStatus(board, currPlayerhasMoves, currPlayerColor, players[0].getScore(),
                    players[1].getScore());
        }

        displays[currPlayer].show(board, null, TypesOf.Color.empty, false, players[0].getScore(), players[1].getScore());
        displays[currPlayer].showEndGameStatus(gameStatus);
    }

    public void makeMove(Cell move) {
        TypesOf.Color currPlayerColor = (players[currPlayer].getColor());

        //List<Path> movePaths = this.gameLogic.validMovePaths(board, currPlayerColor);
        boolean currPlayerhasMoves = !currPlayerValidMoves.isEmpty();

        TypesOf.GameStatus gameStatus = gameLogic.currGameStatus(board, currPlayerhasMoves, currPlayerColor,
                players[0].getScore(),
                players[1].getScore());

        if (gameStatus == TypesOf.GameStatus.noOneWon || gameStatus == TypesOf.GameStatus.passTurn) {

            boolean passTurnState = gameStatus == TypesOf.GameStatus.passTurn;


            if (!passTurnState) {
                //Cell move = players[currPlayer].chooseAndReturnMove(movePaths);
                Path currPathOfLandingPoint = null;

                if (move == null) {
                    displays[currPlayer].showError(TypesOf.Error.notIntegers);
                } else if (isOutOfBounds(move)) {
                    displays[currPlayer].showError(TypesOf.Error.outOfBounds);
                    return;
                } else {
                    currPathOfLandingPoint = pathOfLandingPoint(currPlayerValidMoves, move);
                    if (currPathOfLandingPoint == null) {
                        displays[currPlayer].showError(TypesOf.Error.notValidMove);
                        return;
                    } else {
                        displays[currPlayer].showMoveDone(move, currPlayerColor);
                    }
                }
                //move = players[currPlayer].chooseAndReturnMove(movePaths);
                this.attackThose(currPathOfLandingPoint, currPlayerColor);
                this.displays[currPlayer].show(board, currPlayerValidMoves, currPlayerColor, passTurnState, players[0].getScore(),
                        players[1].getScore());

            }

            currPlayerColor = nextPlayer();

            //movePaths = this.gameLogic.validMovePaths(board, currPlayerColor);
            //currPlayerhasMoves = !movePaths.isEmpty();

            //gameStatus = gameLogic.currGameStatus(board, currPlayerhasMoves, currPlayerColor, players[0].getScore(), players[1].getScore());
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

    public final Board getBoard() {
        return this.board;
    }

    public List<Path> getValidMoves() {
        return this.currPlayerValidMoves;
    }
}
