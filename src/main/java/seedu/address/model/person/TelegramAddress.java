package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's telegram address in the Projact.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegramAddress(String)}
 */
public class TelegramAddress {

    public static final String MESSAGE_CONSTRAINTS =
            "Telegram addresses have the following constraints: \n"
                + " 1. They can only contain alphanumeric characters and underscores with no spaces in between.\n"
                + " 2. They should be 5 - 32 characters long.\n"
                + " 3. They must start with an alphabet letter and end with an alphanumeric character.";


    public static final String VALIDATION_REGEX = "^[A-Za-z][\\w]{3,30}[A-Za-z0-9]$";

    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param telegramAddress A valid name.
     */
    public TelegramAddress(String telegramAddress) {
        requireNonNull(telegramAddress);
        checkArgument(isValidTelegramAddress(telegramAddress), MESSAGE_CONSTRAINTS);
        value = telegramAddress;
    }

    /**
     * Returns true if a given string is a valid telegramName.
     */
    public static boolean isValidTelegramAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TelegramAddress // instanceof handles nulls
                && value.equals(((TelegramAddress) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
