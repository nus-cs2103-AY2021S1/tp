package seedu.address.model.exception;

public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String type) {
        super("Operation would result in duplicate " + type);
    }

    public DuplicateEntityException() {
        super("Operation would result in duplicate entities");
    }
}
