package seedu.address.model.recipe.exceptions;

/**
 * Signals that the operation will result in duplicate Items (Items are considered duplicates if they have the same
 * identity).
 */
public class DuplicateRecipeException extends RuntimeException {
    public DuplicateRecipeException() {
        super("Operation would result in duplicate item");
    }
}
