package Logic;

/**
 * Created by Brain on 08/01/2018.
 */
public class Attack {
    private Cell curr;
    private Path path;
    private int index;

    Attack(Path path) {
        this.path = path;
        this.curr = path.getLanding();
        this.index = 0;
    }

    boolean hasNext() {
        //TODO:change 0 to loop
        if (index < path.numberOfEatingDirections()) {
            // ??? path.getStopCell(index) != 0 && path.getDirection(index) != 0 &&
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

    Cell getNext() {
        curr = curr.CellPlusCell(path.getDirection(index));
        return curr.CellMinusCell(path.getDirection(index));
    }
}
