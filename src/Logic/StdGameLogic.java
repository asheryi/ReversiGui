package Logic;

import java.util.ArrayList;
import java.util.List;

public class StdGameLogic implements GameLogic {
    StdGameLogic() {

    }

    public List<Path> validMovePaths(Board board, TypesOf.Color color) {

        List<Path> paths = new ArrayList<>();
        // Scanning all the cells to check moves.

        int rows = board.getRows();

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= board.getColumns(); j++) {
                Cell currPotentialMove = new Cell(i, j);
                Path path;
                path = theEatingPathOfMove(board, currPotentialMove, color);
                // if the move is valid it's added, otherwise the eating path is deleted .
                if (path.numberOfEatingDirections() > 0) {
                    paths.add(path);
                }
            }
        }

        return paths;
    }

    private Path theEatingPathOfMove(Board board, Cell move, TypesOf.Color color) {
        Path tmpPath = new Path(new Cell(move));
        // if there is a piece there , the move is not valid .
        if (board.getCellValue(move.getRow(), move.getColumn()) != TypesOf.Color.empty) {
            return tmpPath;
        }


        for (int i = 0; i < Directions.allDirectionsSize; i++) {
            updatePathBasedOnDirection(board, Directions.allDirections[i], move, color, tmpPath);
        }
        return tmpPath;
    }

    private void updatePathBasedOnDirection(Board board, Cell direction, Cell move, TypesOf.Color color,
                                            Path path) {
        Cell currPosition = new Cell(move);
        currPosition = currPosition.CellPlusCell(direction);

        if (isOutOfBounds(board, currPosition) ||
                board.getCellValue(currPosition.getRow(), currPosition.getColumn()) == color ||
                board.getCellValue(currPosition.getRow(), currPosition.getColumn()) == TypesOf.Color.empty) {
            return;
        }

        while (!isOutOfBounds(board, currPosition) &&
                board.getCellValue(currPosition.getRow(), currPosition.getColumn()) != color
                && board.getCellValue(
                currPosition.getRow(), currPosition.getColumn()) != TypesOf.Color.empty) {
            currPosition = currPosition.CellPlusCell(direction);
        }
        // if the last cell to the direction is empty then it's not valid , as for out of bounds cell .
        if (!isOutOfBounds(board, currPosition) &&
                board.getCellValue(currPosition.getRow(), currPosition.getColumn()) == color) {
            Cell stopCell = currPosition.CellMinusCell(direction);
            path.insertMove(new Cell(direction), new Cell(stopCell));
        }
    }

    private boolean isOutOfBounds(Board board, Cell point) {
        return point.getColumn() < 1 || point.getColumn() > board.getColumns()
                || point.getRow() < 1 || point.getRow() > board.getColumns();
    }

    public TypesOf.GameStatus currGameStatus(Board board, boolean currPlayerHasMoves, TypesOf.Color currPlayerColor, int blacks,
                                             int whites) {

        if (blacks + whites == board.getRows() * board.getColumns()) {
            if (blacks == whites) {
                return TypesOf.GameStatus.tie;
            }
            return blacks > whites ? TypesOf.GameStatus.blackWon : TypesOf.GameStatus.whiteWon;
        }

        List<Path> otherPlayerMoves = validMovePaths(board, currPlayerColor == TypesOf.Color.black ? TypesOf.Color.white
                : TypesOf.Color.black);

        TypesOf.GameStatus gameStatus = TypesOf.GameStatus.noOneWon;
        // if the previous player had nothing to do .
        if (otherPlayerMoves.isEmpty()) {
            if (!currPlayerHasMoves) {
                // if this player either has nothing to do , it's a tie .
                if (blacks == whites) {
                    gameStatus = TypesOf.GameStatus.tie;
                } else {
                    gameStatus = blacks > whites ? TypesOf.GameStatus.blackWon : TypesOf.GameStatus.whiteWon;
                }
            }
        } else if (!currPlayerHasMoves) {
            gameStatus = TypesOf.GameStatus.passTurn;
        }
        return gameStatus;
    }
}
