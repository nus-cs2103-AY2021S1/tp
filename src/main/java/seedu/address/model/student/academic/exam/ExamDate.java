package seedu.address.model.student.academic.exam;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the date of an exam.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class ExamDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Exam dates should be in the form dd/mm/yy, and should not be blank";

    public static final String VALIDATION_REGEX = "(\\d{1,2})(\\/)(\\d{1,2})(\\/)(\\d{2}|\\d{4})";

    private static final DateTimeFormatter INPUT_DEF = DateTimeFormatter.ofPattern("d/M/yy");
    private static final DateTimeFormatter INPUT_ALT = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public final LocalDate examDate;

    /**
     * Constructs a {@code ExamDate}.
     *
     * @param date A valid exam date.
     */
    public ExamDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.examDate = parseToDate(date);
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
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

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

    public String convertExamDateToUserInputString() {
        return this.examDate.format(INPUT_ALT);
    }

    @Override
    public String toString() {
        return examDate.format(OUTPUT);
    }

    @Override
    public boolean equals(Object obj) {
        return (this == obj) // short circuit if same object
                || (obj instanceof ExamDate) // instanceof handles nulls
                && examDate.equals(((ExamDate) obj).examDate); // state check
    }

    @Override
    public int hashCode() {
        return examDate.hashCode();
    }
}
