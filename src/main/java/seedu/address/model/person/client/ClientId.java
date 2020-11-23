package seedu.address.model.person.client;

import seedu.address.model.Id;

public class ClientId extends Id {
    public static final String MESSAGE_CONSTRAINTS =
            "Client ID must a positive integer.";

    /**
     * Constructs a {@code ClientId}.
     *
     * @param id a valid client ID.
     */
    public ClientId(String id) {
        super(id);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientId // instanceof handles nulls
                && this.id == ((ClientId) other).id); // state check
    }

}
