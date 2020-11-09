package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_TYPE;

import java.util.stream.Stream;

import seedu.address.logic.commands.SortJobCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.information.comparator.JobCompanyComparator;
import seedu.address.model.information.comparator.JobComparator;
import seedu.address.model.information.comparator.JobPriorityComparator;
import seedu.address.model.information.comparator.JobTitleComparator;
import seedu.address.model.information.comparator.JobVacancyComparator;

public class SortJobCommandParser implements Parser<SortJobCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortJobCommand
     * and returns a SortJobCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public SortJobCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SORT_ORDER, PREFIX_SORT_TYPE);
        if (!arePrefixesPresent(argMultimap, PREFIX_SORT_ORDER, PREFIX_SORT_TYPE)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortJobCommand.MESSAGE_USAGE));
        }

        JobComparator comparator;
        Boolean isAscending = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_SORT_ORDER).orElse(null));
        String sortType = argMultimap.getValue(PREFIX_SORT_TYPE).orElse(null);

        switch (sortType) {
        case JobVacancyComparator.SORT_CRITERIA:
            comparator = new JobVacancyComparator();
            return new SortJobCommand(comparator, isAscending);

        case JobPriorityComparator.SORT_CRITERIA:
            comparator = new JobPriorityComparator();
            return new SortJobCommand(comparator, isAscending);

        case JobTitleComparator.SORT_CRITERIA:
            comparator = new JobTitleComparator();
            return new SortJobCommand(comparator, isAscending);

        case JobCompanyComparator.SORT_CRITERIA:
            comparator = new JobCompanyComparator();
            return new SortJobCommand(comparator, isAscending);

        default:
            throw new ParseException(SortJobCommand.MESSAGE_SORT_JOB_TYPE_INVALID);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
