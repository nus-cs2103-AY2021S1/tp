package seedu.flashcard.logic.parser;

import static seedu.flashcard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_FAV;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.flashcard.logic.commands.FilterCommand;
import seedu.flashcard.logic.parser.exceptions.ParseException;
import seedu.flashcard.model.flashcard.Category;
import seedu.flashcard.model.flashcard.CategoryEqualsKeywordsPredicate;
import seedu.flashcard.model.flashcard.FavouriteEqualsKeywordsPredicate;
import seedu.flashcard.model.flashcard.Rating;
import seedu.flashcard.model.flashcard.RatingEqualsKeywordsPredicate;
import seedu.flashcard.model.flashcard.TagsEqualKeywordsPredicate;
import seedu.flashcard.model.tag.Tag;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        Category category = null;
        Rating rating = null;
        Boolean isFavourite = null;
        Set<Tag> tags = null;

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_RATING, PREFIX_FAV, PREFIX_TAG);

        boolean areNoPrefixesPresent = !arePrefixesPresent(argMultimap, PREFIX_CATEGORY)
                && !arePrefixesPresent(argMultimap, PREFIX_RATING)
                && !arePrefixesPresent(argMultimap, PREFIX_FAV)
                && !arePrefixesPresent(argMultimap, PREFIX_TAG);
        if (areNoPrefixesPresent
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_CATEGORY)) {
            category = ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get());
        }
        if (arePrefixesPresent(argMultimap, PREFIX_RATING)) {
            rating = ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).get());
        }
        if (arePrefixesPresent(argMultimap, PREFIX_FAV)) {
            isFavourite = ParserUtil.parseFavourite(argMultimap.getValue(PREFIX_FAV).get());
        }
        if (arePrefixesPresent(argMultimap, PREFIX_TAG)) {
            tags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        }

        return new FilterCommand(new CategoryEqualsKeywordsPredicate(category),
                new RatingEqualsKeywordsPredicate(rating), new FavouriteEqualsKeywordsPredicate(isFavourite),
                new TagsEqualKeywordsPredicate(tags));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
