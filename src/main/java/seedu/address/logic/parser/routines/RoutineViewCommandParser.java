package seedu.address.logic.parser.routines;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.routines.RoutineViewCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RoutineViewCommand object
 */
public class RoutineViewCommandParser implements Parser<RoutineViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RoutineViewCommand
     * and returns a RoutineViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RoutineViewCommand parse(String args) throws ParseException {

        try {
            Index index = ParserUtil.parseIndex(args);
            return new RoutineViewCommand(index);
        } catch (NumberFormatException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RoutineViewCommand.MESSAGE_USAGE));
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RoutineViewCommand.MESSAGE_USAGE));
        }

    }
}
