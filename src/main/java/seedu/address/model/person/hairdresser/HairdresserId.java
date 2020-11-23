package seedu.address.model.person.hairdresser;

import seedu.address.model.Id;

public class HairdresserId extends Id {
    public static final String MESSAGE_CONSTRAINTS =
            "Hairdresser ID must a positive integer.";

    /**
     * Constructs a {@code HairdresserId}.
     *
     * @param id a valid hairdresser ID.
     */
    public HairdresserId(String id) {
        super(id);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HairdresserId // instanceof handles nulls
                && this.id == ((HairdresserId) other).id); // state check
    }
}
