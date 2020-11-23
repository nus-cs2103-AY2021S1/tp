package seedu.address.model.person.deletedfields;

import seedu.address.model.person.Name;

/**
 * This class is a singleton, only one instance can exist at any one time.
 * It is a representation of the name field of a deleted person.
 */
public final class DeletedName extends Name {
    private static DeletedName instance;

    private DeletedName() {
        super("DELETED NAME FIELD");
    }

    public static DeletedName getInstance() {
        if (instance == null) {
            instance = new DeletedName();
        }
        return instance;
    }
}
