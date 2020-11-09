package seedu.resireg.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.resireg.commons.core.index.Index;
import seedu.resireg.commons.util.StringUtil;
import seedu.resireg.logic.parser.exceptions.ParseException;
import seedu.resireg.model.alias.Alias;
import seedu.resireg.model.alias.CommandWord;
import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.room.roomtype.RoomType;
import seedu.resireg.model.student.Email;
import seedu.resireg.model.student.Name;
import seedu.resireg.model.student.Phone;
import seedu.resireg.model.student.StudentId;
import seedu.resireg.model.student.faculty.Faculty;
import seedu.resireg.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DAYS = "Number of days is not a positive integer.";


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
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String faculty} into a {@code Faculty}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code faculty} is invalid.
     */
    public static Faculty parseFaculty(String faculty) throws ParseException {
        requireNonNull(faculty);
        String trimmedFaculty = faculty.trim();
        if (!Faculty.isValidFaculty(trimmedFaculty)) {
            throw new ParseException(Faculty.MESSAGE_CONSTRAINTS);
        }
        return new Faculty(trimmedFaculty);
    }

    /**
     * Parses a {@code String studentId} into a {@code StudentId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code studentId} is invalid.
     */
    public static StudentId parseStudentId(String studentId) throws ParseException {
        requireNonNull(studentId);
        String trimmedStudentId = studentId.trim();
        if (!StudentId.isValidStudentId(trimmedStudentId)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }
        return new StudentId(trimmedStudentId);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
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
     * Parses {@code String floor} into a {@code floor}.
     * Leading and training whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code floor} is invalid.
     */
    public static Floor parseFloor(String floor) throws ParseException {
        requireNonNull(floor);
        String trimmedFloor = floor.trim();
        if (!Floor.isValidFloor(trimmedFloor)) {
            throw new ParseException(Floor.MESSAGE_CONSTRAINTS);
        }
        return new Floor(trimmedFloor);
    }

    /**
     * Parses {@code String roomNumber} into a {@code roomNumber}.
     * Leading and training whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code roomNumber} is invalid.
     */
    public static RoomNumber parseRoomNumber(String roomNumber) throws ParseException {
        requireNonNull(roomNumber);
        String trimmedRoomNumber = roomNumber.trim();
        if (!RoomNumber.isValidRoomNumber(trimmedRoomNumber)) {
            throw new ParseException(RoomNumber.MESSAGE_CONSTRAINTS);
        }
        return new RoomNumber(trimmedRoomNumber);
    }

    /**
     * Parses a {@code String commandWord} into a {@code CommandWord}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code commandWord} is invalid.
     */
    public static CommandWord parseCommandWord(String commandWord) throws ParseException {
        requireNonNull(commandWord);
        String trimmedCommandWord = commandWord.trim();
        if (!CommandWord.isValidCommandWord(trimmedCommandWord)) {
            throw new ParseException(CommandWord.MESSAGE_CONSTRAINTS);
        }
        return new CommandWord(trimmedCommandWord);
    }

    /**
     * Parses a {@code String alias} into a {@code Alias}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code alias} is invalid.
     */
    public static Alias parseAlias(String alias) throws ParseException {
        requireNonNull(alias);
        String trimmedAlias = alias.trim();
        if (!Alias.isValidAlias(trimmedAlias)) {
            throw new ParseException(Alias.MESSAGE_CONSTRAINTS);
        }
        return new Alias(trimmedAlias);
    }

    /**
     * Parses {@code String roomType} into a {@code RoomType}.
     * Leading and training whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code roomType} is invalid.
     */
    public static RoomType parseRoomType(String roomType) throws ParseException {
        requireNonNull(roomType);
        String trimmedRoomType = roomType.trim();
        if (!RoomType.isValidRoomType(trimmedRoomType)) {
            throw new ParseException(RoomType.MESSAGE_CONSTRAINTS);
        }
        return new RoomType(trimmedRoomType);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses a collection of strings to some other type using the given parser function.
     *
     * @param strings Strings to parse.
     * @param parser Parser function which parses strings into objects of type T.
     * @param <T> Type of output objects.
     * @return Set of objects of type T.
     * @throws ParseException If any of the strings cannot be parsed to T.
     */
    public static <T> Set<T> parseCollection(Collection<String> strings, ParserFunction<T> parser)
            throws ParseException {
        requireNonNull(strings);
        final Set<T> things = new HashSet<>();
        for (String s: strings) {
            things.add(parser.parse(s));
        }
        return things;
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static int parseDaysAsInt(String daysStoredFor) throws ParseException {
        String trimmedDays = daysStoredFor.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedDays)) {
            throw new ParseException(MESSAGE_INVALID_DAYS);
        }
        return Integer.parseInt(trimmedDays);
    }

    @FunctionalInterface
    public interface ParserFunction<T> {
        T parse(String string) throws ParseException;
    }


    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    public static Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
