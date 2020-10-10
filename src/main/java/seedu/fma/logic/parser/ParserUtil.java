package seedu.fma.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.fma.commons.core.index.Index;
import seedu.fma.commons.util.StringUtil;
import seedu.fma.logic.parser.exceptions.ParseException;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.exercise.exceptions.ExerciseNotFoundException;
import seedu.fma.model.log.Address;
import seedu.fma.model.log.Comment;
import seedu.fma.model.log.Email;
import seedu.fma.model.log.Phone;
import seedu.fma.model.log.Rep;
import seedu.fma.model.tag.Tag;
import seedu.fma.model.util.Name;

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
     * Parses an {@code String exerciseName} into an {@code Exercise}.
     *
     * @throws ParseException if the given {@code exerciseName} is invalid.
     * @throws ExerciseNotFoundException if the given {@code exerciseName} does not match any existing Exercise.
     */
    public static Exercise parseExercise(String exerciseName) throws ParseException {
        Name name = parseName(exerciseName);
        try {
            return Exercise.find(name);
        } catch (ExerciseNotFoundException e) {
            throw new ParseException(Exercise.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String comment} into a {@code Comment}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code comment} is invalid.
     */
    public static Comment parseComment(String comment) throws ParseException {
        requireNonNull(comment);
        String trimmedComment = comment.trim();
        if (!Comment.isValidComment(comment)) {
            throw new ParseException(Comment.MESSAGE_CONSTRAINTS);
        }
        return new Comment(trimmedComment);
    }

    /**
     * Parses a {@code String rep} into a {@code Rep}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code rep} is invalid.
     */
    public static Rep parseRep(String rep) throws ParseException {
        requireNonNull(rep);
        String trimmedRep = rep.trim();
        if (!Rep.isValidRep(rep)) {
            throw new ParseException(Rep.MESSAGE_CONSTRAINTS);
        }
        return new Rep(trimmedRep);
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
