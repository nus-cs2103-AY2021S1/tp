package seedu.stock.model.stock.exceptions;

/**
 * Signals that the operation will result in duplicate Stock (Stocks are considered duplicates if they have the same
 * identity).
 */
public class DuplicateStockException extends RuntimeException {
    public DuplicateStockException() {
        super("Operation would result in duplicate stock.");
    }
}
