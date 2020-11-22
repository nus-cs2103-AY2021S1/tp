package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Description;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;
import seedu.address.model.person.Address;
import seedu.address.model.person.Block;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GenderMatchPredicate;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.MatriculationNumberMatchPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Room;
import seedu.address.model.person.RoomInBlockPredicate;
import seedu.address.model.person.RoomInFloorPredicate;
import seedu.address.model.person.RoomMatchesNumberPredicate;
import seedu.address.model.studentgroup.StudentGroup;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    private static final Logger logger = LogsCenter.getLogger(ParserUtil.class);
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
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
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
     * Parses a {@code String studentGroup} into a {@code StudentGroup}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code studentGroup} is invalid.
     */
    public static StudentGroup parseStudentGroup(String studentGroup) throws ParseException {
        requireNonNull(studentGroup);
        String trimmedStudentGroup = studentGroup.trim();
        if (!StudentGroup.isValidStudentGroupName(trimmedStudentGroup)) {
            throw new ParseException(StudentGroup.MESSAGE_CONSTRAINTS);
        }
        return new StudentGroup(trimmedStudentGroup);
    }

    /**
     * Parses {@code Collection<String> studentGroups} into a {@code Set<StudentGroup>}.
     *
     * @throws ParseException if the {@code studentGroupName} is invalid while iterating studentGroups.
     */
    public static Set<StudentGroup> parseStudentGroups(Collection<String> studentGroups) throws ParseException {
        requireNonNull(studentGroups);
        final Set<StudentGroup> studentGroupSet = new HashSet<>();
        for (String studentGroupName : studentGroups) {
            studentGroupSet.add(parseStudentGroup(studentGroupName));
        }
        return studentGroupSet;
    }

    /**
     * Parses {@code Block block} into a {@code Remark}.
     */
    public static Block parseBlock(String block) throws ParseException {
        requireNonNull(block);
        String trimmedBlock = block.trim();
        if (!Block.isValidBlock(trimmedBlock)) {
            throw new ParseException(Block.getMessageConstraints());
        }
        return new Block(trimmedBlock);
    }

    /**
     * Parses {@code Room room} into a {@code Room}.
     */
    public static Room parseRoom(String room) throws ParseException {
        requireNonNull(room);
        String trimmedRoom = room.trim();
        if (!Room.isValidRoom(trimmedRoom)) {
            throw new ParseException(Room.getMessageConstraints());
        }
        return new Room(trimmedRoom);
    }
    /**
     * Parses a {@code String gender} into a {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses a {@code String matriculationNumber} into an {@code MatriculationNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code matriculationNumber} is invalid.
     */
    public static MatriculationNumber parseMatriculationNumber(String matriculationNumber) throws ParseException {
        requireNonNull(matriculationNumber);
        String trimmedMatriculationNumber = matriculationNumber.trim();
        if (!MatriculationNumber.isValidMatriculationNumber(trimmedMatriculationNumber)) {
            throw new ParseException(MatriculationNumber.MESSAGE_CONSTRAINTS);
        }
        return new MatriculationNumber(trimmedMatriculationNumber);
    }

    /**
     * Parses a {@code String eventName} into an {@code EventName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code eventName} is invalid.
     */
    public static EventName parseEventName(String eventName) throws ParseException {
        requireNonNull(eventName);
        String trimmedEventName = eventName.trim();
        if (!EventName.isValidEventName(trimmedEventName)) {
            throw new ParseException(EventName.MESSAGE_CONSTRAINTS);
        }
        return new EventName(trimmedEventName);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String eventDate} into a {@code EventDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code eventDate} is invalid.
     */
    public static EventDate parseEventDate(String eventDate) throws ParseException {
        requireNonNull(eventDate);
        String trimmedEventDate = eventDate.trim();
        if (!EventDate.isValidEventDate(eventDate)) {
            throw new ParseException(EventDate.MESSAGE_CONSTRAINTS);
        }
        return new EventDate(trimmedEventDate);
    }

    /**
     * Parses a {@code String location} into a {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        String trimmedLocation = location.trim();
        if (!Location.isValidLocation(location)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedLocation);
    }

    /**
     * Parses a {@code String block} into a {@code RoomInBlockPredicate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code block} is invalid.
     */
    public static RoomInBlockPredicate parseRoomInBlockPredicate(String block) throws ParseException {
        logger.log(Level.INFO, "parsing RoomInBlockPredicate");
        Block searchedBlock = parseBlock(block);
        return new RoomInBlockPredicate(searchedBlock);
    }

    /**
     * Parses a {@code String floor} into a {@code RoomInFloorPredicate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code floor} is invalid.
     */
    public static RoomInFloorPredicate parseRoomInFloorPredicate(String floor) throws ParseException {
        logger.log(Level.INFO, "parsing RoomInFloorPredicate");
        requireNonNull(floor);
        String trimmedFloor = floor.trim();
        if (!RoomInFloorPredicate.isValidFloorNumber(trimmedFloor)) {
            throw new ParseException(RoomInFloorPredicate.MESSAGE_CONSTRAINTS);
        }
        return new RoomInFloorPredicate(trimmedFloor);
    }

    /**
     * Parses {@code String keywords} into a {@code NameContainsKeywordsPredicate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the {@code keywords} are empty.
     */
    public static NameContainsKeywordsPredicate parseNameContainsKeywordPredicate(
            String keywords) throws ParseException {
        logger.log(Level.INFO, "adding name keywords to filter");
        requireNonNull(keywords);
        String trimmedKeywords = keywords.trim();
        if (trimmedKeywords.isEmpty()) {
            logger.log(Level.WARNING, "empty keywords for name");
            throw new ParseException(FindCommand.MESSAGE_EMPTY_KEYWORD);
        }
        String[] nameKeywords = trimmedKeywords.split("\\s+");
        assert nameKeywords.length > 0 : "there should be some keywords";
        return new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords));
    }

    /**
     * Parses {@code String roomNumber} into a {@code RoomMatchesNumberPredicate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code roomNumber} is invalid.
     */
    public static RoomMatchesNumberPredicate parseRoomMatchesNumberPredicate(
            String roomNumber) throws ParseException {
        logger.log(Level.INFO, "parsing RoomMatchesNumberPredicate");
        requireNonNull(roomNumber);
        String trimmedRoomNumber = roomNumber.trim();
        if (!RoomMatchesNumberPredicate.isValidRoomNumber(trimmedRoomNumber)) {
            throw new ParseException(RoomMatchesNumberPredicate.MESSAGE_CONSTRAINTS);
        }
        return new RoomMatchesNumberPredicate(trimmedRoomNumber);
    }

    /**
     * Parses a {@code String matriculationNumberString} into a {@code MatriculationNumberMatchPredicate}.
     *
     * @throws ParseException if the given {@code matriculationNumberString} is invalid.
     */
    public static MatriculationNumberMatchPredicate parseMatriculationNumberMatchPredicate(
            String matriculationNumberString) throws ParseException {
        logger.log(Level.INFO, "parsing MatriculationNumberMatchPredicate");
        MatriculationNumber matriculationNumber = parseMatriculationNumber(matriculationNumberString);
        return new MatriculationNumberMatchPredicate(matriculationNumber);
    }


    /**
     * Parses a {@code String gender} into a {@code GenderMatchPredicate}.
     *
     * @throws ParseException if the given {@code gender} is invalid.
     */
    public static GenderMatchPredicate parseGenderMatchPredicate(String gender) throws ParseException {
        logger.log(Level.INFO, "parsing GenderMatchPredicate");
        Gender predicateGender = parseGender(gender);
        return new GenderMatchPredicate(predicateGender);
    }

}
