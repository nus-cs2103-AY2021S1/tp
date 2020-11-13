package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnremindCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UnremindCommandParser implements Parser<UnremindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnremindCommand
     * and returns an UnremindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnremindCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnremindCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnremindCommand.MESSAGE_USAGE), pe);
        }
    }

}
