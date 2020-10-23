package seedu.taskmaster.model.session;

import javafx.collections.ObservableList;

import java.util.List;

public interface SessionList extends Iterable<Session> {

    void setSessions(SessionListManager replacement);

    /**
     * Replaces the contents of this list with {@code sessions}.
     * {@code sessions} must not contain duplicate sessions.
     */
    void setSessions(List<Session> sessions);

    /**
     * Returns the session list as an unmodifiable {@code ObservableList}
     */
    ObservableList<Session> asUnmodifiableObservableList();

}
