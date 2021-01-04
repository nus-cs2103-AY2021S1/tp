package seedu.homerce.logic.parser.expense;

import static seedu.homerce.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.homerce.logic.commands.expense.SortExpenseCommand;
import seedu.homerce.logic.parser.ParserUtil;
import seedu.homerce.logic.parser.exceptions.ParseException;

public class SortExpenseCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the SortExpenseCommand
     * and returns a SortExpenseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortExpenseCommand parse(String args) throws ParseException {
        try {
            boolean isAscending = ParserUtil.parseOrder(args);
            return new SortExpenseCommand(isAscending);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortExpenseCommand.MESSAGE_USAGE), pe);
        }
    }
}
