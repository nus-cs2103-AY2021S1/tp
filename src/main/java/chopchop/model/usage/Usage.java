package chopchop.model.usage;

import static chopchop.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Usage {
    private final String name;
    private final LocalDateTime date;

    protected Usage(String name, LocalDateTime date) {
        requireAllNonNull(name, date);
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return this.name;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public String getPrintableDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        return this.date.format(formatter);
    }

    /**
     * Returns true if u2 is 'more later'.
     */
    public boolean isAfter(Usage u2) {
        requireNonNull(u2);
        return this.date.isAfter(u2.date);
    }

    /**
     * Returns true if u2 is 'more later'.
     */
    public boolean isAfter(LocalDateTime u2) {
        if (u2 == null) {
            return true;
        }
        return this.date.isAfter(u2);
    }

    /**
     * Returns true if u2 is earlier.
     */
    public boolean isBefore(Usage u2) {
        requireNonNull(u2);
        return this.date.isBefore(u2.date);
    }

    /**
     * Returns true if u2 is earlier.
     */
    public boolean isBefore(LocalDateTime u2) {
        if (u2 == null) {
            return true;
        }
        return this.date.isBefore(u2);
    }

    public boolean isSame(Usage u2) {
        return this.getName().equals(u2.getName());
    }
}
