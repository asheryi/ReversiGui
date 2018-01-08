package Logic;

/**
 * ${class}
 */
public interface PlayerInput {
    /**
     * @return selected landing point , aka move .
     */
    Cell getLandingPoint();

    /**
     * @return Main menu selection .
     */
    int getMenuSelection();
}
