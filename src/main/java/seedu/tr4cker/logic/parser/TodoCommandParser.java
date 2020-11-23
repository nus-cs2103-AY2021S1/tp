package seedu.tr4cker.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.TodoCommand;
import seedu.tr4cker.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TodoCommand object
 */
public class TodoCommandParser implements Parser<TodoCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TodoCommand
     * and returns a TodoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TodoCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] trimmedArgsTokens = args.trim().split(" ");
        int lengthArgsTokens = trimmedArgsTokens.length;
        assert lengthArgsTokens > 0;

        if (!(lengthArgsTokens == 1)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TodoCommand.MESSAGE_USAGE));
        }
        Index index = ParserUtil.parseIndex(trimmedArgsTokens[0]);
        return new TodoCommand(index);
    }
}
