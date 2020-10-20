package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;


public class UnassignallCommand extends Command {

    public static final String COMMAND_WORD = "unassignall";

    public static final String MESSAGE_SUCCESS = "Unassigned all instructors from all modules";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.unassignAllInstructors();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
