package Logic;

import java.util.List;

/**
 * Created by Brain on 08/01/2018.
 */
public class Console implements Display {
    @Override
    public void show(Board board, List<Path> moves, final TypesOf.Color currPlayerColor, boolean passTurn,
                     int blacks,
                     int whites) {
        // counting how many tiny lines needed in order to print the broken line .
        int count = 2;

        System.out.print(" | ");
        for (int i = 1; i <= board.getColumns(); i++) {
            System.out.print(i + " | ");
            count += 4;
        }

        printBrokenLine(count);

        for (int i = 1; i <= board.getRows(); i++) {
            System.out.print(i + "|");
            for (int j = 1; j <= board.getColumns(); j++) {
                System.out.print(" " + colorAsCharacter(board.getCellValue(i, j)) + ' ' + '|');
            }
            printBrokenLine(count);
        }

        System.out.println();

        System.out.println("Black Score: " + blacks + "        White Score: " + whites);

        if (moves == null) {
            return;
        }

        char color = (currPlayerColor == TypesOf.Color.black ? 'X' : 'O');

        System.out.println(color + ",it's your move .");


        if (passTurn) {
            System.out.println("No possible moves. Play passes to the other player.");
        } else {
            System.out.println("Your possible moves are : ");
            if (!moves.isEmpty()) {
                System.out.println("(" + moves.get(0).getLanding().getRow() + "," + moves.get(0).getLanding().getColumn() + ")");
            }
            for (int i = 1; i < moves.size(); i++) {
                System.out.print(",(" + moves.get(i).getLanding().getRow() + "," + moves.get(i).getLanding().getColumn() + ")");
            }

            System.out.println();

            System.out.println("Please enter your move : row,col (row input from 1 to " + board.getRows() + ", col from 1 to "
                    + board.getColumns() + ") :");

        }
    }

    private void printBrokenLine(int count) {
        System.out.println("  ");
        for (int i = 1; i <= count; i++) {
            System.out.print('-');
        }
        System.out.println();
    }

    Console() {
    }

    @Override
    public void showError(TypesOf.Error errorType) {
        System.out.println("Error !");
        if (errorType == TypesOf.Error.outOfBounds || errorType == TypesOf.Error.notIntegers) {
            System.out.println("Please enter integers in the right format and boundaries.");
        } else {
            System.out.println("Please enter a move you can make on the board , the moves are printed for you (:");
        }
        System.out.println("Try again ...");

    }

    private char colorAsCharacter(TypesOf.Color color) {
        switch (color) {
            case white:
                return 'o';
            case black:
                return 'x';
            case empty:
                return ' ';
            default:
                return ' ';
        }
    }

    @Override
    public void showEndGameStatus(TypesOf.GameStatus gameStatus) {
        if (gameStatus == TypesOf.GameStatus.tie) {
            System.out.println("It's a tie ... never say never , try again and this time win ! ");
        } else if (gameStatus == TypesOf.GameStatus.blackWon) {
            System.out.println("X : you won , I knew it (:");
        } else {
            System.out.println("O : you won , I knew it (:");
        }
    }

    @Override
    public void showMoveDone(Cell cell, TypesOf.Color playerColor) {
        char whoDidAMove = colorAsCharacter(playerColor) == 'x' ? 'X' : 'O';
        System.out.println(whoDidAMove + " played : " + cell.toString());
    }
}
