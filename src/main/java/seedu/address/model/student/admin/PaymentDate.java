package seedu.address.model.student.admin;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the last time a Student paid his tuition fees.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class PaymentDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Payment dates should be in the form dd/mm/yy, and should not be blank";

    public static final String VALIDATION_REGEX = "(\\d{1,2})(\\/)(\\d{1,2})(\\/)(\\d{2}|\\d{4})";

    private static final DateTimeFormatter INPUT_DEF = DateTimeFormatter.ofPattern("d/M/yy");
    private static final DateTimeFormatter INPUT_ALT = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("dd/MMM/yyyy");

    public final LocalDate lastPaid;

    /**
     * Constructs a {@code PaymentDate}.
     *
     * @param lastPaid A valid payment date.
     */
    public PaymentDate(String lastPaid) {
        requireNonNull(lastPaid);
        checkArgument(isValidDate(lastPaid), MESSAGE_CONSTRAINTS);
        this.lastPaid = parse(lastPaid);
    }

    private static LocalDate parse(String test) {
        try {
            return LocalDate.parse(test, INPUT_DEF);
        } catch (DateTimeParseException ignored) {
            // format failed, use the other format.
        }

        try {
            return LocalDate.parse(test, INPUT_ALT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns true if a given string is in the correct date format.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String convertPaymentDateToUserInputString() {
        return this.lastPaid.format(INPUT_ALT);
    }

    @Override
    public String toString() {
        return lastPaid.format(OUTPUT);
    }

    @Override
    public boolean equals(Object obj) {
        return (this == obj) // short circuit if same object
                || (obj instanceof PaymentDate) // instanceof handles nulls
                && lastPaid.equals(((PaymentDate) obj).lastPaid); // state check
    }

    @Override
    public int hashCode() {
        return lastPaid.hashCode();
    }

}
