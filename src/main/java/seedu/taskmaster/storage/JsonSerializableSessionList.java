package seedu.taskmaster.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.taskmaster.commons.exceptions.IllegalValueException;
import seedu.taskmaster.model.ReadOnlyTaskmaster;
import seedu.taskmaster.model.session.Session;
import seedu.taskmaster.model.session.SessionList;
import seedu.taskmaster.model.session.SessionListManager;

/**
 * An Immutable SessionList that is serializable to JSON format.
 */
@JsonRootName(value = "sessionList")
class JsonSerializableSessionList {
    public static final String MESSAGE_DUPLICATE_SESSION = "Students list contains duplicate session name(s).";

    private final List<JsonSerializableSession> sessions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTaskmaster} with the given sessions.
     */
    @JsonCreator
    public JsonSerializableSessionList(@JsonProperty("sessions") List<JsonSerializableSession> sessions) {
        assert sessions != null;
        this.sessions.addAll(sessions);
    }

    /**
     * Converts a given {@code ReadOnlyTaskmaster} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaskmaster}.
     */
    public JsonSerializableSessionList(ReadOnlyTaskmaster source) {
        sessions.addAll(source.getSessionList().stream()
                    .map(JsonSerializableSession::new).collect(Collectors.toList()));
    }

    /**
     * Converts this student list into the model's {@code Taskmaster} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SessionList toModelType() throws IllegalValueException {
        SessionList sessionList = new SessionListManager();
        for (JsonSerializableSession jsonSerializableSession : sessions) {
            Session session = jsonSerializableSession.toModelType();

            if (sessionList.contains(session)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SESSION);
            }
            sessionList.add(session);
        }

        return sessionList;
    }

}
