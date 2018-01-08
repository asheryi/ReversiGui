package Logic;

/**
 * ${class}
 */
public class TypesOf {
    public enum Color {
        empty,
        black,
        white
    }

    ;

    public enum Error {
        outOfBounds,
        notIntegers,
        notValidMove
    }

    ;

    public enum GameStatus {
        whiteWon,
        blackWon,
        noOneWon,
        tie,
        passTurn
    }

    ;
}
