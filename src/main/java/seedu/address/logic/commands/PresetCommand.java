package seedu.address.logic.commands;

public abstract class PresetCommand extends Command {

    public static final String COMMAND_WORD = "preset";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": saves the current order to a preset with the given name if provided.\n"
            + "Or loads the preset with the given name if its present.";

    public static final String MESSAGE_DISPLAY_ALL_PRESETS = "Here are your list of saved presets!\n";
}
