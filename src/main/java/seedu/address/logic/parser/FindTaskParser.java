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
        // String trimmedArgs = args.trim();
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(args, PREFIX_NAME,
                PREFIX_DATE, PREFIX_PRIORITY);
        ArgumentMultimap argMultiMap = tokenizer.tokenize();
        if (!isAtLeastOnePrefixPresent(argMultiMap, PREFIX_NAME, PREFIX_DATE, PREFIX_PRIORITY)
                || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
        }

        String keywords = argMultiMap.getValue(PREFIX_NAME).orElse("");
        List<String> taskNameKeywords = keywords.isBlank()
                ? new ArrayList<>()
                : Arrays.asList(keywords.split("\\s+"));
        Date taskDate = ParserUtil.parseTaskDate(argMultiMap.getValue(PREFIX_DATE).get());
        Priority taskPriority = ParserUtil.parseTaskPriority(argMultiMap.getValue(PREFIX_PRIORITY).get());

        TaskSearchCriteriaPredicate predicate = new TaskSearchCriteriaPredicate(taskNameKeywords,
                taskDate, taskPriority);

        return new FindTaskCommand(predicate);
    }

    /**
     * Returns true if at least one of the prefixes does not contains an empty {@code Optional} value in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isAtLeastOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
