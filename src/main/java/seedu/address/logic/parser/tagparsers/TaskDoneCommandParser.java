package seedu.address.logic.parser.tagparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tagcommands.TaskDoneCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class TaskDoneCommandParser implements Parser<TaskDoneCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the LinkDeleteCommand
     * and returns a LinkDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaskDoneCommand parse(String args) throws ParseException {
        requireNonNull(args);

        try {
            String[] twoIndex = ParserUtil.parseTwoIndex(args);
            String tagIndexStr = twoIndex[0];
            String taskIndexStr = twoIndex[1];
            Index tagIndex = ParserUtil.parseIndex(tagIndexStr);
            Index taskIndex = ParserUtil.parseAlphaIndex(taskIndexStr);
            return new TaskDoneCommand(tagIndex, taskIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskDoneCommand.MESSAGE_USAGE), pe);
        }
    }
}
