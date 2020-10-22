package seedu.address.logic.parser.todolistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.todolistcommands.CompleteTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CompleteTaskCommand object.
 */
public class CompleteTaskParser implements Parser<CompleteTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the CompleteTaskCommand
     * and returns a CompleteTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public CompleteTaskCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            return new CompleteTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompleteTaskCommand.MESSAGE_USAGE), pe);
        }
    }
}
