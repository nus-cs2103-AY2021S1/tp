package seedu.forgetfulnus.logic.parser;

import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.forgetfulnus.logic.commands.SortCommand;
import seedu.forgetfulnus.logic.parser.exceptions.ParseException;

public class SortCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    SortCommand parse(String args) throws ParseException {
        assert args != null : "Input cannot be null!";
        try {
            String params = ParserUtil.parseSortParams(args);
            return new SortCommand(params);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE), pe);
        }
    }
}
