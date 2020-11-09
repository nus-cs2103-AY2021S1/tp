package seedu.address.logic.parser.tagparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tagcommands.TaskClearCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class TaskClearCommandParser implements Parser<TaskClearCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TaskClearCommand
     * and returns a TaskClearCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaskClearCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Index index = ParserUtil.parseIndex(args);
            return new TaskClearCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskClearCommand.MESSAGE_USAGE), pe);
        }
    }

}
