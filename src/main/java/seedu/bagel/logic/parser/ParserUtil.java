package seedu.bagel.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.bagel.commons.core.index.Index;
import seedu.bagel.commons.util.StringUtil;
import seedu.bagel.logic.commands.sort.SortCommand.SortRequirement;
import seedu.bagel.logic.parser.exceptions.ParseException;
import seedu.bagel.model.flashcard.Description;
import seedu.bagel.model.flashcard.FlashcardSet;
import seedu.bagel.model.flashcard.Link;
import seedu.bagel.model.flashcard.Title;
import seedu.bagel.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
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
     * Parses a {@code String title} into a {@code Title}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code title} is invalid.
     */
    public static Title parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        if (!Title.isValidTitle(trimmedTitle)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(trimmedTitle);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDesc = description.trim();
        if (!Description.isValidDescription(trimmedDesc)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDesc);
    }

    /**
     * Parses a {@code String set} into a {@code set}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code set} is invalid.
     */
    public static FlashcardSet parseSet(String setNumber) throws ParseException {
        requireNonNull(setNumber);
        String trimmedSetNumber = setNumber.trim();
        if (!FlashcardSet.isValidSetNumber(trimmedSetNumber)) {
            throw new ParseException(FlashcardSet.MESSAGE_CONSTRAINTS);
        }
        return new FlashcardSet(trimmedSetNumber);
    }

    /**
     * Parses a {@code String link} into a {@code Link}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code link} is invalid.
     */
    public static Link parseLink(String link) throws ParseException {
        requireNonNull(link);
        String trimmedLink = link.trim();
        if (!Link.isValidLink(trimmedLink)) {
            throw new ParseException(Link.MESSAGE_CONSTRAINTS);
        }
        return new Link(trimmedLink);
    }

    /**
     * Parses a {@code String requirement} into a {@code SortRequirement}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code requirement} is invalid.
     */
    public static SortRequirement parseRequirement(String req) throws ParseException {
        requireNonNull(req);
        String trimmedReq = req.trim();
        if (!SortRequirement.isValidRequirement(trimmedReq)) {
            throw new ParseException(SortRequirement.MESSAGE_CONSTRAINTS);
        }
        return new SortRequirement(trimmedReq);
    }
    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}
