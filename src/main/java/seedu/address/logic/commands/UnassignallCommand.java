package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;


public class UnassignallCommand extends Command {

    public static final String COMMAND_WORD = "unassignall";

    public static final String MESSAGE_USAGE = "\n" + COMMAND_WORD
            + ": Unassigns all instructors from all modules.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Unassigned all instructors from all modules "
            + "in the active semester";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.unassignAllInstructors();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UnassignallCommand); // instanceof handles nulls
    }
}
