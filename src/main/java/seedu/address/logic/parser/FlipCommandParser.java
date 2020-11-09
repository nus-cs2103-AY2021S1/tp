package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FlipCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class FlipCommandParser implements Parser<FlipCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FlipCommand.
     * and returns a FlipCommand object for execution.
     * @param args the user input
     * @return a FlipCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FlipCommand parse(String args) throws ParseException {
        requireNonNull(args);

        try {
            Index index = ParserUtil.parseIndex(args);
            return new FlipCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FlipCommand.MESSAGE_USAGE), pe);
        }
    }

}
