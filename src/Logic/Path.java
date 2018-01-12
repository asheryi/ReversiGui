package Logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brain on 08/01/2018.
 */
public class Path {
    private Cell landing;
    private List<Cell> directions = new ArrayList<>();
    private List<Cell> stopCells = new ArrayList<>();

    Path(Cell landing_) {
        this.landing = landing_;
    }

    void insertMove(Cell direction, Cell stopCell) {
        directions.add(direction);
        stopCells.add(stopCell);
    }

    Cell getStopCell(int index) {
        return stopCells.get(index);
    }

   public Cell getLanding() {
        return landing;
    }

    Cell getDirection(int index) {
        return directions.get(index);
    }

    int numberOfEatingDirections() {
        return directions.size();
    }

    Path(Path other) {
        this.landing = new Cell(other.landing);
        for (int i = 0; i < other.directions.size(); i++) {
            this.directions.add(new Cell(other.directions.get(i)));
            this.stopCells.add(new Cell(other.stopCells.get(i)));
        }
    }

    int length() {
        int result = 1;
        for (int i = 0; i < directions.size(); i++) {
            Cell currStopCell = stopCells.get(i);
            int flipsOnThatDirection = abs(currStopCell.getColumn() - landing.getColumn());
            if (flipsOnThatDirection == 0) {
                flipsOnThatDirection = abs(currStopCell.getRow() - landing.getRow());
            }
            result += flipsOnThatDirection;
        }
        return result;
    }

    private int abs(int x) {
        return x >= 0 ? x : -x;
    }
}
