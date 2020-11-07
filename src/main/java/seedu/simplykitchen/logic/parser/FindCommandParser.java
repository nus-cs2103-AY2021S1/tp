package seedu.simplykitchen.logic.parser;

import static seedu.simplykitchen.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import seedu.simplykitchen.commons.core.LogsCenter;
import seedu.simplykitchen.logic.commands.FindCommand;
import seedu.simplykitchen.logic.parser.exceptions.ParseException;
import seedu.simplykitchen.model.food.DescriptionContainsKeywordsPredicate;
import seedu.simplykitchen.model.food.ExpiryDate;
import seedu.simplykitchen.model.food.ExpiryDateSearchPredicate;
import seedu.simplykitchen.model.food.Priority;
import seedu.simplykitchen.model.food.PrioritySearchPredicate;
import seedu.simplykitchen.model.tag.Tag;
import seedu.simplykitchen.model.tag.TagSearchPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_PRIORITY, PREFIX_EXPIRY_DATE, PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            logger.info("Invalid Preamble in Find command");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        Optional<DescriptionContainsKeywordsPredicate> descriptionContainsKeywordsPredicate =
                getDescriptionContainsKeywordsPredicate(argMultimap);
        Optional<PrioritySearchPredicate> prioritySearchPredicate = getPrioritySearchPredicate(argMultimap);
        Optional<ExpiryDateSearchPredicate> expiryDateSearchPredicate = getExpiryDateSearchPredicate(argMultimap);
        Optional<TagSearchPredicate> tagSearchPredicate = getTagSearchPredicate(argMultimap);

        if (descriptionContainsKeywordsPredicate.isEmpty()
                && prioritySearchPredicate.isEmpty()
                && expiryDateSearchPredicate.isEmpty()
                && tagSearchPredicate.isEmpty()) {
            logger.info("Missing all fields in Find command");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(descriptionContainsKeywordsPredicate, prioritySearchPredicate,
                expiryDateSearchPredicate, tagSearchPredicate);
    }

    private Optional<DescriptionContainsKeywordsPredicate> getDescriptionContainsKeywordsPredicate(
            ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
            String trimmedArgs = description.trim();
            String[] descriptionKeywords = trimmedArgs.split("\\s+");
            for (String keyword : descriptionKeywords) {
                ParserUtil.parseDescription(keyword);
            }
            return Optional.of(new DescriptionContainsKeywordsPredicate(Arrays.asList(descriptionKeywords)));
        } else {
            return Optional.empty();
        }
    }

    private Optional<PrioritySearchPredicate> getPrioritySearchPredicate(ArgumentMultimap argMultimap)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            Priority priority = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
            return Optional.of(new PrioritySearchPredicate(priority.value));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ExpiryDateSearchPredicate> getExpiryDateSearchPredicate(ArgumentMultimap argMultimap)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_EXPIRY_DATE).isPresent()) {
            ExpiryDate expiryDate = ParserUtil.parseExpiryDate(argMultimap.getValue(PREFIX_EXPIRY_DATE).get());
            return Optional.of(new ExpiryDateSearchPredicate(expiryDate.value));
        } else {
            return Optional.empty();
        }
    }

    private Optional<TagSearchPredicate> getTagSearchPredicate(ArgumentMultimap argMultimap)
            throws ParseException {
        if (argMultimap.getAllValues(PREFIX_TAG).size() != 0) {
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            return Optional.of(new TagSearchPredicate(tagList));
        } else {
            return Optional.empty();
        }
    }

}
