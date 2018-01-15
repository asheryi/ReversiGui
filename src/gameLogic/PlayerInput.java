package gameLogic;

// Takes input from user
public interface PlayerInput {
    /**
     * @return selected landing point , aka move .
     */
    Cell getLandingPoint();

    /**
     * @return menuWin.Main menu selection .
     */
    int getMenuSelection();
}
