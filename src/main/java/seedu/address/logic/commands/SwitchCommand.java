package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Switches the active module list in FaculType.
 */
public class SwitchCommand extends Command {

    public static final String COMMAND_WORD = "switch";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches the active module list in FaculType.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SWITCH_SUCCESS = "Switched active module list to Semester %s";

    @Override
    public CommandResult execute(Model model) {
        model.switchModuleList();

        return new CommandResult(
            String.format(MESSAGE_SWITCH_SUCCESS, model.getSemester()),
            false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        return other instanceof SwitchCommand;
    }
}
