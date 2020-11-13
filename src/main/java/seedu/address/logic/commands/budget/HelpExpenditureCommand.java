package seedu.address.logic.commands.budget;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpExpenditureCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String SYNTAX = COMMAND_WORD;
    public static final String DESCRIPTION = "Shows a list of commands that is currently available.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Usage: " + SYNTAX + "\n"
            + "Description: " + DESCRIPTION + "\n";

    public static final String HELP_MESSAGE = AddExpenditureCommand.MESSAGE_USAGE
            + "\n" + DeleteExpenditureCommand.MESSAGE_USAGE
            + "\n" + EditExpenditureCommand.MESSAGE_USAGE
            + "\n" + FindExpenditureCommand.MESSAGE_USAGE
            + "\n" + ListExpenditureCommand.MESSAGE_USAGE
            + "\n" + SortExpenditureCommand.MESSAGE_USAGE
            + "\n" + CloseBudgetCommand.MESSAGE_USAGE
            + "\n" + HelpExpenditureCommand.MESSAGE_USAGE
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
        return obj instanceof HelpExpenditureCommand;
    }
}
