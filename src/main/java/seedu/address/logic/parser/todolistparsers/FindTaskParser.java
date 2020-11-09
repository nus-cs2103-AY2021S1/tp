package seedu.address.logic.parser.todolistparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.todolistcommands.FindTaskCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.FindTaskCriteria;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskContainsTagsPredicate;
import seedu.address.model.task.TaskMatchesDatePredicate;
import seedu.address.model.task.TaskMatchesPriorityPredicate;
import seedu.address.model.task.TaskMatchesStatusPredicate;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindTaskCommand object.
 */
public class FindTaskParser implements Parser<FindTaskCommand> {

    private final Logger logger = LogsCenter.getLogger(FindTaskParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the FindTaskCommand
     * and returns a FindTaskCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public FindTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        logger.info("Parsing command arguments: " + args);

        ArgumentTokenizer tokenizer = new ArgumentTokenizer(args,
                PREFIX_NAME, PREFIX_DATE, PREFIX_PRIORITY, PREFIX_TAG, PREFIX_STATUS);
        ArgumentMultimap argMultiMap = tokenizer.tokenize();

        if (!isAtLeastOnePrefixPresent(argMultiMap, PREFIX_NAME, PREFIX_DATE,
                PREFIX_PRIORITY, PREFIX_TAG, PREFIX_STATUS) || !argMultiMap.getPreamble().isBlank()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
        }

        FindTaskCriteria findTaskCriteria = new FindTaskCriteria();

        List<Prefix> prefixes = Arrays.asList(PREFIX_NAME, PREFIX_DATE,
                PREFIX_PRIORITY, PREFIX_STATUS, PREFIX_TAG);

        for (Prefix prefix : prefixes) {
            if (argMultiMap.getValue(prefix).isPresent()) {
                Predicate<Task> predicate = initialisePredicate(prefix, argMultiMap);
                findTaskCriteria.addPredicate(predicate);
            }
        }

        return new FindTaskCommand(findTaskCriteria);
    }

    /**
     * Creates a new Predicate object using the prefix arguments found in the
     * ArgumentMultimap.
     *
     * @param prefix Prefix object which is mapped to the required arguments.
     * @param argMultimap ArgumentMultimap object containing the mapping of Prefixes to their respective arguments.
     * @return Predicate object created using the prefix argument.
     * @throws ParseException If the user input does not conform the expected format.
     */
    private Predicate<Task> initialisePredicate(Prefix prefix, ArgumentMultimap argMultimap) throws ParseException {

        Predicate<Task> predicate = null;

        if (prefix.equals(PREFIX_NAME)) {
            List<String> keywords = ParserUtil.parseSearchKeywords(argMultimap.getValue(PREFIX_NAME).get());
            predicate = new TaskNameContainsKeywordsPredicate(keywords);
        } else if (prefix.equals(PREFIX_DATE)) {
            Date searchDate = ParserUtil.parseTaskDate(argMultimap.getValue(PREFIX_DATE).get());
            predicate = new TaskMatchesDatePredicate(searchDate);
        } else if (prefix.equals(PREFIX_PRIORITY)) {
            Priority searchPriority = ParserUtil.parseTaskPriority(argMultimap.getValue(PREFIX_PRIORITY).get());
            predicate = new TaskMatchesPriorityPredicate(searchPriority);
        } else if (prefix.equals(PREFIX_STATUS)) {
            Status searchStatus = ParserUtil.parseTaskStatus(argMultimap.getValue(PREFIX_STATUS).get());
            predicate = new TaskMatchesStatusPredicate(searchStatus);
        } else if (prefix.equals(PREFIX_TAG)) {
            List<String> tagKeywords = ParserUtil.parseSearchKeywords(argMultimap.getValue(PREFIX_TAG).get());
            Set<Tag> searchTags = ParserUtil.parseTags(tagKeywords);
            predicate = new TaskContainsTagsPredicate(searchTags);
        }

        requireNonNull(predicate);
        return predicate;
    }

    /**
     * Determines if at least one of the prefixes does not contain an empty {@code Optional} value in the given
     * {@code ArgumentMultimap}.
     *
     * @param argMultimap ArgumentMultimap object containing the mapping of Prefixes to their respective arguments.
     * @param prefixes Prefixes to check for in the ArgumentMultimap.
     * @return True if at least one prefix has an argument mapped to it, false otherwise.
     */
    private static boolean isAtLeastOnePrefixPresent(ArgumentMultimap argMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argMultimap.getValue(prefix).isPresent());
    }
}
