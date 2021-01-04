package seedu.homerce.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.homerce.commons.util.CollectionUtil.requireAllNonNull;

import seedu.homerce.model.client.exceptions.ClientNotFoundException;
import seedu.homerce.model.util.uniquelist.UniqueList;

/**
 * A list of clients that enforces uniqueness between its elements and does not allow nulls.
 * A client is considered unique by comparing using {@code Client#isSame(Client)}. As such, adding and updating of
 * clients uses Client#isSame(Client) for equality so as to ensure that the client being added or updated is
 * unique in terms of identity in the UniqueClientList. However, the removal of a client uses Client#equals(Object) so
 * as to ensure that the client with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 */
public class UniqueClientList extends UniqueList<Client> {

    /**
     * Returns true if the list contains a client with the provided phone number.
     */
    public boolean containsPhone(Phone toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(x -> x.getPhone().equals(toCheck));
    }

    /**
     * Find the client based on Phone number. If phone does not match a client, exception is thrown.
     */
    public Client getClientByPhone(Phone phone) {
        requireAllNonNull(phone);
        for (Client client : internalList) {
            if (client.getPhone().equals(phone)) {
                return client;
            }
        }
        throw new ClientNotFoundException();
    }
}
