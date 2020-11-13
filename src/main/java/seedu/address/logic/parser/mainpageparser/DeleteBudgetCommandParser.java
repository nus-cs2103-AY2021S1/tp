package seedu.address.logic.parser.mainpageparser;

import seedu.address.logic.commands.main.DeleteBudgetCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.state.budgetindex.BudgetIndex;

public class DeleteBudgetCommandParser implements Parser<DeleteBudgetCommand> {

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    @Override
    public DeleteBudgetCommand parse(String userInput) throws ParseException {
        BudgetIndex budgetIndex = ParserUtil.parseBudgetIndex(userInput);
        return new DeleteBudgetCommand(budgetIndex);
    }

}
