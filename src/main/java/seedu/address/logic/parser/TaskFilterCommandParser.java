package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimapUtil.areOnlyTheseTwoPrefixesPresent;
import static seedu.address.logic.parser.ArgumentMultimapUtil.isOnlyOnePrefixPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_ASSIGNEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_IS_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;

import java.util.function.Predicate;

import seedu.address.logic.commands.project.TaskFilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.GitUserName;
import seedu.address.model.project.Deadline;
import seedu.address.model.task.Date;
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
            ArgumentTokenizer.tokenize(args, PREFIX_TASK_ASSIGNEE,
                PREFIX_TASK_DEADLINE, PREFIX_TASK_NAME, PREFIX_TASK_PROGRESS, PREFIX_TASK_IS_DONE,
                PREFIX_START_DATE, PREFIX_END_DATE);

        if (!(areOnlyTheseTwoPrefixesPresent(argMultimap, PREFIX_START_DATE, PREFIX_END_DATE)
            || isOnlyOnePrefixPresent(argMultimap, PREFIX_TASK_ASSIGNEE,
            PREFIX_TASK_DEADLINE, PREFIX_TASK_NAME, PREFIX_TASK_PROGRESS, PREFIX_TASK_IS_DONE))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskFilterCommand.MESSAGE_USAGE));
        }

        Predicate<Task> predicate = task -> true;

        if (argMultimap.getValue(PREFIX_TASK_ASSIGNEE).isPresent()) {
            GitUserName assigneeGitUserName = ParsePersonUtil.parseGitUserName(argMultimap
                .getValue(PREFIX_TASK_ASSIGNEE).get());
            predicate = task -> task.hasAssigneeWhoseGitNameIs(assigneeGitUserName);
        }
        if (argMultimap.getValue(PREFIX_TASK_DEADLINE).isPresent()) {
            Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_TASK_DEADLINE).get());
            predicate = task -> task.isDueOn(deadline);
        }
        if (argMultimap.getValue(PREFIX_TASK_NAME).isPresent()) {
            String taskName = ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_TASK_NAME).get());
            predicate = task -> task.getTaskName().contains(taskName);
        }
        if (argMultimap.getValue(PREFIX_TASK_PROGRESS).isPresent()) {
            Double progress = ParserUtil.parseTaskProgress(argMultimap.getValue(PREFIX_TASK_PROGRESS).get());
            predicate = task -> task.getProgress().equals(progress);
        }
        if (argMultimap.getValue(PREFIX_TASK_IS_DONE).isPresent()) {
            Boolean isDone = ParserUtil.parseDoneStatus(argMultimap.getValue(PREFIX_TASK_IS_DONE).get());
            predicate = task -> task.isDone().equals(isDone);
        }
        if (areOnlyTheseTwoPrefixesPresent(argMultimap, PREFIX_START_DATE, PREFIX_END_DATE)) {
            Date startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_DATE).get());
            Date endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_END_DATE).get());
            if (endDate.isBefore(startDate)) {
                throw new ParseException(TaskFilterCommand.MESSAGE_INVALID_TIME_RANGE);
            }
            predicate = task -> task.isDueBetween(startDate, endDate);
        }
        return new TaskFilterCommand(predicate);
    }
}
