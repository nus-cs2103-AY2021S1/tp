package seedu.forgetfulnus.model.flashcard;

/**
 * Tracks the order in which a FlashCard is added to the Glossary. No two unique FlashCards should have the same order
 * at any point in time. The user should never directly see or interact with this field.
 */

public class Order implements Comparable<Order> {
    public static final String MESSAGE_CONSTRAINTS = "Order cannot be less than one!";

    /**
     * Static variable to track order in which a FlashCard is added. Initialised to -1 at the start of the app, but
     * will be reassigned the moment a new FlashCard is added or deleted.
     */
    private static int nextOrderOfAddition = -1;
    private int value;
    public Order(int value) {
        this.value = value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static int getNextOrderOfAddition() {
        return nextOrderOfAddition;
    }
    public static void setNextOrderOfAddition(int value) {
        nextOrderOfAddition = value;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        return (other instanceof Order && value == ((Order) other).getValue());
    }
    @Override
    public int compareTo(Order other) {
        return value - other.value;
    }
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
