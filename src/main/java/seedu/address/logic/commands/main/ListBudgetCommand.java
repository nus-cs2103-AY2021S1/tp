package seedu.address.logic.commands.main;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ListBudgetCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String SYNTAX = COMMAND_WORD;
    public static final String DESCRIPTION = "Lists all budgets.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Usage: " + SYNTAX + "\n"
            + "Description: " + DESCRIPTION + "\n";

    public static final String MESSAGE_SUCCESS = "Updated list of budgets to show all entries.\n";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.listBudgets();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListBudgetCommand;
    }
}
