package Logic;

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

    Cell getNext() {
        curr = curr.CellPlusCell(path.getDirection(index));
        return curr.CellMinusCell(path.getDirection(index));
    }
}
