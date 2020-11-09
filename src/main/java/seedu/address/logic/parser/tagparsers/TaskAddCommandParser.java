package seedu.address.logic.parser.tagparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tagcommands.TaskAddCommand;
import seedu.address.logic.commands.tagcommands.TaskAddCommand.EditTagDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TaskAddCommand object
 */
public class TaskAddCommandParser implements Parser<TaskAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TaskAddCommandParser
     * and returns a TaskAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaskAddCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskAddCommand.MESSAGE_USAGE), pe);
        }

        EditTagDescriptor editTagDescriptor = new EditTagDescriptor();

        if (argMultimap.getValue(PREFIX_TASK).isPresent()) {
            editTagDescriptor.setNewTagTask(ParserUtil.parseTagTask(argMultimap.getValue(PREFIX_TASK).get(), false));
        } else {
            throw new ParseException(TaskAddCommand.MESSAGE_TASK_NAME_NOT_PROVIDED);
        }

        return new TaskAddCommand(index, editTagDescriptor);
    }
}
