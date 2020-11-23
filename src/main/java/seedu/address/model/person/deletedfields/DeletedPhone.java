package seedu.address.model.person.deletedfields;

import seedu.address.model.person.Phone;

/**
 * This class is a singleton, only one instance can exist at any one time.
 * It is a representation of the phone field of a deleted person.
 */
public final class DeletedPhone extends Phone {
    private static DeletedPhone instance;
    private DeletedPhone() {
        super("000");
    }

    public static DeletedPhone getInstance() {
        if (instance == null) {
            instance = new DeletedPhone();
        }
        return instance;
    }

    @Override
    public String toString() {
        return "DELETED PHONE FIELD";
    }
}
