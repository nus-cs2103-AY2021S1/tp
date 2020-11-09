package seedu.expense.logic.parser;

import static seedu.expense.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.Arrays;
import java.util.List;

import seedu.expense.logic.commands.FindCommand;
import seedu.expense.logic.parser.exceptions.ParseException;
import seedu.expense.model.expense.Date;
import seedu.expense.model.expense.DateMatchesPredicate;
import seedu.expense.model.expense.DescriptionContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    public static final String MISSING_ARGUMENTS = "You cannot leave arguments empty.";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_DATE);

        String keywords = "";
        List<String> dates = argMultimap.getAllValues(PREFIX_DATE);
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_DESCRIPTION).get();
            if (keywords.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MISSING_ARGUMENTS));
            }
        }
        if (dates.size() == 1 && dates.get(0).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MISSING_ARGUMENTS));
        }
        for (String date: dates) {
            if (!Date.isValidDate(date)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Date.MESSAGE_CONSTRAINTS));
            }
        }
        if (keywords.isEmpty() && dates.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(
                new DescriptionContainsKeywordsPredicate(Arrays.asList(keywords.trim().split("\\s+"))),
                new DateMatchesPredicate(dates)
        );
    }

}
