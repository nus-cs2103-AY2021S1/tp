package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Lists all modules in all semesters in MyMods
 * to the user regardless of whether the user
 * keys in this command inside a semester or not.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all modules in all semesters.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all modules in all semesters";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListCommand; // instanceof handles nulls
    }
}
