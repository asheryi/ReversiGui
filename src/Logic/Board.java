package Logic;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int rows;
    private int columns;
    private TypesOf.Color[][] grid;

    Board(int rows_, int columns_, List<Cell> blacks, List<Cell> whites) {
        this.rows = rows_;
        this.columns = columns_;
        grid = new TypesOf.Color[this.rows][this.columns];
        for (int i = 1; i <= this.rows; i++) {
            for (int j = 1; j <= this.columns; j++) {
                this.setCellAs(i, j, TypesOf.Color.empty);
            }
        }

        int size = blacks.size();

        for (int i = 0; i < size; i++) {
            this.setCellAs(blacks.get(i).getRow(), blacks.get(i).getColumn(), TypesOf.Color.black);
        }

        size = whites.size();

        for (int i = 0; i < size; i++) {
            this.setCellAs(whites.get(i).getRow(), whites.get(i).getColumn(), TypesOf.Color.white);
        }
    }

    void setCellAs(int i, int j, TypesOf.Color value) {
        this.grid[i - 1][j - 1] = value;
    }


    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public TypesOf.Color getCellValue(int i, int j) {
        return this.grid[i - 1][j - 1];
    }


    Board(Board board) {
        this.columns = board.columns;
        this.rows = board.rows;

        grid = new TypesOf.Color[this.rows][this.columns];

        for (int i = 1; i <= this.rows; i++) {
            for (int j = 1; j <= this.columns; j++) {
                this.setCellAs(i, j, board.getCellValue(i, j));
            }
        }
    }
}
