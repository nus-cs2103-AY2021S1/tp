package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's telegram in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}
 */
public class Telegram {

    public static final String MESSAGE_CONSTRAINTS =
            "Accepted characters: A-z (case-insensitive), 0-9 and underscores. Length: 5-32 characters.";

    /*
     * TODO: update regex
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9_-]{5,32}$";

    public final String telegram;

    /**
     * Constructs a {@code Telegram}.
     *
     * @param telegram A valid telegram.
     */
    public Telegram(String telegram) {
        requireNonNull(telegram);
        checkArgument(isValidTelegram(telegram), MESSAGE_CONSTRAINTS);
        this.telegram = telegram;
    }

    /**
     * Returns true if a given string is a valid telegram.
     */
    public static boolean isValidTelegram(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return "@" + telegram;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && telegram.equals(((Telegram) other).telegram)); // state check
    }

    @Override
    public int hashCode() {
        return telegram.hashCode();
    }

}
