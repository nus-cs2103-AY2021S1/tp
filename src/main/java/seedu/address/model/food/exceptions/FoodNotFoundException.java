package seedu.address.model.food.exceptions;

/**
 * Signals that the operation is unable to find the specified food.
 */
public class FoodNotFoundException extends RuntimeException {

    public FoodNotFoundException() {
        super("Food not found");
    }
}
