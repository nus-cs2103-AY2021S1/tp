package chopchop.model.usage;

import static chopchop.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;

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

    public boolean isAfter(Usage u2) {
        return this.date.isAfter(u2.date);
    }

    public boolean isAfter(LocalDateTime u2) {
        return this.date.isAfter(u2);
    }

    public boolean isBefore(Usage u2) {
        return this.date.isBefore(u2.date);
    }

    public boolean isBefore(LocalDateTime u2) {
        return this.date.isBefore(u2);
    }
}
