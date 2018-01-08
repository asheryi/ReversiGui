package Logic;

import java.util.Scanner;

/**
 * Created by Brain on 08/01/2018.
 */
public class ConsoleController implements PlayerInput {
    private Scanner reader;

    ConsoleController() {
        reader = new Scanner(System.in);
    }

    public Cell getLandingPoint() {
        int row, col;
        row = reader.nextInt();
        col = reader.nextInt();
        return new Cell(row, col);
    }

    public int getMenuSelection() {
        int selection;
        selection = reader.nextInt();
        return selection;
    }
}
