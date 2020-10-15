package seedu.fma.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.fma.model.log.Log;
import seedu.fma.model.log.UniqueLogList;
/**
 * Wraps all data at the log-book level
 * Duplicates are not allowed (by .isSameLog comparison)
 */
public class LogBook implements ReadOnlyLogBook {

    private final UniqueLogList logs;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */

    public LogBook() {
        logs = new UniqueLogList();
    }


    /**
     * Creates an LogBook using the Logs in the {@code toBeCopied}
     */
    public LogBook(ReadOnlyLogBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    // list overwrite operations

    /**
     * Replaces the contents of the log list with {@code logs}.
     * {@code logs} must not contain duplicate logs.
     */
    public void setLogs(List<Log> logs) {
        this.logs.setLogs(logs);
    }

    /**
     * Resets the existing data of this {@code LogBook} with {@code newData}.
     */
    public void resetData(ReadOnlyLogBook newData) {
        requireNonNull(newData);

        setLogs(newData.getLogList());
    }

    // log-level operations

    /**
     * Returns true if a log with the same identity as {@code log} exists in the log book.
     */
    public boolean hasLog(Log log) {
        requireNonNull(log);
        return logs.contains(log);
    }

    /**
     * Adds a log to the log book.
     * The log must not already exist in the log book.
     */
    public void addLog(Log p) {
        logs.add(p);
    }

    /**
     * Replaces the given log {@code target} in the list with {@code editedLog}.
     * {@code target} must exist in the log book.
     * The log identity of {@code editedLog} must not be the same as another existing log in the log book.
     */
    public void setLog(Log target, Log editedLog) {
        requireNonNull(editedLog);

        logs.setLog(target, editedLog);
    }

    /**
     * Removes {@code key} from this {@code LogBook}.
     * {@code key} must exist in the log book.
     */
    public void removeLog(Log key) {
        logs.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return logs.asUnmodifiableObservableList().stream()
                .reduce("", (a, b) -> a + b.toString(), (a, b) -> a + b);
    }

    @Override
    public ObservableList<Log> getLogList() {
        return logs.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LogBook // instanceof handles nulls
                && logs.equals(((LogBook) other).logs));
    }

    @Override
    public int hashCode() {
        return logs.hashCode();
    }
}
