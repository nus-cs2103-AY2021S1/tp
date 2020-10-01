package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.account.entry.Entry;

/**
 * Jackson-friendly version of {@link Entry}.
 */
public class JsonAdaptedEntry {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Entry's %s field is missing!";

    protected final String type;
    protected final String description;
    protected final String amount;
    protected final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEntry} with the given entry's details.
     */
    @JsonCreator
    public JsonAdaptedEntry(@JsonProperty("type") String type, @JsonProperty("description") String description,
                            @JsonProperty("amount") String amount, @JsonProperty("tagged") List<JsonAdaptedTag> tags) {
        this.type = type;
        this.description = description;
        this.amount = amount;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts this Jackson-friendly adapted entry object into the model's {@code Entry} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted entry.
     */
    public Entry toModelType() throws IllegalValueException {
        return null;
    }

}
