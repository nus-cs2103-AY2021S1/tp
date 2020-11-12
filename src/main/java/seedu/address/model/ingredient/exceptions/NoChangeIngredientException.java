package seedu.address.model.ingredient.exceptions;

/**
 * Signals that the operation will not change any amounts (Ingredients are considered duplicates if they have the same
 * name and amount).
 */
public class NoChangeIngredientException extends RuntimeException {
    public NoChangeIngredientException() {
        super("The operation does not change any of the existing amounts");
    }
}
