package seedu.fma.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.fma.commons.exceptions.IllegalValueException;
import seedu.fma.model.LogBook;
import seedu.fma.model.ReadOnlyLogBook;
import seedu.fma.model.log.Log;

/**
 * An Immutable LogBook that is serializable to JSON format.
 */
@JsonRootName(value = "logbook")
class JsonSerializableLogBook {

    public static final String MESSAGE_DUPLICATE_LOG = "Log list contains duplicate log(s).";

    private final List<JsonAdaptedLog> logs = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableLogBook} with the given logs.
     */
    @JsonCreator
    public JsonSerializableLogBook(@JsonProperty("logs") List<JsonAdaptedLog> logs) {
        this.logs.addAll(logs);
    }

    /**
     * Converts a given {@code ReadOnlyLogBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableLogBook}.
     */
    public JsonSerializableLogBook(ReadOnlyLogBook source) {
        logs.addAll(source.getLogList().stream().map(JsonAdaptedLog::new).collect(Collectors.toList()));
    }

    /**
     * Converts this log book into the model's {@code LogBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public LogBook toModelType() throws IllegalValueException {
        LogBook logBook = new LogBook();
        for (JsonAdaptedLog jsonAdaptedLog : logs) {
            Log log = jsonAdaptedLog.toModelType();
            if (logBook.hasLog(log)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_LOG);
            }
            logBook.addLog(log);
        }
        return logBook;
    }

}
