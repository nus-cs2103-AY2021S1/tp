package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentDate;
import seedu.address.model.appointment.AppointmentId;
import seedu.address.model.appointment.AppointmentStatus;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.client.Address;
import seedu.address.model.person.client.ClientId;
import seedu.address.model.person.hairdresser.HairdresserId;
import seedu.address.model.person.hairdresser.Title;
import seedu.address.model.specialisation.Specialisation;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    /**
     * Parses a {@code String clientId} into a {@code ClientId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code clientId} is invalid.
     */
    public static ClientId parseClientId(String clientId) throws ParseException {
        requireNonNull(clientId);
        String trimmedClientId = clientId.trim();
        if (!ClientId.isValidId(trimmedClientId)) {
            throw new ParseException(ClientId.MESSAGE_CONSTRAINTS);
        }
        return new ClientId(trimmedClientId);
    }

    /**
     * Parses a {@code String hairdresserId} into a {@code HairdresserId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code clientId} is invalid.
     */
    public static HairdresserId parseHairdresserId(String hairdresserId) throws ParseException {
        requireNonNull(hairdresserId);
        String trimmedHairdresserId = hairdresserId.trim();
        if (!HairdresserId.isValidId(trimmedHairdresserId)) {
            throw new ParseException(HairdresserId.MESSAGE_CONSTRAINTS);
        }
        return new HairdresserId(trimmedHairdresserId);
    }

    /**
     * Parses a {@code String appointmentId} into a {@code AppointmentId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code clientId} is invalid.
     */
    public static AppointmentId parseAppointmentId(String appointmentId) throws ParseException {
        requireNonNull(appointmentId);
        String trimmedAppointmentId = appointmentId.trim();
        if (!AppointmentId.isValidId(trimmedAppointmentId)) {
            throw new ParseException(AppointmentId.MESSAGE_CONSTRAINTS);
        }
        return new AppointmentId(trimmedAppointmentId);
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
     * Parses a {@code String gender} into an {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gender} is invalid.
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
     * Parses a {@code String gender} into an {@code Gender}.
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
     * Parses a {@code String specialisation} into a {@code Specialisation}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code spec} is invalid.
     */
    public static Specialisation parseSpecialisation(String spec) throws ParseException {
        requireNonNull(spec);
        String trimmedSpec = spec.trim();
        if (!Specialisation.isValidSpecialisation(trimmedSpec)) {
            throw new ParseException(Specialisation.MESSAGE_CONSTRAINTS);
        }
        return new Specialisation(trimmedSpec);
    }

    /**
     * Parses {@code Collection<String> specialisations} into a {@code Set<Specialisation>}.
     */
    public static Set<Specialisation> parseSpecialisations(Collection<String> specs) throws ParseException {
        requireNonNull(specs);
        final Set<Specialisation> specSet = new HashSet<>();
        for (String spec : specs) {
            specSet.add(parseSpecialisation(spec));
        }
        return specSet;
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
     * Parses a {@code String date} into a {@code AppointmentDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static AppointmentDate parseAppointmentDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!AppointmentDate.isValidAppointmentDate(trimmedDate)) {
            throw new ParseException(AppointmentDate.MESSAGE_CONSTRAINTS);
        }
        return new AppointmentDate(trimmedDate);
    }

    /**
     * Parses a {@code String time} into a {@code AppointmentTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code time} is invalid.
     */
    public static AppointmentTime parseAppointmentTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        if (!AppointmentTime.isValidAppointmentTime(trimmedTime)) {
            throw new ParseException(AppointmentTime.MESSAGE_CONSTRAINTS);
        }
        return new AppointmentTime(trimmedTime);
    }

    /**
     * Parses a {@code String status} into a {@code AppointmentStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static AppointmentStatus parseAppointmentStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim().toUpperCase();
        if (!AppointmentStatus.isValidAppointmentStatus(trimmedStatus)) {
            throw new ParseException(AppointmentStatus.MESSAGE_CONSTRAINTS);
        }
        return AppointmentStatus.valueOf(trimmedStatus);
    }
}
