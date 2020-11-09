package seedu.address.model.recipe.exceptions;

/**
 * Signals that the operation will result in duplicate Ingredients.
 */
public class DuplicateIngredientException extends RuntimeException {
    public DuplicateIngredientException() {
        super("Operation would result in duplicate ingredient");
    }
}
