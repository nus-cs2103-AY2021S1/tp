package seedu.address.model.person.hairdresser.exception;

import seedu.address.model.exception.EntityNotFoundException;

public class HairdresserNotFoundException extends EntityNotFoundException {
    public HairdresserNotFoundException() {
        super("hairdresser");
    }
}
