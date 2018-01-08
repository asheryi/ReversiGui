package Logic;

import java.util.List;

public abstract class Player {

    protected TypesOf.Color color;
    protected PlayerInput inputHandler;
    protected Counter discsCounter;

    protected Player() {
    }

    TypesOf.Color getRivalColor() {
        return color == TypesOf.Color.black ? TypesOf.Color.white : TypesOf.Color.black;
    }

    /*****************************************************************************
     *function name:updateScore
     *operation function:updates the score of the player
     *NOTE:
     *****************************************************************************/
    public void updateScore(int newScore) {
        discsCounter.setValue(newScore);
    }

    /*****************************************************************************
     *function name:getScore
     *operation function:returns the player score
     *NOTE:
     *****************************************************************************/
    int getScore() {
        return discsCounter.getValue();
    }

    /*****************************************************************************
     *function name:getColor
     *operation function:returns the player color
     *NOTE:
     *****************************************************************************/
    TypesOf.Color getColor() {
        return color;
    }


    /*****************************************************************************
     *function name:chooseAndReturnMove
     *operation function:returns a move from the player
     *NOTE:
     *****************************************************************************/
    public abstract Cell chooseAndReturnMove(List<Path> availableMovePaths);
}
