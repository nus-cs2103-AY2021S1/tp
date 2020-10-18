package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimapUtil.isOnlyOnePrefixPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_FILTER_BY_ASSIGNEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_FILTER_BY_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_FILTER_BY_NAME;

import java.util.function.Predicate;

import seedu.address.logic.commands.project.TaskFilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.GitUserName;
import seedu.address.model.project.Deadline;
import seedu.address.model.task.Task;

/**
 * Parses input {@code String} and creates a TaskFilterCommand object.
 */
public class TaskFilterCommandParser implements Parser<TaskFilterCommand> {

    /**
     * Parses the given input {@code String}.
     * @param args  the user input
     * @return      the filter command whose predicate corresponds to the user input
     * @throws ParseException   if the user input does not follow the format
     */
    @Override
    public TaskFilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_TASK_FILTER_BY_ASSIGNEE,
                PREFIX_TASK_FILTER_BY_DEADLINE, PREFIX_TASK_FILTER_BY_NAME);

        if (!isOnlyOnePrefixPresent(argMultimap, PREFIX_TASK_FILTER_BY_ASSIGNEE,
            PREFIX_TASK_FILTER_BY_DEADLINE, PREFIX_TASK_FILTER_BY_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskFilterCommand.MESSAGE_USAGE));
        }

        Predicate<Task> predicate = task -> true;

        if (argMultimap.getValue(PREFIX_TASK_FILTER_BY_ASSIGNEE).isPresent()) {
            GitUserName assigneeGitUserName = ParsePersonUtil.parseGitUserName(argMultimap
                .getValue(PREFIX_TASK_FILTER_BY_ASSIGNEE).get());
            predicate = task -> task.hasAssigneeWhoseGitNameIs(assigneeGitUserName);
        }

        if (argMultimap.getValue(PREFIX_TASK_FILTER_BY_DEADLINE).isPresent()) {
            Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_TASK_FILTER_BY_DEADLINE).get());
            predicate = task -> task.getDeadline().equals(deadline);
        }

        if (argMultimap.getValue(PREFIX_TASK_FILTER_BY_NAME).isPresent()) {
            predicate = task -> task.getTaskName()
                .contains(argMultimap.getValue(PREFIX_TASK_FILTER_BY_NAME).get());
        }

        return new TaskFilterCommand(predicate);
    }
}
