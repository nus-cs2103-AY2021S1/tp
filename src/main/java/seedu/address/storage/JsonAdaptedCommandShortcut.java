package seedu.address.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commandshortcut.CommandShortcut;
import seedu.address.logic.commandshortcut.CommandWord;

public class JsonAdaptedCommandShortcut {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Shortcut Set's %s field is missing!";

    private final String commandWord;
    private final List<String> shortcuts;

    /**
     * Constructs a {@code JsonAdaptedCommandShortcut} with the given {@code String and @code List<String>}.
     */
    @JsonCreator
    public JsonAdaptedCommandShortcut(@JsonProperty("word") String commandWord,
                                      @JsonProperty("shortcuts") List<String> shortcuts) {
        this.commandWord = commandWord;
        this.shortcuts = shortcuts;
    }

    /**
     * Converts a given {@code CommandShortcut} into this class for Jackson use.
     */
    public JsonAdaptedCommandShortcut(CommandShortcut source) {
        this.commandWord = source.getCommandWord().getDefaultWord();
        this.shortcuts = source.getAllShortcuts();
    }

    /**
     * Converts this Jackson-friendly adapted commandAlias object into the model's {@code CommandShortcut} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted commandShortcut.
     */
    public CommandShortcut toModelType() throws IllegalValueException {
        if (commandWord == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Command Word"));
        }
        CommandWord word = CommandWord.getCommandWord(commandWord);
        if (word == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Command Word"));
        }


        return new CommandShortcut(word, this.shortcuts);
    }
}
