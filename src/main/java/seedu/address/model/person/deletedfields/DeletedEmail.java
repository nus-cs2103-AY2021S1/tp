package seedu.address.model.person.deletedfields;

import seedu.address.model.person.Email;

/**
 * This class is a singleton, only one instance can exist at any one time.
 * It is a representation of the email field of a deleted person.
 */
public final class DeletedEmail extends Email {
    private static DeletedEmail instance;

    private DeletedEmail() {
        super("DELETED@Email.com");
    }

    public static DeletedEmail getInstance() {
        if (instance == null) {
            instance = new DeletedEmail();
        }
        return instance;
    }

    @Override
    public String toString() {
        return "DELETED EMAIL FIELD";
    }
}
