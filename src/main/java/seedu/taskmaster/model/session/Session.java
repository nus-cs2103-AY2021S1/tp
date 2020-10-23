package seedu.taskmaster.model.session;

import seedu.taskmaster.model.session.record.StudentRecord;
import seedu.taskmaster.model.session.record.StudentRecordList;
import javafx.collections.ObservableList;

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
}
