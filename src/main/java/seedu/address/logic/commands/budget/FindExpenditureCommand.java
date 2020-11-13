package seedu.address.logic.commands.budget;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.expenditure.Name;

/**
 * Filters a budget book to find specific expenditures matching a search term.
 */
public class FindExpenditureCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String SYNTAX = COMMAND_WORD + " NAME";
    public static final String DESCRIPTION = "Finds expenditures by name.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Usage: " + SYNTAX + "\n"
            + "Description: " + DESCRIPTION + "\n"
            + "Parameters: " + "NAME\n"
            + "Example: " + COMMAND_WORD + " Shirt\n";

    public static final String MESSAGE_SUCCESS =
            "Updated list of expenditures to show entries that contain \'%1$s\'.";
    public static final String MESSAGE_NO_EXPENDITURES_FOUND = "No expenditures matching \'%s\' were found.";
    private final Name name;

    /**
     * Creates a FindExpenditureCommand to to find expenditures matching
     * the specified {@code name}.
     * @param name the name of the expenditure to be searched.
     */
    public FindExpenditureCommand(Name name) {
        requireNonNull(name);
        this.name = name;
    }

    /**
     * Executes the find expenditure command.
     * @param model {@code Model} which the command should operate on.
     * @return the result of the command with the success message.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String searchTerm = name.value;
        model.findExpenditure(searchTerm);
        return new CommandResult(String.format(MESSAGE_SUCCESS, name));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindExpenditureCommand // instanceof handles nulls
                && name.equals(((FindExpenditureCommand) other).name));
    }
}
