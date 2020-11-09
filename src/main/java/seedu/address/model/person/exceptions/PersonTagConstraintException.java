package seedu.address.model.person.exceptions;

public class PersonTagConstraintException extends RuntimeException {
    public PersonTagConstraintException() {
        super("Person can only have either prof or ta tag but not both.");
    }
}
