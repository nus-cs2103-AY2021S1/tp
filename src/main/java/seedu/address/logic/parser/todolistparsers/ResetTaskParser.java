package seedu.address.logic.parser.todolistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.todolistcommands.ResetTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ResetTaskCommand object.
 */
public class ResetTaskParser implements Parser<ResetTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ResetTaskCommand
     * and returns a ResetTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ResetTaskCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            return new ResetTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResetTaskCommand.MESSAGE_USAGE), pe);
        }
    }
}
