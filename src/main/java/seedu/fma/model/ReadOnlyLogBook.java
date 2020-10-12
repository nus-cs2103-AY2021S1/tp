package seedu.fma.model;

import javafx.collections.ObservableList;
import seedu.fma.model.log.Log;

/**
 * Unmodifiable view of an log book
 */
public interface ReadOnlyLogBook {

    /**
     * Returns an unmodifiable view of the logs list.
     * This list will not contain any duplicate logs.
     */
    ObservableList<Log> getLogList();

}
