package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimapUtil.isOnlyOneGivenPrefixPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASCENDING_SORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCENDING_SORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_IS_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;

import java.util.Comparator;

import seedu.address.logic.commands.project.TaskSorterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskComparators;

/**
 * Parses input {@code String} and creates a TaskSorterCommand object.
 */
public class TaskSorterCommandParser implements Parser<TaskSorterCommand> {

    /**
     * Parses the given input {@code String}.
     * @param args  the user input
     * @return      the filter command whose predicate corresponds to the user input
     * @throws ParseException   if the user input does not follow the format
     */
    @Override
    public TaskSorterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_ASCENDING_SORT, PREFIX_DESCENDING_SORT,
                PREFIX_TASK_DEADLINE, PREFIX_TASK_NAME, PREFIX_TASK_PROGRESS, PREFIX_TASK_IS_DONE);
        if (!isValidSorterArgs(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskSorterCommand.MESSAGE_USAGE));
        }

        boolean isDescendingOrder = false;
        if (argMultimap.getValue(PREFIX_DESCENDING_SORT).isPresent()) {
            isDescendingOrder = true;
        }
        Comparator<Task> comparator = TaskComparators.SORT_BY_TASK_NAME;
        if (argMultimap.getValue(PREFIX_TASK_DEADLINE).isPresent()) {
            comparator = TaskComparators.SORT_BY_DEADLINE;
        }
        if (argMultimap.getValue(PREFIX_TASK_PROGRESS).isPresent()) {
            comparator = TaskComparators.SORT_BY_PROGRESS;
        }
        if (argMultimap.getValue(PREFIX_TASK_IS_DONE).isPresent()) {
            comparator = TaskComparators.SORT_BY_IS_DONE;
        }
        if (isDescendingOrder) {
            comparator = comparator.reversed();
        }
        return new TaskSorterCommand(comparator);
    }

    private static boolean isValidSorterArgs(ArgumentMultimap argumentMultimap) {
        if (argumentMultimap.numOfPrefixesWithNoValues() != 2) {
            return false;
        }
        return isOnlyOneGivenPrefixPresent(argumentMultimap, PREFIX_ASCENDING_SORT, PREFIX_DESCENDING_SORT)
            && isOnlyOneGivenPrefixPresent(argumentMultimap, PREFIX_TASK_DEADLINE, PREFIX_TASK_NAME,
            PREFIX_TASK_PROGRESS, PREFIX_TASK_IS_DONE);
    }
}
