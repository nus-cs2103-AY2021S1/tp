package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_TYPE;

import java.util.stream.Stream;

import seedu.address.logic.commands.SortPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.information.comparator.PersonBlackListComparator;
import seedu.address.model.information.comparator.PersonComparator;
import seedu.address.model.information.comparator.PersonDateOfApplicationComparator;
import seedu.address.model.information.comparator.PersonExpectedSalaryComparator;
import seedu.address.model.information.comparator.PersonExperienceComparator;
import seedu.address.model.information.comparator.PersonNameComparator;

/**
 * Parses input arguments and creates a new SortPersonCommand object.
 */
public class SortPersonCommandParser implements Parser<SortPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortPersonCommand
     * and returns a SortPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public SortPersonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SORT_ORDER, PREFIX_SORT_TYPE);
        if (!arePrefixesPresent(argMultimap, PREFIX_SORT_ORDER, PREFIX_SORT_TYPE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortPersonCommand.MESSAGE_USAGE));
        }

        Boolean isAscending = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_SORT_ORDER).orElse(null));
        String sortType = argMultimap.getValue(PREFIX_SORT_TYPE).orElse(null);
        PersonComparator comparator;

        switch (sortType) {
        case PersonExperienceComparator.SORT_CRITERIA:
            comparator = new PersonExperienceComparator();
            return new SortPersonCommand(comparator, isAscending);
        case PersonExpectedSalaryComparator.SORT_CRITERIA:
            comparator = new PersonExpectedSalaryComparator();
            return new SortPersonCommand(comparator, isAscending);
        case PersonDateOfApplicationComparator.SORT_CRITERIA:
            comparator = new PersonDateOfApplicationComparator();
            return new SortPersonCommand(comparator, isAscending);
        case PersonBlackListComparator.SORT_CRITERIA:
            comparator = new PersonBlackListComparator();
            return new SortPersonCommand(comparator, isAscending);
        case PersonNameComparator.SORT_CRITERIA:
            comparator = new PersonNameComparator();
            return new SortPersonCommand(comparator, isAscending);
        default:
            throw new ParseException(SortPersonCommand.MESSAGE_SORT_TYPE_INVALID);

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
