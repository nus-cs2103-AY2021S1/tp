package seedu.stock.logic.parser;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.DeleteCommand;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.stock.SerialNumber;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    private static final Logger logger = LogsCenter.getLogger(DeleteCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        logger.log(Level.INFO, "Starting to parse delete command");
        Set<SerialNumber> serialNumberSet = ParserUtil.parseSerialNumbers(args);
        logger.log(Level.INFO, "Finished parsing delete command successfully");
        return new DeleteCommand(serialNumberSet);
    }

}
