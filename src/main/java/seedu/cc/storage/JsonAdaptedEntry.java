package seedu.cc.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.cc.commons.exceptions.IllegalValueException;
import seedu.cc.model.account.entry.Entry;

/**
 * Jackson-friendly version of {@link Entry}.
 */
public class JsonAdaptedEntry {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Entry's %s field is missing!";

    //protected final String type;
    protected final String description;
    protected final String amount;
    protected final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEntry} with the given entry's details.
     */
    @JsonCreator
    public JsonAdaptedEntry(@JsonProperty("description") String description,
                            @JsonProperty("amount") String amount, @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        //this.type = type;
        this.description = description;
        this.amount = amount;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Entry} into this class for Jackson use.
     */
    public JsonAdaptedEntry(Entry source) {
        description = source.getDescription().toString();
        amount = source.getAmount().toString();
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
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
