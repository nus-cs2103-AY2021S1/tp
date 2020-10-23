package seedu.taskmaster.model.session;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SessionDateTime {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("h:mm a");

    private final LocalDateTime localDateTime;

    /**
     * Constructs a {@code SessionDateTime}.
     *
     * @param localDateTime The date and time of a session.
     */
    public SessionDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return localDateTime.toLocalDate().format(DATE_FORMAT) + " "
                + localDateTime.toLocalTime().format(TIME_FORMAT);
    }
}
