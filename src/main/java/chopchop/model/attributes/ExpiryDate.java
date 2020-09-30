package chopchop.model.attributes;

import static chopchop.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExpiryDate {
    public static final String MESSAGE_CONSTRAINTS =
        "Exipry date should be in the form, yyyy-MM-dd";
    //temporary using 1 fixed date format before parser is integrated.
    public static final String VALIDATION_REGEX = "^\\d{4}-\\d{2}-\\d{2}";
    private final LocalDateTime date;

    /**
     * Constructs a {@code ExpiryDate}.
     *
     * @param date A valid string in the date format, yyyy-MM-dd HH:mm
     */
    public ExpiryDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDateTime.parse(date, formatter);
    }

    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public LocalDateTime getDate() {
        return this.date;
    }

}
