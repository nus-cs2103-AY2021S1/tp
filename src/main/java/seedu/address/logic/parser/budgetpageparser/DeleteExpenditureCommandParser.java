package seedu.address.logic.parser.budgetpageparser;

import seedu.address.logic.commands.budget.DeleteExpenditureCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.state.expenditureindex.ExpenditureIndex;

/**
 * Represents a Parser that is able to parse user inputs regarding deleting expenditures.
 */
public class DeleteExpenditureCommandParser implements Parser<DeleteExpenditureCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteBudgetCommand
     * and returns a DeleteBudgetCommand object for execution.
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException
     */
    @Override
    public DeleteExpenditureCommand parse(String userInput) throws ParseException {
        ExpenditureIndex expenditureIndex = ParserUtil.parseExpenditureIndex(userInput);
        return new DeleteExpenditureCommand(expenditureIndex);
    }

}
