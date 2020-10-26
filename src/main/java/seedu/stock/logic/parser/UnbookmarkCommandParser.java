package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Set;

import seedu.stock.logic.commands.UnbookmarkCommand;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.stock.SerialNumber;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class UnbookmarkCommandParser implements Parser<UnbookmarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a BookmarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnbookmarkCommand parse(String args) throws ParseException {
        try {
            Set<SerialNumber> serialNumberSet = ParserUtil.parseSerialNumbers(args);
            return new UnbookmarkCommand(serialNumberSet);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnbookmarkCommand.MESSAGE_USAGE), pe);
        }
    }

}
