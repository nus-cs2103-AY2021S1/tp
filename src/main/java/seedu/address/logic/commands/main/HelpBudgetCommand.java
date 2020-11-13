package seedu.address.logic.commands.main;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpBudgetCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String SYNTAX = COMMAND_WORD;
    public static final String DESCRIPTION = "Shows a list of commands that is currently available.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Usage: " + SYNTAX + "\n"
            + "Description: " + DESCRIPTION + "\n";

    public static final String HELP_MESSAGE = CreateBudgetCommand.MESSAGE_USAGE
            + "\n" + DeleteBudgetCommand.MESSAGE_USAGE
            + "\n" + EditBudgetCommand.MESSAGE_USAGE
            + "\n" + FindBudgetCommand.MESSAGE_USAGE
            + "\n" + ListBudgetCommand.MESSAGE_USAGE
            + "\n" + SortBudgetCommand.MESSAGE_USAGE
            + "\n" + ClearBudgetsCommand.MESSAGE_USAGE
            + "\n" + OpenBudgetCommand.MESSAGE_USAGE
            + "\n" + HelpBudgetCommand.MESSAGE_USAGE
            + "\n" + ExitCommand.MESSAGE_USAGE;

    /**
     * Executes the help command.
     * @param model {@code Model} which the command should operate on.
     * @return the commmand result along with a success message
     */
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(HELP_MESSAGE, false, false);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof HelpBudgetCommand;
    }
}
