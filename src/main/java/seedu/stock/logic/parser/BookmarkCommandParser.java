package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.BookmarkCommand;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.stock.SerialNumber;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class BookmarkCommandParser implements Parser<BookmarkCommand> {
    private static final Logger logger = LogsCenter.getLogger(BookmarkCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a BookmarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BookmarkCommand parse(String args) throws ParseException {
        logger.log(Level.INFO, "Starting to parse bookmark command");
        try {
            Set<SerialNumber> serialNumberSet = ParserUtil.parseSerialNumbers(args);
            logger.log(Level.INFO, "Finished parsing bookmark command successfully");
            return new BookmarkCommand(serialNumberSet);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, BookmarkCommand.MESSAGE_USAGE), pe);
        }
    }

}
