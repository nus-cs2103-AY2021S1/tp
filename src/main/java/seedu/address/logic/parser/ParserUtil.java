package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.deck.DeckName;
import seedu.address.model.deck.entry.Translation;
import seedu.address.model.deck.entry.Word;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String word} into a {@code Word}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code word} is invalid.
     */
    public static Word parseWord(String word) throws ParseException {
        requireNonNull(word);
        String trimmedWord = word.trim();
        if (!Word.isValidLength(trimmedWord)) {
            throw new ParseException(Word.LENGTH_CONSTRAINTS);
        }
        if (!Word.isValidWord(trimmedWord)) {
            throw new ParseException(Word.MESSAGE_CONSTRAINTS);
        }
        return new Word(trimmedWord);
    }

    /**
     * Parses a {@code String translation} into an {@code Translation}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code translation} is invalid.
     */
    public static Translation parseTranslation(String translation) throws ParseException {
        requireNonNull(translation);
        String trimmedTranslation = translation.trim();
        if (!Translation.isValidLength(trimmedTranslation)) {
            throw new ParseException(Translation.LENGTH_CONSTRAINTS);
        }
        if (!Translation.isValidTranslation(trimmedTranslation)) {
            throw new ParseException(Translation.MESSAGE_CONSTRAINTS);
        }
        return new Translation(trimmedTranslation);
    }

    /**
     * Parses a {@code String name} into an {@code DeckName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static DeckName parseDeckName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!DeckName.isValidLength(trimmedName)) {
            throw new ParseException(DeckName.LENGTH_CONSTRAINTS);
        }
        if (!DeckName.isValidDeckName(trimmedName)) {
            throw new ParseException(DeckName.MESSAGE_CONSTRAINTS);
        }
        return new DeckName(trimmedName);
    }
}
