package seedu.jarvis.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.jarvis.commons.core.index.Index;
import seedu.jarvis.commons.util.StringUtil;
import seedu.jarvis.logic.parser.exceptions.ParseException;
import seedu.jarvis.model.flag.Flag;
import seedu.jarvis.model.login.Username;
import seedu.jarvis.model.student.Email;
import seedu.jarvis.model.student.Name;
import seedu.jarvis.model.student.Telegram;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "The provided index is not valid as it is out of the accepted "
            + "range. To obtain the valid index, use the view command and take note of the "
            + "leftmost identifier of each item in the list.";

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
     * Parses a {@code String[] nameComponents} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nameComponents} is invalid.
     */
    public static Name parseName(String[] nameComponents) throws ParseException {
        requireNonNull(nameComponents);
        StringBuilder studentNameBuilder = new StringBuilder();
        int lastNameComponentIndex = nameComponents.length;
        for (int i = 0; i < lastNameComponentIndex; i++) {
            studentNameBuilder.append(nameComponents[i]).append(" ");
        }
        String trimmedName = studentNameBuilder.toString().trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String telegram} into a {@code Telegram}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Telegram parseTelegram(String telegram) throws ParseException {
        requireNonNull(telegram);
        String trimmedPhone = telegram.trim();
        return new Telegram(trimmedPhone);
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
     * Parses a username into Username object.
     * @param username
     * @return A Username object
     * @throws ParseException
     */
    public static Username parseUsername(String username) throws ParseException {
        requireNonNull(username);
        String trimmedUsername = username.trim();
        if (!Username.isValidUsername(trimmedUsername)) {
            throw new ParseException(Username.MESSAGE_CONSTRAINTS);
        }
        return new Username(trimmedUsername);
    }

    /**
     * Parses a String score with integer values into a boolean.
     * @param score
     * @return A Boolean object
     * @throws ParseException
     */
    public static boolean parseScore(String score) throws ParseException {
        requireNonNull(score);
        String trimmedScore = score.trim();
        if (!trimmedScore.equals("1") && !trimmedScore.equals("0")) {
            throw new ParseException("Mastery check score can only be 0 or 1.");
        }

        return trimmedScore.equals("1"); // true if 1, false if 0
    }

    /**
     * Parses a string containing a flag such as "-s" and return the Flag object which matches it.
     * @param flag The String arguments to be parsed
     */
    public static Flag parseFlag(String flag) throws ParseException {
        requireNonNull(flag);
        String trimmedFlag = flag.trim();
        if (!Flag.isValidFlag(trimmedFlag)) {
            throw new ParseException(Flag.MESSAGE_CONSTRAINTS);
        }
        return new Flag(trimmedFlag);
    }
}
