package seedu.tasklist.logic.parser;

import static seedu.tasklist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.tasklist.commons.core.index.Index;
import seedu.tasklist.logic.commands.UnprioritizeCommand;
import seedu.tasklist.logic.parser.exceptions.ParseException;

public class UnprioritizeCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the UnprioritiseCommand
     * and returns an UnprioritiseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnprioritizeCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnprioritizeCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnprioritizeCommand.MESSAGE_USAGE), pe);
        }
    }
}
