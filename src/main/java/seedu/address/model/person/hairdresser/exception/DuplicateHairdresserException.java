package seedu.address.model.person.hairdresser.exception;

import seedu.address.model.exception.DuplicateEntityException;

public class DuplicateHairdresserException extends DuplicateEntityException {

    public DuplicateHairdresserException() {
        super("hairdressers");
    }
}
