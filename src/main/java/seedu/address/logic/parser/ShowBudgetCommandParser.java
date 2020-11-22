package seedu.address.logic.parser;

import seedu.address.logic.commands.ShowBudgetCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ShowBudget object
 */
public class ShowBudgetCommandParser implements Parser<ShowBudgetCommand> {

    /**
     * Parses the given {@code String} of userInput in the context of the ShowBudgetCommand
     * and returns a ShowBudgetCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ShowBudgetCommand parse(String userInput) {
        return new ShowBudgetCommand();
    }
}
