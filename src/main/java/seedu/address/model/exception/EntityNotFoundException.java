package seedu.address.model.exception;

public class EntityNotFoundException extends RuntimeException {


    public EntityNotFoundException(String type) {
        super("The " + type + " could not be found!");
    }

    public EntityNotFoundException() {
        super("The entity could not be found");
    }
}
