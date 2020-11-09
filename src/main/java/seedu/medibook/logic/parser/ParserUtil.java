package seedu.medibook.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.medibook.commons.core.index.Index;
import seedu.medibook.commons.util.StringUtil;
import seedu.medibook.logic.parser.exceptions.ParseException;
import seedu.medibook.model.commonfields.Date;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.doctor.Mcr;
import seedu.medibook.model.medicaldetail.Allergy;
import seedu.medibook.model.medicaldetail.Condition;
import seedu.medibook.model.medicaldetail.Tag;
import seedu.medibook.model.medicaldetail.Treatment;
import seedu.medibook.model.medicalnote.Content;
import seedu.medibook.model.patient.Address;
import seedu.medibook.model.patient.BloodType;
import seedu.medibook.model.patient.DateOfBirth;
import seedu.medibook.model.patient.Email;
import seedu.medibook.model.patient.Height;
import seedu.medibook.model.patient.Ic;
import seedu.medibook.model.patient.Phone;
import seedu.medibook.model.patient.Weight;

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
     * Parses a {@code String ic} into a {@code Ic}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ic} is invalid.
     */
    public static Ic parseIc(String ic) throws ParseException {
        requireNonNull(ic);
        String trimmedIc = ic.trim();
        String capitalisedTrimmedIc = trimmedIc.toUpperCase();
        if (!Ic.isValidIc(capitalisedTrimmedIc)) {
            throw new ParseException(Ic.MESSAGE_CONSTRAINTS);
        }
        return new Ic(capitalisedTrimmedIc);
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
     * Parses a {@code String dateOfBirth} into a {@code DateOfBirth}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateOfBirth} is invalid.
     */
    public static DateOfBirth parseDateOfBirth(String dateOfBirth) throws ParseException {
        requireNonNull(dateOfBirth);
        String trimmedDateOfBirth = dateOfBirth.trim();
        if (!DateOfBirth.isValidDateOfBirth(trimmedDateOfBirth)) {
            throw new ParseException(DateOfBirth.MESSAGE_CONSTRAINTS);
        }
        return new DateOfBirth(trimmedDateOfBirth);
    }

    /**
     * Parses a {@code String date} into a {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!Date.isValidDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }

        try {
            return new Date(trimmedDate, true);
        } catch (IllegalArgumentException e) {
            throw new ParseException(Date.MESSAGE_NON_FUTURE);
        }
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
     * Parses a {@code Optional<String> address} into an {@code Optional<Address>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Optional<Address> parseAddress(Optional<String> address) throws ParseException {
        if (address.isPresent()) {
            String trimmedAddress = address.get().trim();
            if (!Address.isValidAddress(trimmedAddress)) {
                throw new ParseException(Address.MESSAGE_CONSTRAINTS);
            }
            return Optional.of(new Address(trimmedAddress));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Parses a {@code Optional<String> email} into an {@code Optional<Email>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Optional<Email> parseEmail(Optional<String> email) throws ParseException {
        if (email.isPresent()) {
            String trimmedEmail = email.get().trim();
            if (!Email.isValidEmail(trimmedEmail)) {
                throw new ParseException(Email.MESSAGE_CONSTRAINTS);
            }
            return Optional.of(new Email(trimmedEmail));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Parses a {@code Optional<String> height} into a {@code Optional<Height>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code height} is invalid.
     */
    public static Optional<Height> parseHeight(Optional<String> height) throws ParseException {
        if (height.isPresent()) {
            String trimmedHeight = height.get().trim();
            if (!Height.isValidHeight(trimmedHeight)) {
                throw new ParseException(Height.MESSAGE_CONSTRAINTS);
            }
            return Optional.of(new Height(trimmedHeight));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Parses a {@code Optional<String> weight} into a {@code Optional<Weight>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code weight} is invalid.
     */
    public static Optional<Weight> parseWeight(Optional<String> weight) throws ParseException {
        if (weight.isPresent()) {
            String trimmedWeight = weight.get().trim();
            if (!Weight.isValidWeight(trimmedWeight)) {
                throw new ParseException(Weight.MESSAGE_CONSTRAINTS);
            }
            return Optional.of(new Weight(trimmedWeight));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Parses a {@code Optional<String> BloodType} into a {@code Optional<BloodType>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code BloodType} is invalid.
     */
    public static Optional<BloodType> parseBloodType(Optional<String> bloodType) throws ParseException {
        if (bloodType.isPresent()) {
            String trimmedBloodType = bloodType.get().trim();
            if (!BloodType.isValidBloodType(trimmedBloodType)) {
                throw new ParseException(BloodType.MESSAGE_CONSTRAINTS);
            }
            return Optional.of(new BloodType(trimmedBloodType));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Parses a {@code String content} into a {@code Content}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code content} is invalid.
     */
    public static Content parseContent(String content) throws ParseException {
        requireNonNull(content);
        String trimmedContent = content.trim();
        if (!Content.isValidContent(trimmedContent)) {
            throw new ParseException(Content.MESSAGE_CONSTRAINTS);
        }
        return new Content(trimmedContent);
    }

    /**
     * Parses a {@code String mcr} into a {@code Mcr}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code mcr} is invalid.
     */
    public static Mcr parseMcr(String mcr) throws ParseException {
        requireNonNull(mcr);
        String trimmedMcr = mcr.trim();
        String capitalisedTrimmedMcr = trimmedMcr.toUpperCase();
        if (!Mcr.isValidMcr(capitalisedTrimmedMcr)) {
            throw new ParseException(Mcr.MESSAGE_CONSTRAINTS);
        }
        return new Mcr(capitalisedTrimmedMcr);
    }

    /**
     * Parses a {@code String allergy} into a {@code Allergy}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code allergy} is invalid.
     */
    public static Allergy parseAllergy(String allergy) throws ParseException {
        String trimmedTagName = trimTagName(allergy);
        assert Tag.isValidTagName(trimmedTagName);
        return new Allergy(trimmedTagName);
    }

    /**
     * Parses a {@code String condition} into a {@code Condition}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code condition} is invalid.
     */
    public static Condition parseCondition(String condition) throws ParseException {
        String trimmedTagName = trimTagName(condition);
        assert Tag.isValidTagName(trimmedTagName);
        return new Condition(trimmedTagName);
    }

    /**
     * Parses a {@code String treatment} into a {@code Treatment}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code treatment} is invalid.
     */
    public static Treatment parseTreatment(String treatment) throws ParseException {
        String trimmedTagName = trimTagName(treatment);
        assert Tag.isValidTagName(trimmedTagName);
        return new Treatment(trimmedTagName);
    }

    /**
     * Trims a {@code String tag} into a valid tag name.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException
     */
    private static String trimTagName(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTagName = tag.trim();
        if (!Tag.isValidTagName(trimmedTagName)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return trimmedTagName;
    }

    /**
     * Parses {@code Collection<String> allergies} into a {@code Set<Allergy>}.
     */
    public static Set<Allergy> parseAllergies(Collection<String> allergies) throws ParseException {
        requireNonNull(allergies);
        final Set<Allergy> allergySet = new HashSet<>();
        for (String tagName : allergies) {
            allergySet.add(parseAllergy(tagName));
        }
        return allergySet;
    }

    /**
     * Parses {@code Collection<String> conditions} into a {@code Set<Condition>}.
     */
    public static Set<Condition> parseConditions(Collection<String> conditions) throws ParseException {
        requireNonNull(conditions);
        final Set<Condition> conditionSet = new HashSet<>();
        for (String tagName : conditions) {
            conditionSet.add(parseCondition(tagName));
        }
        return conditionSet;
    }

    /**
     * Parses {@code Collection<String> treatments} into a {@code Set<Treatment>}.
     */
    public static Set<Treatment> parseTreatments(Collection<String> treatments) throws ParseException {
        requireNonNull(treatments);
        final Set<Treatment> treatmentSet = new HashSet<>();
        for (String tagName : treatments) {
            treatmentSet.add(parseTreatment(tagName));
        }
        return treatmentSet;
    }
}
