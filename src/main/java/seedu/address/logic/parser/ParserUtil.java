package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.allergy.Allergy;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Appointment;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.MedicalRecord;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Phone;

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
     * Parses a {@code String nric} into a {@code Nric}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nric} is invalid.
     */
    public static Nric parseNric(String nric) throws ParseException {
        requireNonNull(nric);
        String trimmedNric = nric.trim();
        if (!Nric.isValidNric(trimmedNric)) {
            throw new ParseException(Nric.MESSAGE_CONSTRAINTS);
        }
        return new Nric(trimmedNric);
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
     * Parses a {@code String allergy} into a {@code Allergy}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code allergy} is invalid.
     */
    public static Allergy parseAllergy(String allergy) throws ParseException {
        requireNonNull(allergy);
        String trimmedAllergy = allergy.trim();
        if (!Allergy.isValidAllergyName(trimmedAllergy)) {
            throw new ParseException(Allergy.MESSAGE_CONSTRAINTS);
        }
        return new Allergy(trimmedAllergy);
    }

    /**
     * Parses a {@code String appointment} into a {@code Appointment}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code appointment} is invalid.
     */
    public static Appointment parseAppointment(String appointment, boolean allowPast) throws ParseException {
        requireNonNull(appointment);
        String trimmedAppointment = appointment.trim();
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(trimmedAppointment, PREFIX_DESCRIPTION);
        String timing = argMultimap.getPreamble().trim();
        String description;
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
        } else {
            description = "";
        }

        if (!Appointment.isValidDateTime(timing)) {
            throw new ParseException(Appointment.MESSAGE_CONSTRAINTS);
        }

        LocalDateTime localDateTime =
                LocalDateTime.parse(timing, DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"));
        if (!allowPast && Appointment.isPassed(localDateTime)) {
            throw new ParseException(Appointment.TIME_RANGE_CONSTRAINTS);
        }

        if (!Appointment.isValidDescription(description)) {
            throw new ParseException(Appointment.MESSAGE_CONSTRAINTS_INVALID_DESCRIPTION);
        }

        return new Appointment().setTime(timing).setDescription(description);
    }

    /**
     * Parses {@code Collection<String> allergies} into a {@code Set<Allergy>}.
     */
    public static Set<Allergy> parseAllergies(Collection<String> allergies) throws ParseException {
        requireNonNull(allergies);
        final Set<Allergy> allergySet = new HashSet<>();
        for (String allergyName : allergies) {
            allergySet.add(parseAllergy(allergyName));
        }
        return allergySet;
    }

    /**
     * Parses {@code Collection<String> appointments} into a {@code Set<Appointment>}.
     */
    public static Set<Appointment> parseAppointments(Collection<String> appointments) throws ParseException {
        requireNonNull(appointments);
        final Set<Appointment> appointmentSet = new HashSet<>();
        for (String appointmentTime : appointments) {
            appointmentSet.add(parseAppointment(appointmentTime, false));
        }
        return appointmentSet;
    }

    /**
     * Parses a {@code String url} into an {@code MedicalRecord}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code url} is invalid.
     */
    public static MedicalRecord parseMedicalRecord(String url) throws ParseException {
        requireNonNull(url);
        String trimmedUrl = url.trim();
        if (!MedicalRecord.isValidUrl(trimmedUrl)) {
            throw new ParseException(MedicalRecord.MESSAGE_CONSTRAINTS);
        }
        return new MedicalRecord(trimmedUrl);
    }
}
