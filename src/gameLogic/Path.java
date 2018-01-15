package gameLogic;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private Cell landing;
    private List<Cell> directions = new ArrayList<>();
    private List<Cell> stopCells = new ArrayList<>();
    /*****************************************************************************
     *function name:Path
     *operation function:constructor gets start point,the position of the the first
     * disc, and the direction the disc attack.
     *NOTE:
     *****************************************************************************/
    Path(Cell landing_) {
        this.landing = landing_;
    }
    /*****************************************************************************
     *function name:setLanding
     *operation function:set the landing point.
     *NOTE:
     *****************************************************************************/
    void insertMove(Cell direction, Cell stopCell) {
        directions.add(direction);
        stopCells.add(stopCell);
    }
    /*****************************************************************************
     *function name:getStartAttackFrom
     *operation function:get the point from where to start the attack.
     *NOTE:
     *****************************************************************************/
    Cell getStopCell(int index) {
        return stopCells.get(index);
    }
    /*****************************************************************************
     *function name:getLanding
     *operation function:get the landing point.
     *NOTE:
     *****************************************************************************/
   public Cell getLanding() {
        return landing;
    }
    /*****************************************************************************
     *function name:getDirection
     *operation function:get the direction the disc attack.
     *NOTE:
     *****************************************************************************/
    Cell getDirection(int index) {
        return directions.get(index);
    }
    /*
     * @return the amounts of eating directions.
     */
    int numberOfEatingDirections() {
        return directions.size();
    }
    /*
        * Copy constructor.
        * @param other- Path-to be copy.
        */
    Path(Path other) {
        this.landing = new Cell(other.landing);
        for (int i = 0; i < other.directions.size(); i++) {
            this.directions.add(new Cell(other.directions.get(i)));
            this.stopCells.add(new Cell(other.stopCells.get(i)));
        }
    }
    /*
     * @return the amount of disks to eat.
     */
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
