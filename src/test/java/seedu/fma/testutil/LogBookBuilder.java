package seedu.fma.testutil;

import seedu.fma.model.LogBook;
import seedu.fma.model.log.Log;

/**
 * A utility class to help with building LogBook objects.
 * Example usage: <br>
 *     {@code LogBook ab = new LogBookBuilder().withLog("John", "Doe").build();}
 */
public class LogBookBuilder {

    private LogBook logBook;

    public LogBookBuilder() {
        logBook = new LogBook();
    }

    public LogBookBuilder(LogBook logBook) {
        this.logBook = logBook;
    }

    /**
     * Adds a new {@code Log} to the {@code LogBook} that we are building.
     */
    public LogBookBuilder withLog(Log log) {
        logBook.addLog(log);
        return this;
    }

    public LogBook build() {
        return logBook;
    }
}
