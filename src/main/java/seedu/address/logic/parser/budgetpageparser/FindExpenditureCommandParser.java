package seedu.address.logic.parser.budgetpageparser;

import seedu.address.logic.commands.budget.FindExpenditureCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expenditure.Name;

/**
 * Represents a Parser that is able to parse user inputs regarding finding expenditures.
 */
public class FindExpenditureCommandParser implements Parser<FindExpenditureCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindExpenditureCommand
     * and returns a FindExpenditureCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FindExpenditureCommand parse(String userInput) throws ParseException {
        Name name = ParserUtil.parseExpenditureName(userInput);
        return new FindExpenditureCommand(name);
    }
}
