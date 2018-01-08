package Logic;

/**
 * ${class}
 */
// Counter class to count  , use is to pass counter to several uses for read/write purposes.
public class Counter {
    private int value;

    /**
     * @return the value .
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of counter to be value .
     *
     * @param value - to set to .
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Constructor of Counter based on initial value .
     *
     * @param initVal - init val.
     */
    Counter(int initVal) {
        value = initVal;
    }
}
