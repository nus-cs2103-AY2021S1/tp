package seedu.address.logic.parser.todolistparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.todolistcommands.FindTaskCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Date;
import seedu.address.model.task.FindTaskCriteria;
import seedu.address.model.task.NameContainsKeywordsPredicate;
import seedu.address.model.task.Priority;
import seedu.address.model.task.TaskMatchesDatePredicate;
import seedu.address.model.task.TaskMatchesPriorityPredicate;

/**
 * Parses input arguments and creates a new FindTaskCommand object
 */
public class FindTaskParser implements Parser<FindTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTaskCommand
     * and returns a FindTaskCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public FindTaskCommand parse(String args) throws ParseException {
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(args, PREFIX_NAME, PREFIX_DATE, PREFIX_PRIORITY);
        ArgumentMultimap argMultiMap = tokenizer.tokenize();
        if (!isAtLeastOnePrefixPresent(argMultiMap, PREFIX_NAME, PREFIX_DATE, PREFIX_PRIORITY)
                || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
        }

        FindTaskCriteria findTaskCriteria = new FindTaskCriteria();

        if (argMultiMap.getValue(PREFIX_NAME).isPresent()) {
            String keywords = argMultiMap.getValue(PREFIX_NAME).get();
            List<String> keywordsAsList = parseSearchKeywords(keywords);
            NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(keywordsAsList);
            findTaskCriteria.addPredicate(namePredicate);
        }

        if (argMultiMap.getValue(PREFIX_DATE).isPresent()) {
            String dateAsString = argMultiMap.getValue(PREFIX_DATE).get();
            Date searchDate = parseSearchDate(dateAsString);
            TaskMatchesDatePredicate datePredicate = new TaskMatchesDatePredicate(searchDate);
            findTaskCriteria.addPredicate(datePredicate);
        }

        if (argMultiMap.getValue(PREFIX_PRIORITY).isPresent()) {
            String priorityAsString = argMultiMap.getValue(PREFIX_PRIORITY).get();
            Priority searchPriority = parseSearchPriority(priorityAsString);
            TaskMatchesPriorityPredicate priorityPredicate = new TaskMatchesPriorityPredicate(searchPriority);
            findTaskCriteria.addPredicate(priorityPredicate);
        }

        return new FindTaskCommand(findTaskCriteria);
    }

    /**
     * Parses the String containing the search keywords provided by the user into a list of keywords.
     *
     * @param keywords String containing search keywords provided by the user.
     * @return List of search keywords.
     * @throws ParseException If no valid keyword was provided by the user.
     */
    private List<String> parseSearchKeywords(String keywords) throws ParseException {
        requireNonNull(keywords);
        if (keywords.isBlank()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
        } else {
            String[] keywordArray = keywords.split("\\s+");
            return Arrays.asList(keywordArray);
        }
    }

    /**
     * Parses the search date provided by the user if it exists.
     *
     * @param date String containing the search date.
     * @return Date object representing the search date. If no date was provided, null is returned.
     * @throws ParseException If the date provided is incorrect or invalid.
     */
    private Date parseSearchDate(String date) throws ParseException {
        requireNonNull(date);
        if (date.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
        } else {
            return ParserUtil.parseTaskDate(date);
        }
    }

    /**
     * Parses the search priority criteria provided by the user if it exists.
     *
     * @param priority String containing the search priority criteria.
     * @return Priority object representing the search priority. If no priority was provided, null is returned.
     * @throws ParseException If the priority provided is incorrect or invalid.
     */
    private Priority parseSearchPriority(String priority) throws ParseException {
        requireNonNull(priority);
        if (priority.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
        } else {
            return ParserUtil.parseTaskPriority(priority);
        }
    }

    /**
     * Determines if at least one of the prefixes does not contain an empty {@code Optional} value in the given
     * {@code ArgumentMultimap}.
     *
     * @param argumentMultimap ArgumentMultimap object containing the mapping of Prefixes to their respective arguments.
     * @param prefixes Prefixes to check for in the ArgumentMultimap.
     * @return True if at least one prefix has an argument mapped to it, false otherwise.
     */
    private static boolean isAtLeastOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
