package seedu.address.logic.parser.task;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.DateTimeDue;
import seedu.address.model.task.Description;

/**
 * Contains utility methods used for parsing strings in the various task-related Parser classes.
 */
public class TaskParserUtil {

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given description is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code Optional<String> optionalDueAt} into a {@code DateTimeDue}.
     * {@code optionalDueAt} can be empty but must not be null.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given date-time string is invalid.
     */
    public static DateTimeDue parseDateTimeDue(Optional<String> optionalDueAt) throws ParseException {
        requireNonNull(optionalDueAt);
        boolean isValid = optionalDueAt
                .map(String::trim)
                .map(DateTimeDue::isValidDateTimeDue)
                .orElse(true); // empty Optional considered valid

        if (!isValid) {
            throw new ParseException(DateTimeDue.MESSAGE_CONSTRAINTS);
        }

        return new DateTimeDue(optionalDueAt);
    }
}
