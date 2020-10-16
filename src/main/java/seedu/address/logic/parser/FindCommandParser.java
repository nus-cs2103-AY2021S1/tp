package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_SEARCH_PHRASE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DATE_TIME,
                                            PREFIX_DESCRIPTION, PREFIX_TYPE, PREFIX_STATUS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE,
                PREFIX_DESCRIPTION, PREFIX_DATE_TIME, PREFIX_TYPE, PREFIX_STATUS, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        setKeyword(PREFIX_TITLE, argMultimap, predicate);
        setKeyword(PREFIX_DESCRIPTION, argMultimap, predicate);
        setKeyword(PREFIX_TYPE, argMultimap, predicate);
        setKeyword(PREFIX_DATE_TIME, argMultimap, predicate);
        setKeyword(PREFIX_STATUS, argMultimap, predicate);

        return new FindCommand(predicate);
    }

    private void setKeyword(Prefix prefix,
                            ArgumentMultimap argMultimap,
                            TaskContainsKeywordsPredicate predicate) throws ParseException {
        if (argMultimap.getValue(prefix).isPresent()) {
            String val = argMultimap.getValue(prefix).get();
            if (val.trim().length() == 0) {
                throw new ParseException(MESSAGE_EMPTY_SEARCH_PHRASE);
            }
            predicate.setKeyword(prefix, val);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
