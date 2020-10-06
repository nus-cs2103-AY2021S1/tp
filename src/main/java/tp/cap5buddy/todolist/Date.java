package tp.cap5buddy.todolist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;

public class Date {
    private static final DateTimeFormatter validDateFormat = DateTimeFormatter.ISO_LOCAL_DATE;
    private final LocalDate value;

    public Date(String date) {
        requireNonNull(date);
        this.value = LocalDate.parse(date);
    }

    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
    public LocalDate getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Date)) {
            return false;
        }

        return value.equals(((Date) other).value);
    }

    @Override
    public String toString() {
        return value.format(validDateFormat);
    }
}
