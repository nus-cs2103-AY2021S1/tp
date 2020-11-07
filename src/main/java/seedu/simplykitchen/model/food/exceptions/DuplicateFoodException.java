package seedu.simplykitchen.model.food.exceptions;

/**
 * Signals that the operation will result in duplicate Foods (Food items are considered duplicates if they have the same
 * identity).
 */
public class DuplicateFoodException extends RuntimeException {
    public DuplicateFoodException() {
        super("Operation would result in duplicate food items");
    }
}
