package Logic;

public class Cell implements Comparable<Cell> {
    private int row;
    private int column;
    /**
     * Cell Constructor.
     * @param row_ - int ,row of cell.
     * @param column_ - - int , column of cell.
     */
   public Cell(int row_, int column_) {
        row = row_;
        column = column_;
    }
    /**
     *returns the cell column.
     */
  public   int getColumn() {
        return column;
    }
    /**
     *returns the cell row.
     */
  public  int getRow() {
        return row;
    }
    /**
     * Update this cell to be : row +p's row , col +p's col, and return reference to this .
     * @param p - point to add from .
     * @return reference to this cell after update(as described)
     */
    Cell CellPlusCell(Cell p) {
        return new Cell(getRow() + p.getRow(), getColumn() + p.getColumn());
    }

    Cell CellMinusCell(Cell p) {
        return new Cell(getRow() - p.getRow(), getColumn() - p.getColumn());
    }
    /**
     *Copy constructor of Cell.
     * @param p - cell to be copy.
     */
    Cell(Cell p) {
        this.row = p.row;
        this.column = p.column;
    }
    /**
     *applied to print the cell.
     */
    @Override
    public String toString() {
        return "(" + this.row + "," + this.column + ")";
    }
    /**
     * Comparing two cells row by row , column by column .
     * @param p - other cell .
     * @return true if and only if this row = to p's and same for column .
     */
    @Override
    public int compareTo(Cell p) {
        if (this.row == p.row && this.column == p.column) {
            return 0;
        } else {
            return 1;
        }
    }

}
