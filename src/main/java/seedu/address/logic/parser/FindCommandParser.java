package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expense.Date;
import seedu.address.model.expense.DateMatchesPredicate;
import seedu.address.model.expense.NameContainsKeywordsPredicate;
import seedu.address.model.expense.TagsMatchesPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

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
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, //PREFIX_AMOUNT,
                        PREFIX_DATE, PREFIX_TAG);

        String keywords = "";
        List<String> dates = argMultimap.getAllValues(PREFIX_DATE);
        List<String> tags = argMultimap.getAllValues(PREFIX_TAG);
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_DESCRIPTION).get();
        }
        for (String date: dates) {
            if (!Date.isValidDate(date)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Date.MESSAGE_CONSTRAINTS));
            }
        }
        if (keywords.isEmpty() && dates.isEmpty() && tags.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(
                new NameContainsKeywordsPredicate(Arrays.asList(keywords.trim().split("\\s+"))),
                new DateMatchesPredicate(dates),
                new TagsMatchesPredicate(tags)
        );
    }

}
