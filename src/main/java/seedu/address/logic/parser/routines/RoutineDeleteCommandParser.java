package seedu.address.logic.parser.routines;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.routines.RoutineDeleteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RoutineDeleteCommand object
 */
public class RoutineDeleteCommandParser implements Parser<RoutineDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RoutineDeleteCommand
     * and returns a RoutineDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RoutineDeleteCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RoutineDeleteCommand.MESSAGE_USAGE));
        }
        try {
            Index index = ParserUtil.parseIndex(args);
            return new RoutineDeleteCommand(index);
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RoutineDeleteCommand.MESSAGE_USAGE));
        }
    }

}
