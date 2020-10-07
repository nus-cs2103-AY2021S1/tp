package jimmy.mcgymmy.model.food.exceptions;

/**
 * Signals that the operation will result in duplicate Food items (Food items are considered duplicates if they have the same
 * name and nutrition content).
 */
public class DuplicateFoodException extends RuntimeException {
    public DuplicateFoodException() {
        super("Operation would result in duplicate food items");
    }
}
