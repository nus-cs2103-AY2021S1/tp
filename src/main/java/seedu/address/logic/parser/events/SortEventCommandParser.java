package seedu.address.logic.parser.events;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.events.SortEventCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code SortCommand} object
 */
public class SortEventCommandParser implements Parser<SortEventCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code SortCommand}
     * and returns a {@code SortCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortEventCommand parse(String args) throws ParseException {

        try {
            Index index = ParserUtil.parseIndex(args);
            return new SortEventCommand(index);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortEventCommand.MESSAGE_USAGE), ive);
        }

    }
}
