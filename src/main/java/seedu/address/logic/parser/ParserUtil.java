package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.clientsource.ClientSource;
import seedu.address.model.note.Note;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Priority;
import seedu.address.model.policy.PolicyDescription;
import seedu.address.model.policy.PolicyName;

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
        if (phone == null) {
            return null;
        }
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
        if (address == null) {
            return null;
        }
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
        if (email == null) {
            return null;
        }
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String clientsource} into a {@code ClientSource}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code clientsource} is invalid.
     */
    public static ClientSource parseClientSource(String clientSource) throws ParseException {
        requireNonNull(clientSource);
        String trimmedClientSource = clientSource.trim();
        if (!ClientSource.isValidClientSourceName(trimmedClientSource)) {
            throw new ParseException(ClientSource.MESSAGE_CONSTRAINTS);
        }
        return new ClientSource(trimmedClientSource);
    }

    /**
     * Parses {@code Collection<String> clientSources} into a {@code Set<ClientSource>}.
     */
    public static Set<ClientSource> parseClientSources(Collection<String> clientSources) throws ParseException {
        requireNonNull(clientSources);
        final Set<ClientSource> clientSourceSet = new HashSet<>();
        for (String clientSourceName : clientSources) {
            clientSourceSet.add(parseClientSource(clientSourceName));
        }
        return clientSourceSet;
    }

    /**
     * Parses a {@code String note} into a {@code Note}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code note} is invalid.
     */
    public static Note parseNote(String note) throws ParseException {
        if (note == null) {
            return null;
        }
        String trimmedNote = note.trim();
        if (!Note.isValidNoteName(trimmedNote)) {
            throw new ParseException(Note.MESSAGE_CONSTRAINTS);
        }
        return new Note(trimmedNote);
    }

    /**
     * Parses a {@code String priority} into a {@code Priority}.
     * Leading and trailing white spaces will be trimmed.
     *
     * @throws ParseException if the given {@code priority} is invalid.
     */
    public static Priority parsePriority(String priority) throws ParseException {
        if (priority == null) {
            return new Priority(null);
        }
        String trimmedPriority = priority.trim();
        if (!Priority.isValidPriority(trimmedPriority)) {
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS);
        }
        return new Priority(trimmedPriority);
    }

    /**
     * Parses a {@code String name} into a {@code PolicyName}.
     * Leading and trailing white spaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static PolicyName parsePolicyName(String name) throws ParseException {
        if (name == null) {
            return null;
        }
        String trimmedName = name.trim();
        if (!PolicyName.isValidPolicyName(trimmedName)) {
            throw new ParseException(PolicyName.MESSAGE_CONSTRAINTS);
        }
        return new PolicyName(trimmedName);
    }

    /**
     * Parses a {@code String description} into a {@code PolicyDescription}.
     * Leading and trailing white spaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static PolicyDescription parsePolicyDescription(String description) throws ParseException {
        if (description == null) {
            return null;
        }
        String trimmedDescription = description.trim();
        if (!PolicyDescription.isValidPolicyDescription(trimmedDescription)) {
            throw new ParseException(PolicyDescription.MESSAGE_CONSTRAINTS);
        }
        return new PolicyDescription(trimmedDescription);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
