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
            "Payment dates should be valid and in the form dd/mm/yy, and should not be blank";

    private static final DateTimeFormatter INPUT_DEF = DateTimeFormatter.ofPattern("d/M/yy");
    private static final DateTimeFormatter INPUT_ALT = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public static final String TODAY = LocalDate.now().format(INPUT_DEF);

    public final LocalDate lastPaid;

    /**
     * Constructs a {@code PaymentDate}.
     *
     * @param lastPaid A valid payment date.
     */
    public PaymentDate(String lastPaid) {
        requireNonNull(lastPaid);
        checkArgument(isValidDate(lastPaid), MESSAGE_CONSTRAINTS);
        this.lastPaid = parseToDate(lastPaid);
    }

    /*
     * The {@code String} has already been validated by {@link #isValidDate(String)}.
     * We just have to find out which format it fits.
     */
    private LocalDate parseToDate(String lastPaid) {
        try {
            return LocalDate.parse(lastPaid, INPUT_DEF);
        } catch (DateTimeParseException ignored) {
            // date is in d/M/yyyy
            return LocalDate.parse(lastPaid, INPUT_ALT);
        }
    }

    /**
     * Returns true if a given string is in the correct date format.
     */
    public static boolean isValidDate(String test) {
        LocalDate testDate = null;
        for (DateTimeFormatter format : new DateTimeFormatter[] {INPUT_DEF, INPUT_ALT}) {
            try {
                testDate = LocalDate.parse(test, format);
                break;
            } catch (DateTimeParseException ignored) {
                // does not match the DateTimeFormat, try the next
            }
        }

        return testDate != null;
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
