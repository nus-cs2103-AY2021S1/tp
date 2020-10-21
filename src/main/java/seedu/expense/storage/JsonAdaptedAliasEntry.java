package seedu.expense.storage;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.expense.commons.exceptions.IllegalValueException;
import seedu.expense.model.alias.AliasEntry;


/**
 * Jackson-friendly version of {@link AliasEntry}.
 */
class JsonAdaptedAliasEntry {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "AliasEntry's %s field is missing!";

    private final String customisedCommandWord;
    private final String defaultCommandWord;

    /**
     * Constructs a {@code JsonAdaptedAliasEntry} with the given expense details.
     */
    @JsonCreator
    public JsonAdaptedAliasEntry(@JsonProperty("customisedCommandWord") String customisedCommandWord,
                              @JsonProperty("defaultCommandWord") String defaultCommandWord) {
        this.customisedCommandWord = customisedCommandWord;
        this.defaultCommandWord = defaultCommandWord;
    }

    /**
     * Converts a given {@code Expense} into this class for Jackson use.
     */
    public JsonAdaptedAliasEntry(AliasEntry entry) {
        customisedCommandWord = entry.getKey();
        defaultCommandWord = entry.getValue();
    }

    /**
     * Converts this Jackson-friendly adapted alias entry into the model's {@code AliasEntry} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted alias entry.
     */
    public AliasEntry toModelType() { // throws IllegalValueException {
        requireNonNull(customisedCommandWord);
        requireNonNull(defaultCommandWord);

        assert !customisedCommandWord.isEmpty() : "Custom keyword cannot be empty!";
        assert !defaultCommandWord.isEmpty() : "Default keyword cannot be empty!";

        return new AliasEntry(customisedCommandWord, defaultCommandWord);
    }

}
