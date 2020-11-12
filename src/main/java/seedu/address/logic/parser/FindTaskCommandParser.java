package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_SEARCH_PHRASE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.TaskContainsKeywordsPredicate;
import seedu.address.model.task.Title;
import seedu.address.model.task.deadline.Status;


/**
 * Parses input arguments and creates a new FindTaskCommand object
 */
public class FindTaskCommandParser implements Parser<FindTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTaskCommand
     * and returns a FindTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TITLE,
                                            PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_STATUS, PREFIX_TAG);

        if (!Parser.isAnyPrefixPresent(argMultimap, PREFIX_TITLE,
                PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_STATUS, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "", FindTaskCommand.MESSAGE_USAGE));
        }

        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        setKeyword(PREFIX_TITLE, argMultimap, predicate);
        setKeyword(PREFIX_DESCRIPTION, argMultimap, predicate);
        setKeyword(PREFIX_DATE, argMultimap, predicate);
        setKeyword(PREFIX_STATUS, argMultimap, predicate);
        setKeyword(PREFIX_TAG, argMultimap, predicate);

        return new FindTaskCommand(predicate);
    }

    private void setKeyword(Prefix prefix,
                            ArgumentMultimap argMultimap,
                            TaskContainsKeywordsPredicate predicate) throws ParseException {
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
            if (prefix.equals(PREFIX_DATE) && !DateTimeUtil.isValidDate(trimmed)) {
                throw new ParseException(DateTimeUtil.SEARCH_DATE_CONSTRAINTS);
            }
            if (prefix.equals(PREFIX_STATUS) && !Status.isValidStatus(trimmed)) {
                throw new ParseException(Status.SEARCH_CONSTRAINTS);
            } //now the status only have a boolean value of isCompleted.
            if (prefix.equals(PREFIX_TAG) && !Tag.isValidTagName(trimmed)) {
                throw new ParseException(Tag.SEARCH_CONSTRAINTS);
            }
        }
        values.forEach(val -> predicate.setKeyword(prefix, val));
    }
}
