package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.person.client.Client;
import seedu.address.model.person.client.ClientId;

/**
 * Tests that a {@code Client}'s {@code id} matches the given id.
 */
public class RecordContainsClientIdPredicate implements Predicate<Client> {
    private final ClientId id;

    public RecordContainsClientIdPredicate(ClientId id) {
        this.id = id;
    }

    @Override
    public boolean test(Client client) {
        return client.getId().equals(id);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecordContainsClientIdPredicate // instanceof handles nulls
                && this.id.equals(((RecordContainsClientIdPredicate) other).id)); // state check
    }
}
