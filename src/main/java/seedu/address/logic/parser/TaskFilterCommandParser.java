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

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.logic.commands.project.TaskFilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.GitUserName;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;
import seedu.address.model.task.Date;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;

/**
 * Parses input {@code String} and creates a TaskFilterCommand object.
 */
public class TaskFilterCommandParser implements Parser<TaskFilterCommand> {

    /**
     * Parses the given input {@code String} in the context of the TaskFilterCommand,
     * and returns a TaskFilterCommand object for execution.
     *
     * @param args the user input.
     * @return a TaskFilterCommand whose predicate corresponds to the user input.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public TaskFilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_TASK_ASSIGNEE, PREFIX_TASK_DEADLINE,
                    PREFIX_TASK_NAME, PREFIX_TASK_PROGRESS, PREFIX_TASK_IS_DONE,
                    PREFIX_START_DATE, PREFIX_END_DATE);

        if (!isValidFilterArgs(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskFilterCommand.MESSAGE_USAGE));
        }

        Predicate<Task> predicate = Project.SHOW_ALL_TASKS_PREDICATE;

        if (argMultimap.getValue(PREFIX_TASK_ASSIGNEE).isPresent()) {
            GitUserName assigneeGitUserName = ParsePersonUtil
                    .parseGitUserName(argMultimap.getValue(PREFIX_TASK_ASSIGNEE).get());
            predicate = task -> task.hasAssigneeWhoseGitNameIs(assigneeGitUserName);
        }
        if (argMultimap.getValue(PREFIX_TASK_DEADLINE).isPresent()) {
            Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_TASK_DEADLINE).get());
            predicate = task -> task.isDueOn(deadline);
        }
        if (argMultimap.getValue(PREFIX_TASK_NAME).isPresent()) {
            String[] taskNameKeywords = ParserUtil.parseTaskNameKeywords(argMultimap.getValue(PREFIX_TASK_NAME).get());
            predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList(taskNameKeywords));
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

    private static boolean isValidFilterArgs(ArgumentMultimap argMultimap) {
        return (areOnlyTheseTwoPrefixesPresent(argMultimap, PREFIX_START_DATE, PREFIX_END_DATE)
                || isOnlyOnePrefixPresent(argMultimap, PREFIX_TASK_ASSIGNEE, PREFIX_TASK_DEADLINE,
                        PREFIX_TASK_NAME, PREFIX_TASK_PROGRESS, PREFIX_TASK_IS_DONE));
    }
}

