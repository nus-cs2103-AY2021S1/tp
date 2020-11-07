package seedu.address.model.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Contact's telegram in the contact list.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}.
 */
public class Telegram {

    public static final String MESSAGE_CONSTRAINTS =
            "Telegram username should adhere to the following requirements: \n"
                    + "1. Start with the \"@\" symbol\n"
                    + "2. Contain at least 5 characters not including the \"@\" symbol \n"
                    + "3. Contain only alphanumeric characters and underscore \n"
                    + "4. Should not be blank";

    /** Constraints for a telegram username. */
    public static final String USERNAME_REGEX = "[a-zA-Z0-9_]{5,}";
    public static final String VALIDATION_REGEX = "@" + USERNAME_REGEX;

    /** String describing the telegram of a contact. */
    private final String telegram;

    /**
     * Creates and initialises a new Telegram object.
     *
     * @param telegram String describing a telegram.
     */
    public Telegram(String telegram) {
        requireNonNull(telegram);
        checkArgument(isValidTelegram(telegram), MESSAGE_CONSTRAINTS);
        this.telegram = telegram;
    }

    /**
     * Determines if a given string is a valid telegram.
     *
     * @param test A given String to test.
     * @return True if the given string is a valid telegram.
     */
    public static boolean isValidTelegram(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.telegram;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && this.telegram.equals(((Telegram) other).telegram)); // state check
    }

    @Override
    public int hashCode() {
        return this.telegram.hashCode();
    }

}
