package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commandshortcut.CommandShortcut;
import seedu.address.logic.commandshortcut.CommandShortcutSet;


public class JsonAdaptedCommandShortcutSet {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Shortcut Set's %s field is missing!";

    private final List<JsonAdaptedCommandShortcut> shortcutList;

    /**
     * Constructs a {@code JsonAdaptedCommandShortcutSet} with the given {@code List<JsonAdaptedCommandShortcut>}.
     */
    @JsonCreator
    public JsonAdaptedCommandShortcutSet(@JsonProperty("shortcutList") List<JsonAdaptedCommandShortcut> shortcutList) {
        this.shortcutList = shortcutList;
    }

    /**
     * Converts a given {@code CommandShortcutSet} into this class for Jackson use.
     */
    public JsonAdaptedCommandShortcutSet(CommandShortcutSet source) {
        this.shortcutList = source.getListOfShortcuts().stream()
                .map(JsonAdaptedCommandShortcut::new).collect(Collectors.toList());
    }

    /**
     * Converts this Jackson-friendly adapted CommandAliasSet object into the model's {@code CommandShortcutSet} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted commandShortcutSet.
     */
    public CommandShortcutSet toModelType() throws IllegalValueException {
        if (shortcutList == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Shortcut"));
        }
        List<CommandShortcut> alias = new ArrayList<>();
        for (JsonAdaptedCommandShortcut jsonAdaptedCommandShortcut : shortcutList) {
            CommandShortcut commandShortcut = jsonAdaptedCommandShortcut.toModelType();
            alias.add(commandShortcut);
        }

        CommandShortcutSet.getInstance().setUpAliasSet(alias);
        return CommandShortcutSet.getInstance();
    }
}
