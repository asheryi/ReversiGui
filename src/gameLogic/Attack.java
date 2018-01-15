package gameLogic;

public class Attack {
    private Cell curr;
    private Path path;
    private int index;

    Attack(Path path) {
        this.path = path;
        this.curr = path.getLanding();
        this.index = 0;
    }
    /**
     * indicate if we can call getNext function , aka there is a next cell .
     * @return true if and only if there is a next cell .
     */
    boolean hasNext() {
        if (index < path.numberOfEatingDirections()) {
            if ((curr.CellMinusCell(path.getDirection(index))).compareTo(path.getStopCell(index)) != 0) {
                return true;
            } else {
                index++;
                curr = path.getLanding();
                if (index < path.numberOfEatingDirections()) {
                    curr = curr.CellPlusCell(path.getDirection(index));
                }
                return hasNext();
            }
        } else {
            return false;
        }
    }
    /**
     * @return the next disc position which need to be flip.
     */
    Cell getNext() {
        curr = curr.CellPlusCell(path.getDirection(index));
        return curr.CellMinusCell(path.getDirection(index));
    }
}
