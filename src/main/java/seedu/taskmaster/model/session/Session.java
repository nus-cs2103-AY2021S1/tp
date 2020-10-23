package seedu.taskmaster.model.session;

import javafx.collections.ObservableList;
import seedu.taskmaster.model.record.StudentRecord;
import seedu.taskmaster.model.record.StudentRecordList;

/**
 * Represents a tutorial session conducted by a teaching assistant.
 */
public class Session {

    private static final String STRING_FORMAT = "%s|%s";

    private final SessionName sessionName;
    private final SessionDateTime sessionDateTime;
    private final StudentRecordList studentRecords;

    /**
     * A session is represented by its {@code sessionName},
     * stores date and time data and a list of student records.
     */
    public Session(SessionName sessionName,
                   SessionDateTime sessionDateTime,
                   StudentRecordList studentRecords) {
        this.sessionName = sessionName;
        this.sessionDateTime = sessionDateTime;
        this.studentRecords = studentRecords;
    }

    public SessionName getSessionName() {
        return sessionName;
    }

    public SessionDateTime getSessionDateTime() {
        return sessionDateTime;
    }

    /**
     * Returns list of student records as an unmodifiable {@code ObservableList}
     */
    public ObservableList<StudentRecord> getStudentRecords() {
        return studentRecords.asUnmodifiableObservableList();
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, sessionName.toString(), sessionDateTime);
    }

    /**
     * Returns true if both sessions have the same identity and data fields.
     * This defines a stronger notion of equality between two sessions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Session)) {
            return false;
        }
        Session otherSession = (Session) other;
        return otherSession.getSessionName().equals(getSessionName())
                && otherSession.getSessionDateTime().equals(getSessionDateTime())
                && otherSession.getStudentRecords().equals(getStudentRecords());
    }

}
