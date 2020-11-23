package seedu.address.model.person.client.exception;

import seedu.address.model.exception.EntityNotFoundException;

public class ClientNotFoundException extends EntityNotFoundException {
    public ClientNotFoundException() {
        super("client");
    }
}
