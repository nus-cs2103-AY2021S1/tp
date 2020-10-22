package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UndoneCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UndoneCommandParser implements Parser<UndoneCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UndoneCommand
     * and returns an UndoneCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UndoneCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UndoneCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndoneCommand.MESSAGE_USAGE), pe);
        }
    }

}
