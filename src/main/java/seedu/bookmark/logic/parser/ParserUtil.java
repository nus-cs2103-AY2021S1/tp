package seedu.bookmark.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.commons.util.StringUtil;
import seedu.bookmark.logic.parser.exceptions.ParseException;
import seedu.bookmark.model.book.Bookmark;
import seedu.bookmark.model.book.Genre;
import seedu.bookmark.model.book.Goal;
import seedu.bookmark.model.book.Name;
import seedu.bookmark.model.book.Note;
import seedu.bookmark.model.book.TotalPages;
import seedu.bookmark.model.tag.Tag;

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
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String genre} into an {@code Genre}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Genre parseGenre(String genre) throws ParseException {
        requireNonNull(genre);
        String trimmedGenre = genre.trim();
        if (!Genre.isValidGenre(trimmedGenre)) {
            throw new ParseException(Genre.MESSAGE_CONSTRAINTS);
        }
        return new Genre(trimmedGenre);
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

    /**
     * Parses {@code String totalPages} into a {@code TotalPages}.
     */
    public static TotalPages parseTotalPages(String totalPages) throws ParseException {
        requireNonNull(totalPages);
        String trimmedTotalPages = totalPages.trim();
        if (!TotalPages.isValidTotalPages(trimmedTotalPages)) {
            throw new ParseException(TotalPages.MESSAGE_CONSTRAINTS);
        }
        trimmedTotalPages = StringUtil.trimLeadingZeroes(trimmedTotalPages);
        return new TotalPages(trimmedTotalPages);
    }

    /**
     * Parses {@code Optional<String> bookmark} into a {@code Bookmark}
     */
    public static Bookmark parseBookmark(Optional<String> bookmark) throws ParseException {
        if (bookmark.isEmpty()) {
            return new Bookmark();
        } else {
            String trimmedBookmark = bookmark.get();
            trimmedBookmark = trimmedBookmark.trim();
            if (!Bookmark.isValidBookmark(trimmedBookmark)) {
                throw new ParseException(Bookmark.MESSAGE_CONSTRAINTS);
            }
            trimmedBookmark = StringUtil.trimLeadingZeroes(trimmedBookmark);
            return new Bookmark(trimmedBookmark);
        }
    }

    /**
     * Checks input, throws ParseException if any argument does not match the requirements
     * Else, return a valid Goal object.
     * @param page page in goal
     * @param deadline deadline in goal
     * @return new Goal object
     * @throws ParseException with MESSAGE_USAGE message
     */
    public static Goal parseGoal(String page, String deadline) throws ParseException {
        requireNonNull(page, deadline);
        String trimmedPage = page.trim();
        String trimmedDeadline = deadline.trim();
        if (!Goal.isValidGoal(trimmedPage, trimmedDeadline)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Goal.MESSAGE_CONSTRAINTS));
        }
        return new Goal(trimmedPage, trimmedDeadline);
    }

    /**
     * Parses {@code String title} and {@code String text} into a {@code Note}
     */
    public static Note parseNote(String title, String text) throws ParseException {
        requireNonNull(title);
        requireNonNull(text);
        String trimmedTitle = title.trim();
        String trimmedText = text.trim();

        if (!Note.isValidNote(trimmedTitle, trimmedText)) {
            throw new ParseException(Note.MESSAGE_CONSTRAINTS);
        }
        return new Note(trimmedTitle, trimmedText);
    }
}
