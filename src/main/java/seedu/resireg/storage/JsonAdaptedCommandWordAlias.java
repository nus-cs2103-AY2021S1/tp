package seedu.resireg.storage;

import static seedu.resireg.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.model.alias.Alias;
import seedu.resireg.model.alias.CommandWord;
import seedu.resireg.model.alias.CommandWordAlias;
import seedu.resireg.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedCommandWordAlias {

    private final String commandWord;
    private final String alias;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedCommandWordAlias(@JsonProperty("commandWord") String commandWord,
                          @JsonProperty("alias") String alias) {
        this.commandWord = commandWord;
        this.alias = alias;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedCommandWordAlias(CommandWordAlias source) {
        this.commandWord = source.getCommandWord().toString();
        this.alias = source.getAlias().toString();
    }

    /**
     * Converts this Jackson-friendly adapted CommandWordAlias object into the model's {@code CommandWordAlias} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted command word alias.
     */
    public CommandWordAlias toModelType() throws IllegalValueException {
        if (commandWord == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                CommandWord.class.getSimpleName()));
        }
        if (!CommandWord.isValidCommandWord(commandWord)) {
            throw new IllegalValueException(CommandWord.MESSAGE_CONSTRAINTS);
        }
        final CommandWord modelCommandWord = new CommandWord(commandWord);

        if (alias == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Alias.class.getSimpleName()));
        }
        if (!Alias.isValidAlias(alias)) {
            throw new IllegalValueException(Alias.MESSAGE_CONSTRAINTS);
        }
        final Alias modelAlias = new Alias(alias);


        return new CommandWordAlias(modelCommandWord, modelAlias);
    }

}
