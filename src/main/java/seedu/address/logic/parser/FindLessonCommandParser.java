package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_SEARCH_PHRASE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.util.DateUtil;
import seedu.address.logic.commands.FindLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.LessonContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;


/**
 * Parses input arguments and creates a new FindLessonCommand object
 */
public class FindLessonCommandParser implements Parser<FindLessonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindLessonCommand
     * and returns a FindLessonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindLessonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TITLE,
                PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_TIME, PREFIX_DATE_TIME, PREFIX_TAG);

        if (!isAnyPrefixPresent(argMultimap, PREFIX_TITLE,
                PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_TIME, PREFIX_DATE_TIME, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindLessonCommand.MESSAGE_USAGE));
        }
        LessonContainsKeywordsPredicate predicate = new LessonContainsKeywordsPredicate();
        setKeyword(PREFIX_TITLE, argMultimap, predicate);
        setKeyword(PREFIX_DESCRIPTION, argMultimap, predicate);
        setKeyword(PREFIX_DATE, argMultimap, predicate);
        setKeyword(PREFIX_TIME, argMultimap, predicate);
        setKeyword(PREFIX_DATE_TIME, argMultimap, predicate);
        setKeyword(PREFIX_TAG, argMultimap, predicate);

        return new FindLessonCommand(predicate);
    }

    private void setKeyword(Prefix prefix,
                            ArgumentMultimap argMultimap,
                            LessonContainsKeywordsPredicate predicate) throws ParseException {
        List<String> values = argMultimap.getAllValues(prefix);
        if (values.size() == 0) {
            return;
        }
        // trim values
        values = values.stream().map(String::trim).collect(Collectors.toList());
        for (String trimmed : values) {
            if (trimmed.length() == 0) {
                throw new ParseException(MESSAGE_EMPTY_SEARCH_PHRASE);
            }
            if (prefix.equals(PREFIX_TITLE) && !Title.isValidTitle(trimmed)) {
                throw new ParseException(Title.SEARCH_CONSTRAINTS);
            }
            if (prefix.equals(PREFIX_DESCRIPTION) && !Description.isValidDescription(trimmed)) {
                throw new ParseException(Description.SEARCH_CONSTRAINTS);
            }
            if (prefix.equals(PREFIX_DATE) && !DateUtil.isValidDate(trimmed)) {
                throw new ParseException(DateUtil.SEARCH_DATE_CONSTRAINTS);
            }
            if (prefix.equals(PREFIX_DATE_TIME) && !DateUtil.isValidDateTime(trimmed)) {
                throw new ParseException(DateUtil.DATE_TIME_CONSTRAINTS);
            }
            if (prefix.equals(PREFIX_TIME) && !DateUtil.isValidTime(trimmed)) {
                throw new ParseException(DateUtil.SEARCH_TIME_CONSTRAINTS);
            }
            if (prefix.equals(PREFIX_TAG) && !Tag.isValidTagName(trimmed)) {
                throw new ParseException(Tag.SEARCH_CONSTRAINTS);
            }
        }
        values.forEach(val -> predicate.setKeyword(prefix, val));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isAnyPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
