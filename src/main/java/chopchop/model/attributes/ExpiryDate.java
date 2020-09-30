package chopchop.model.attributes;

import static chopchop.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;
import java.time.LocalDate;


public class ExpiryDate {
    public static final String MESSAGE_CONSTRAINTS =
        "Exipry date should be in the form, yyyy-MM-dd";
    //temporary using 1 fixed date format before parser is integrated.
    public static final String VALIDATION_REGEX = "^\\d{4}-\\d{2}-\\d{2}";
    private final LocalDate date;

    /**
     * Constructs a {@code ExpiryDate}.
     *
     * @param date A valid string in the date format, yyyy-MM-dd
     */
    public ExpiryDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date);
    }

    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return this.date.toString();
    }
}
