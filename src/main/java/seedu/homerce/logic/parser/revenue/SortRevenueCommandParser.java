package seedu.homerce.logic.parser.revenue;

import static seedu.homerce.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.homerce.logic.commands.revenue.SortRevenueCommand;
import seedu.homerce.logic.parser.ParserUtil;
import seedu.homerce.logic.parser.exceptions.ParseException;

public class SortRevenueCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the SortRevenueCommand
     * and returns a SortRevenueCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortRevenueCommand parse(String args) throws ParseException {
        try {
            boolean isAscending = ParserUtil.parseOrder(args);
            return new SortRevenueCommand(isAscending);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortRevenueCommand.MESSAGE_USAGE), pe);
        }
    }
}
