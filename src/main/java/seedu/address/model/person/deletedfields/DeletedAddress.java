package seedu.address.model.person.deletedfields;

import seedu.address.model.person.client.Address;

/**
 * This class is a singleton, only one instance can exist at any one time.
 * It is a representation of the address field of a deleted client.
 */
public final class DeletedAddress extends Address {
    private static DeletedAddress instance;
    private DeletedAddress() {
        super("DELETED ADDRESS FIELD");
    }

    public static DeletedAddress getInstance() {
        if (instance == null) {
            instance = new DeletedAddress();
        }
        return instance;
    }
}
