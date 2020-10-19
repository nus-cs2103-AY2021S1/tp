package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Date;
import seedu.address.model.task.Priority;
import seedu.address.model.task.TaskSearchCriteriaPredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Parses input arguments and creates a new FindTaskCommand object
 */
public class FindTaskParser implements Parser<FindTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTaskCommand parse(String args) throws ParseException {
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(args, PREFIX_NAME,
                PREFIX_DATE, PREFIX_PRIORITY);
        ArgumentMultimap argMultiMap = tokenizer.tokenize();
        if (!isAtLeastOnePrefixPresent(argMultiMap, PREFIX_NAME, PREFIX_DATE, PREFIX_PRIORITY)
                || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
        }
        String keywords = argMultiMap.getValue(PREFIX_NAME).orElse("");
        List<String> searchKeywordList = convertKeywordsIntoList(keywords);

        String dateAsString = argMultiMap.getValue(PREFIX_DATE).orElse("");
        Date searchDate = parseSearchDate(dateAsString);

        String priorityAsString = argMultiMap.getValue(PREFIX_PRIORITY).orElse("");
        Priority searchPriority = parseSearchPriority(priorityAsString);

        TaskSearchCriteriaPredicate predicate = new TaskSearchCriteriaPredicate(searchKeywordList,
                searchDate, searchPriority);

        return new FindTaskCommand(predicate);
    }

    /**
     * Converts the String containing the search keywords provided by the user into a list of keywords.
     *
     * @param keywords String containing search keywords provided by the user.
     * @return Empty list if no search keywords was provided, otherwise a list of search keywords is returned.
     */
    public List<String> convertKeywordsIntoList(String keywords) {
        if (keywords.isEmpty()) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(keywords.split("\\s+"));
        }
    }

    /**
     * Parses the search date provided by the user if it exists.
     *
     * @param date String containing the search date.
     * @return Date object representing the search date. If no date was provided, null is returned.
     * @throws ParseException If the date provided is incorrect or invalid.
     */
    public Date parseSearchDate(String date) throws ParseException {
        if (date.isEmpty()) {
            return null;
        } else {
            return ParserUtil.parseTaskDate(date);
        }
    }

    /**
     * Parses the search priority provided by the user if it exists.
     *
     * @param priority String containing the search priority.
     * @return Priority object representing the search priority. If no priority was provided, null is returned.
     * @throws ParseException If the priority provided is incorrect or invalid.
     */
    public Priority parseSearchPriority(String priority) throws ParseException {
        if (priority.isEmpty()) {
            return null;
        } else {
            return ParserUtil.parseTaskPriority(priority);
        }
    }

    /**
     * Returns true if at least one of the prefixes does not contains an empty {@code Optional} value in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isAtLeastOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
