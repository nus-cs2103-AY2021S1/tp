package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.information.Address;
import seedu.address.model.information.BlacklistStatus;
import seedu.address.model.information.CompanyName;
import seedu.address.model.information.Date;
import seedu.address.model.information.Email;
import seedu.address.model.information.Experience;
import seedu.address.model.information.Name;
import seedu.address.model.information.Phone;
import seedu.address.model.information.Priority;
import seedu.address.model.information.Salary;
import seedu.address.model.information.UrlLink;
import seedu.address.model.information.Vacancy;
import seedu.address.model.tag.Tag;

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
     * Parses a {@code String companyName} into a {@code CompanyName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code company name} is invalid.
     */
    public static CompanyName parseCompanyName(String companyName) throws ParseException {
        requireNonNull(companyName);
        String trimmedName = companyName.trim();
        if (!CompanyName.isValidCompanyName(trimmedName)) {
            throw new ParseException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        return new CompanyName(trimmedName);
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
     * Parses a {@code String experience} into a {@code Experience}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code experience} is invalid.
     */
    public static Experience parseExperience(String experience) throws ParseException {
        requireNonNull(experience);
        String trimmedExperience = experience.trim();
        if (!Experience.isValidExperience(trimmedExperience)) {
            throw new ParseException(Experience.MESSAGE_CONSTRAINTS);
        }
        return new Experience(trimmedExperience);
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
        return new Date(trimmedDate);
    }
    /**
     * Parses a {@code String isBlacklisted} into a {@code BlacklistStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code isBlacklisted} is invalid.
     */

    public static BlacklistStatus parseBlacklistStatus(String isBlacklisted) throws ParseException {
        requireNonNull(isBlacklisted);
        String trimmedIsBlacklisted = isBlacklisted.trim();
        if (!BlacklistStatus.isValidBlacklistStatus(isBlacklisted)) {
            throw new ParseException(BlacklistStatus.MESSAGE_CONSTRAINTS);
        }
        return new BlacklistStatus(trimmedIsBlacklisted);
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
     * Parses a {@code String link} into a {@code UrlLink}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code link} is invalid.
     */
    public static UrlLink parseUrlLink(String link) throws ParseException {
        requireNonNull(link);
        String trimmedLink = link.trim();
        if (!UrlLink.isValidLink(trimmedLink)) {
            throw new ParseException(UrlLink.MESSAGE_CONSTRAINTS);
        }
        return new UrlLink(trimmedLink);
    }

    /**
     * Parses a {@code String salary} into a {@code Salary}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code salary} is invalid.
     */
    public static Salary parseSalary(String salary) throws ParseException {
        requireNonNull(salary);
        String trimmedSalary = salary.trim();
        if (!Salary.isValidSalary(trimmedSalary)) {
            throw new ParseException(Salary.MESSAGE_CONSTRAINTS);
        }
        return new Salary(trimmedSalary);
    }

    /**
     * Parses a {@code String vacancy} into a {@code Vacancy}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code vacancy} is invalid.
     */
    public static Vacancy parseVacancy(String vacancy) throws ParseException {
        requireNonNull(vacancy);
        String trimmedVacancy = vacancy.trim();
        if (!Vacancy.isValidVacancy(trimmedVacancy)) {
            throw new ParseException(Vacancy.MESSAGE_CONSTRAINTS);
        }
        return new Vacancy(trimmedVacancy);
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
     * Parses a {@code String priority} into a {@code Priority}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code priority} is invalid.
     */
    public static Priority parsePriorityString(String priority) throws ParseException {
        requireNonNull(priority);
        String trimmedPriority = priority.trim();
        if (!Priority.isValidPriority(trimmedPriority)) {
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS);
        }
        return new Priority(trimmedPriority);
    }

    /**
     * Parses {@code List<String> priorities} into a {@code Priority}.
     */
    public static Priority parsePriority(List<String> priorities) throws ParseException {
        requireNonNull(priorities);
        // if no priority given, assign moderate
        String priorityString = priorities.isEmpty() ? "moderate" : priorities.get(priorities.size() - 1);
        return parsePriorityString(priorityString);
    }

    /**
     * Parses a {@code String order} into a boolean option.
     * True is returned when order is ascending.
     * False is returned when order is descending or null
     *
     * @throws ParseException if the given {@code order} is invalid.
     */
    public static Boolean parseOrder(String order) throws ParseException {
        if (order == null || order.trim().equalsIgnoreCase("desc")) {
            return false;
        } else if (order.trim().equalsIgnoreCase("asc")) {
            return true;
        } else {
            throw new ParseException("Order can only be 'asc' or 'desc'");
        }
    }
}
