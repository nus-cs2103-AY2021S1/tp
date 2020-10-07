package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expense.Date;
import seedu.address.model.expense.DateMatchesPredicate;
import seedu.address.model.expense.NameContainsKeywordsPredicate;
import seedu.address.model.expense.TagsMatchesPredicate;
import seedu.address.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_AMOUNT,
                        PREFIX_DATE, PREFIX_TAG);
        String keywords = "";
        String date = "";
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_DESCRIPTION).get();
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            date = argMultimap.getValue(PREFIX_DATE).get();
        }
        List<String> tags = argMultimap.getAllValues(PREFIX_TAG);

        // String[] nameKeywords = trimmedArgs.split("\\s+");

        if (keywords.equals(date) && tags.isEmpty()) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        /*
        boolean dateFound = false;
        Date date;
        boolean tagsFound = false;
        Set<Tag> tags = new HashSet<Tag>();
        for (int i = 0; i < nameKeywords.length; i++) {
            if (nameKeywords[i].contains("-@") && !dateFound) {
                if (nameKeywords[i].length() > 2) {
                    date = new Date(nameKeywords[i].substring(2));
                } else if (i < nameKeywords.length - 1) {
                    date = new Date(nameKeywords[i+1]);
                }
                dateFound = true;
            } else if (nameKeywords[i].contains("t/")) {
                if (nameKeywords[i].length() > 2) {
                    tags.add(new Tag(nameKeywords[i].substring(2)));
                } else if (i < nameKeywords.length - 1) {
                    tags.add(new Tag(nameKeywords[i+1]));
                }
            }
        }
         */
        return new FindCommand(
                new NameContainsKeywordsPredicate(Arrays.asList(keywords.trim().split("\\s+"))),
                new DateMatchesPredicate(date),
                new TagsMatchesPredicate(tags)
        );
    }

}
