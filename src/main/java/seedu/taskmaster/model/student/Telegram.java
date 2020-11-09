package seedu.taskmaster.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.taskmaster.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's telegram username in the student list.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}
 */
public class Telegram {


    public static final String MESSAGE_CONSTRAINTS =
            "Telegram numbers should consist of only "
            + "alphanumeric characters and underscores with length between 5 and 32 characters inclusive.";
    public static final String VALIDATION_REGEX = "[a-zA-Z0-9_]{5,32}";
    public final String value;

    /**
     * Constructs a {@code Telegram}.
     *
     * @param telegramAccount A valid telegram account.
     */
    public Telegram(String telegramAccount) {
        requireNonNull(telegramAccount);
        checkArgument(isValidTelegram(telegramAccount), MESSAGE_CONSTRAINTS);
        value = telegramAccount;
    }

    /**
     * Returns true if a given string is a valid telegram account.
     */
    public static boolean isValidTelegram(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && value.equals(((Telegram) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
