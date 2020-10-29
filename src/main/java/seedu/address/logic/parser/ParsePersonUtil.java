package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.GitUserIndex;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.GitUserName;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.Phone;

/**
 * Contains utility methods used for parsing strings in NewTeammate Parser class.
 */
public class ParsePersonUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code gitUserName} into an {@code GitUserIndex} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static GitUserIndex parseGitUserIndex(String gitUserName) throws ParseException {
        requireNonNull(gitUserName);
        String trimmedGitUserName = gitUserName.trim();
        if (!GitUserName.isValidGitUserName(trimmedGitUserName)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return new GitUserIndex(trimmedGitUserName);
    }

    /**
     * Parses a {@code String personName} into a {@code PersonName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code personName} is invalid.
     */
    public static PersonName parsePersonName(String personName) throws ParseException {
        requireNonNull(personName);
        String trimmedPersonName = personName.trim();
        if (!PersonName.isValidPersonName(personName)) {
            throw new ParseException(PersonName.MESSAGE_CONSTRAINTS);
        }
        return new PersonName(trimmedPersonName);
    }

    /**
     * Parses a {@code String gitUserName} into a {@code GitUserName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code personName} is invalid.
     */
    public static GitUserName parseGitUserName(String gitUserName) throws ParseException {
        requireNonNull(gitUserName);
        String trimmedGitUserName = gitUserName.trim();
        if (!GitUserName.isValidGitUserName(gitUserName)) {
            throw new ParseException(GitUserName.MESSAGE_CONSTRAINTS);
        }
        return new GitUserName(trimmedGitUserName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedphone = phone.trim();
        if (!Phone.isValidPhone(phone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedphone);
    }

    /**
     * Parses a {@code String email} into a {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(email)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String address} into a {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedaddress = address.trim();
        if (!Address.isValidAddress(address)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedaddress);
    }
}
