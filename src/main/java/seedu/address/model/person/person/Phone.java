package seedu.address.model.person.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Optional;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {


    public static final String MESSAGE_CONSTRAINTS =
            "Phone should only take numbers that are at least 3 digits long.";
    public static final String VALIDATION_REGEX = "\\d{3,}";

    private static final String EMPTY_PHONE_FIELD = "-";
    public final Optional<String> value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(Optional<String> phone) {
        if (phone.isPresent()) {
            checkArgument(isValidPhone(phone.get()), MESSAGE_CONSTRAINTS);
            value = phone.get().isEmpty()
                    ? Optional.empty()
                    : phone;
        } else {
            value = phone;
        }
    }

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        if (phone.isEmpty()) {
            value = Optional.empty();
        } else {
            value = Optional.of(phone);
        }
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        if (test.isEmpty()) {
            return true;
        } else {
            return test.matches(VALIDATION_REGEX);
        }
    }

    @Override
    public String toString() {
        return value.orElse(EMPTY_PHONE_FIELD);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && value.equals(((Phone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
