package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.person.hairdresser.HairdresserId;

/**
 * Tests that a {@code Hairdresser}'s {@code id} matches the given id.
 */
public class RecordContainsHairdresserIdPredicate implements Predicate<Hairdresser> {
    private final HairdresserId id;

    public RecordContainsHairdresserIdPredicate(HairdresserId id) {
        this.id = id;
    }

    @Override
    public boolean test(Hairdresser hairdresser) {
        return hairdresser.getId().equals(id);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecordContainsHairdresserIdPredicate // instanceof handles nulls
                && this.id.equals(((RecordContainsHairdresserIdPredicate) other).id)); // state check
    }
}
