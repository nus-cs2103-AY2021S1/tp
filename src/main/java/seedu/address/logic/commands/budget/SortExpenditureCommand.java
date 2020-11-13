package seedu.address.logic.commands.budget;


import seedu.address.logic.commands.BudgetPageCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.sort.SortType;

/**
 * Creates a new SortExpenditureCommand.
 */
public class SortExpenditureCommand extends BudgetPageCommand {
    public static final String COMMAND_WORD = "sort";
    public static final String SYNTAX = COMMAND_WORD;
    public static final String DESCRIPTION = "Sorts expenditure by name or time.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Usage: " + SYNTAX + " TYPE\n"
            + "Description: " + DESCRIPTION + "\n"
            + "Parameters: TYPE\n"
            + "Example: " + COMMAND_WORD + " time\n";

    public static final String MESSAGE_SUCCESS = "Sorted expenditures.";

    private final SortType sortType;
    /**
     * Creates a SortBudgetCommand to to sort budgets by {@code sortType}.
     * @param sortType the type of sort.
     */
    public SortExpenditureCommand(SortType sortType) {
        this.sortType = sortType;
    }

    /**
     * Executes the sort expenditure command.
     * @param model {@code Model} which the command should operate on.
     * @return the commmand result along with a success message
     */
    @Override
    public CommandResult execute(Model model) {
        switch (sortType) {
        case TIME: {
            model.sortExpenditureByCreatedDate();
            break;
        }
        case NAME: {
            model.sortExpendituresByName();
            break;
        }
        default: {
            break;
        }
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortExpenditureCommand // instanceof handles nulls
                && sortType.equals(((SortExpenditureCommand) other).sortType));
    }
}
