package seedu.address.logic.parser.todolistparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Comparator;

import seedu.address.logic.commands.todolistcommands.SortTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Criterion;
import seedu.address.model.task.Task;
import seedu.address.model.task.comparator.TaskComparatorByDate;
import seedu.address.model.task.comparator.TaskComparatorByName;
import seedu.address.model.task.comparator.TaskComparatorByPriority;

/**
 * Parses input arguments and creates a new SortTaskCommand object
 */
public class SortTaskParser implements Parser<SortTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SortTaskCommand
     * and returns an SortTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortTaskCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        String trimmedArgs = userInput.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortTaskCommand.MESSAGE_USAGE));
        }

        String[] splittedInput = trimmedArgs.split("\\s+");
        Comparator<Task> comparator = parseReversedComparatorForSort(splittedInput);

        return new SortTaskCommand(comparator);
    }

    private Comparator<Task> parseComparatorForSort(String input) throws ParseException {
        Criterion criterion = ParserUtil.parseTaskCriterion(input);
        switch(criterion) {
        case NAME:
            return new TaskComparatorByName();
        case PRIORITY:
            return new TaskComparatorByPriority();
        case DATE:
            return new TaskComparatorByDate();
        default:
            throw new ParseException(Criterion.MESSAGE_CONSTRAINTS);
        }
    }

    private Comparator<Task> parseReversedComparatorForSort(String[] splitInput) throws ParseException {
        assert splitInput.length >= 1;

        if (splitInput.length == 1) {
            return parseComparatorForSort(splitInput[0]);
        }

        if (!splitInput[0].equals("r")) {
            throw new ParseException("Format should be in order i.e. [r] [CRITERION]");
        }

        return parseComparatorForSort(splitInput[1]).reversed();
    }
}
