package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.DeadlineContainsKeywordsPredicate;
import seedu.address.model.assignment.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.assignment.NameContainsKeywordsPredicate;
import seedu.address.model.assignment.PriorityContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private static boolean moreThanOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        long countPrefixesPresent = Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).count();
        return countPrefixesPresent > 1;
    }

    private FindCommand findByName(String[] keywords) throws ParseException {
        for (String keyword : keywords) {
            ParserUtil.parseName(keyword);
        }
        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
    }

    private FindCommand findByModuleCode(String[] keywords) throws ParseException {
        for (String keyword : keywords) {
            ParserUtil.parseModuleCode(keyword);
        }
        return new FindCommand(new ModuleCodeContainsKeywordsPredicate(Arrays.asList(keywords)));
    }

    private static void tryParseDateFormat(String keyword) throws ParseException {
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd-MM-uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalDate date = LocalDate.parse(keyword, inputFormat);
            date.format(inputFormat);
        } catch (DateTimeException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.INVALID_DATE_OR_TIME_MESSAGE));
        }
    }

    private static void tryParseTimeFormat(String keyword) throws ParseException {
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("HHmm")
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalTime time = LocalTime.parse(keyword, inputFormat);
            time.format(inputFormat);
        } catch (DateTimeException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.INVALID_DATE_OR_TIME_MESSAGE));
        }
    }

    private FindCommand findByDeadline(String[] keywords) throws ParseException {
        requireNonNull(keywords);
        for (String keyword : keywords) {
            boolean isDateFormat = keyword.matches("^\\d{2}-\\d{2}-\\d{4}$");
            boolean isTimeFormat = keyword.matches("^\\d{4}$");

            if (isDateFormat) {
                FindCommandParser.tryParseDateFormat(keyword);
            } else if (isTimeFormat) {
                FindCommandParser.tryParseTimeFormat(keyword);
            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.INVALID_DATE_OR_TIME_MESSAGE));
            }
        }
        return new FindCommand(new DeadlineContainsKeywordsPredicate(Arrays.asList(keywords)));
    }

    private FindCommand findByPriority(String[] keywords) throws ParseException {
        for (String keyword : keywords) {
            ParserUtil.parsePriority(keyword);
        }
        return new FindCommand(new PriorityContainsKeywordsPredicate(Arrays.asList(keywords)));
    }

    private String[] getKeywords(Prefix prefix, ArgumentMultimap argMultimap) {
        assert argMultimap.getValue(prefix).isPresent();
        return argMultimap.getValue(prefix).get().split("\\s+");
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] keywords;
        String trimmedArgs = args.trim();
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DEADLINE, PREFIX_MODULE_CODE, PREFIX_PRIORITY);

        boolean isPreambleMissing = argMultimap.getPreamble().isEmpty();
        boolean isPrefixNamePresent = argMultimap.getValue(PREFIX_NAME).isPresent();
        boolean isPrefixDeadlinePresent = argMultimap.getValue(PREFIX_DEADLINE).isPresent();
        boolean isPrefixModuleCodePresent = argMultimap.getValue(PREFIX_MODULE_CODE).isPresent();
        boolean isPrefixPriorityPresent = argMultimap.getValue(PREFIX_PRIORITY).isPresent();
        boolean isMoreThanOnePrefixPresent = moreThanOnePrefixPresent(
                argMultimap, PREFIX_NAME, PREFIX_MODULE_CODE, PREFIX_DEADLINE, PREFIX_PRIORITY);

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.NO_PREFIX_AND_KEYWORD));
        } else if (isMoreThanOnePrefixPresent) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MORE_THAN_ONE_PREFIX_MESSAGE));
        } else if (isPrefixNamePresent && isPreambleMissing) {
            keywords = getKeywords(PREFIX_NAME, argMultimap);
            return findByName(keywords);
        } else if (isPrefixModuleCodePresent && isPreambleMissing) {
            keywords = getKeywords(PREFIX_MODULE_CODE, argMultimap);
            return findByModuleCode(keywords);
        } else if (isPrefixDeadlinePresent && isPreambleMissing) {
            keywords = getKeywords(PREFIX_DEADLINE, argMultimap);
            return findByDeadline(keywords);
        } else if (isPrefixPriorityPresent && isPreambleMissing) {
            keywords = getKeywords(PREFIX_PRIORITY, argMultimap);
            return findByPriority(keywords);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
}
