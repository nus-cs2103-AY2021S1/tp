package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimapUtil.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_IS_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;

import seedu.address.logic.commands.project.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.Deadline;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns an AddTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK_NAME, PREFIX_TASK_PROGRESS,
                        PREFIX_TASK_IS_DONE, PREFIX_TASK_DEADLINE);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASK_NAME,
                PREFIX_TASK_PROGRESS, PREFIX_TASK_IS_DONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }
        String taskName = ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_TASK_NAME).get());
        Double taskProgress = ParserUtil.parseTaskProgress(argMultimap.getValue(PREFIX_TASK_PROGRESS).get());
        boolean taskStatus = ParserUtil.parseDoneStatus(argMultimap.getValue(PREFIX_TASK_IS_DONE).get());
        Deadline taskDeadline = null;
        if (argMultimap.getValue(PREFIX_TASK_DEADLINE).isPresent()) {
            taskDeadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_TASK_DEADLINE).orElse(null));
        }
        Task task = new Task(taskName, null, taskDeadline, taskProgress, taskStatus);

        return new AddTaskCommand(task);
    }
}
