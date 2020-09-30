package chopchop.model.recipe.exceptions;

/**
 * Signals that the operation will result in duplicate Recipes (Recipes are considered duplicates if they have the same
 * identity and data).
 */
public class DuplicateRecipeException extends RuntimeException {
    public DuplicateRecipeException() {
        super("Operation would result in duplicate recipes");
    }
}
