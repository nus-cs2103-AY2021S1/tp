package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_FILTER_BY_ASSIGNEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_FILTER_BY_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_FILTER_BY_NAME;

import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.project.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.Deadline;
import seedu.address.model.task.Task;

public class FilterCommandParser implements Parser<FilterCommand> {


    @Override
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_TASK_FILTER_BY_ASSIGNEE,
                PREFIX_TASK_FILTER_BY_DEADLINE, PREFIX_TASK_FILTER_BY_NAME);

        if (!isOnlyOnePrefixPresent(argMultimap, PREFIX_TASK_FILTER_BY_ASSIGNEE,
            PREFIX_TASK_FILTER_BY_DEADLINE, PREFIX_TASK_FILTER_BY_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        Predicate<Task> predicate = task -> true;

        if (argMultimap.getValue(PREFIX_TASK_FILTER_BY_ASSIGNEE).isPresent()) {
            predicate = task -> task.hasAssigneeWhoseNameIs(argMultimap
                .getValue(PREFIX_TASK_FILTER_BY_ASSIGNEE).get().trim());
        }

        if (argMultimap.getValue(PREFIX_TASK_FILTER_BY_DEADLINE).isPresent()) {
            Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_TASK_FILTER_BY_DEADLINE).get());
            predicate = task -> task.getDeadline().equals(deadline);
        }

        if (argMultimap.getValue(PREFIX_TASK_FILTER_BY_NAME).isPresent()) {
            predicate = task -> task.getTaskName()
                .contains(argMultimap.getValue(PREFIX_TASK_FILTER_BY_NAME).get().trim());
        }

        return new FilterCommand(predicate);
    }

    private static boolean isOnlyOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes)
            .filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
            .count() == 1;
    }
}
