package Logic;

/**
 * Created by Brain on 08/01/2018.
 */
public class Cell implements Comparable<Cell> {
    private int row;
    private int column;

    Cell(int row_, int column_) {
        row = row_;
        column = column_;
    }

    int getColumn() {
        return column;
    }

    int getRow() {
        return row;
    }

    Cell CellPlusCell(Cell p) {
        return new Cell(getRow() + p.getRow(), getColumn() + p.getColumn());
    }

    Cell CellMinusCell(Cell p) {
        return new Cell(getRow() - p.getRow(), getColumn() - p.getColumn());
    }

    Cell(Cell p) {
        this.row = p.row;
        this.column = p.column;
    }

    @Override
    public String toString() {
        return "(" + this.row + "," + this.column + ")";
    }

    @Override
    public int compareTo(Cell p) {
        if (this.row == p.row && this.column == p.column) {
            return 0;
        } else {
            return 1;
        }
    }

}
