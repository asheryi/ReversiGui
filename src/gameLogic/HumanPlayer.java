package gameLogic;

import java.util.List;

public class HumanPlayer extends Player {
    HumanPlayer(PlayerInput inputHandler, Counter discsCounter, TypesOf.Color color) {
        super();
        this.inputHandler = inputHandler;
        this.discsCounter = discsCounter;
        this.color = color;
    }

    int getMenuSelection() {
        return inputHandler.getMenuSelection();
    }

    @Override
    public Cell chooseAndReturnMove(List<Path> availableMovePaths) {
        return inputHandler.getLandingPoint();
    }
}
