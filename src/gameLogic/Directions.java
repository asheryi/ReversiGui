package gameLogic;

public class Directions {
    private static final Cell left = new Cell(0, -1);
    private static final Cell right = new Cell(0, 1);
    private static final Cell up = new Cell(1, 0);
    private static final Cell down = new Cell(-1, 0);

    private static final Cell upLeft = new Cell(1, -1);
    private static final Cell downLeft = new Cell(1, 1);
    private static final Cell upRight = new Cell(-1, -1);
    private static final Cell downRight = new Cell(-1, 1);
    // all of the available directions .
    public static final Cell[] allDirections = {left, right, up, down, upLeft, upRight, downLeft, downRight};
    static final int allDirectionsSize = 8;
}
