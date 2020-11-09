package seedu.address.logic.parser.contactlistparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.contactlistcommands.DeleteContactCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteContactCommand object.
 */
public class DeleteContactParser implements Parser<DeleteContactCommand> {

    private final Logger logger = LogsCenter.getLogger(DeleteContactCommand.class);

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteContactCommand
     * and returns a DeleteContactCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public DeleteContactCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            logger.info("Parsing the command arguments: " + args);
            Index index = ParserUtil.parseIndex(args);
            return new DeleteContactCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteContactCommand.MESSAGE_USAGE), pe);
        }
    }
}
