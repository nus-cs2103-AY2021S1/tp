package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.School;
import seedu.address.model.student.Year;
import seedu.address.model.student.admin.AdditionalDetail;
import seedu.address.model.student.admin.ClassTime;
import seedu.address.model.student.admin.ClassVenue;
import seedu.address.model.student.admin.Fee;
import seedu.address.model.student.admin.PaymentDate;


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
     * Parses a {@code String school} into a {@code School}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static School parseSchool(String school) throws ParseException {
        requireNonNull(school);
        String trimmedSchool = school.trim();
        if (!School.isValidSchool(trimmedSchool)) {
            throw new ParseException(School.MESSAGE_CONSTRAINTS);
        }
        return new School(school);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
=======
     * Parses a {@code String year} into a {@code Year}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code year} is invalid.
     */
    public static Year parseYear(String year) throws ParseException {
        requireNonNull(year);
        String trimmedYear = year.trim();
        if (!Year.isValidYear(trimmedYear)) {
            throw new ParseException(Year.MESSAGE_CONSTRAINTS);
        }
        return new Year(year);
    }

    /**
     * Parses a {@code String venue} into a {@code ClassVenue}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code venue} is invalid.
     */
    public static ClassVenue parseVenue(String venue) throws ParseException {
        requireNonNull(venue);
        String trimmedVenue = venue.trim();
        if (!ClassVenue.isValidClassVenue(trimmedVenue)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new ClassVenue(trimmedVenue);
    }

    /**
     * Parses a {@code String time} into a {@code ClassTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code time} is invalid.
     */
    public static ClassTime parseTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        if (!ClassTime.isValidClassTime(trimmedTime)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new ClassTime(trimmedTime);
    }

    /**
     * Parses a {@code String fee} into a {@code Fee}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code fee} is invalid.
     */
    public static Fee parseFee(String fee) throws ParseException {
        requireNonNull(fee);
        String trimmedFee = fee.trim();
        if (!Fee.isValidFee(trimmedFee)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Fee(trimmedFee);
    }

    /**
     * Parses a {@code String paymentDate} into a {@code PaymentDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code paymentDate} is invalid.
     */
    public static PaymentDate parsePaymentDate(String paymentDate) throws ParseException {
        requireNonNull(paymentDate);
        String trimmedPaymentDate = paymentDate.trim();
        if (!PaymentDate.isValidDate(trimmedPaymentDate)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new PaymentDate(trimmedPaymentDate);
    }

    /**
     * Parses a {@code String detail} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code detail} is invalid.
     */
    public static AdditionalDetail parseAdditionalDetail(String detail) throws ParseException {
        requireNonNull(detail);
        String trimmedDetail = detail.trim();
        if (!AdditionalDetail.isValidAdditionalDetail(trimmedDetail)) {
            throw new ParseException(AdditionalDetail.MESSAGE_CONSTRAINTS);
        }
        return new AdditionalDetail(trimmedDetail);
    }

    /**
     * Parses {@code Collection<String> additionalDetails} into a {@code Set<Tag>}.
     */
    public static Set<AdditionalDetail> parseAdditionalDetails(Collection<String> additionalDetails)
            throws ParseException {
        requireNonNull(additionalDetails);
        final Set<AdditionalDetail> detailSet = new HashSet<>();
        for (String detail : additionalDetails) {
            detailSet.add(parseAdditionalDetail(detail));
        }
        return detailSet;
    }
}
