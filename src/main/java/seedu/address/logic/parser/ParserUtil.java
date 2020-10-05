package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

<<<<<<< Updated upstream:src/main/java/seedu/address/logic/parser/ParserUtil.java
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
=======
import jimmy.mcgymmy.commons.core.index.Index;
import jimmy.mcgymmy.commons.util.StringUtil;
import jimmy.mcgymmy.logic.parser.exceptions.ParseException;
import jimmy.mcgymmy.model.food.Carbohydrate;
import jimmy.mcgymmy.model.food.Fat;
import jimmy.mcgymmy.model.food.Macronutrient;
import jimmy.mcgymmy.model.food.Protein;
import jimmy.mcgymmy.model.person.Address;
import jimmy.mcgymmy.model.person.Email;
import jimmy.mcgymmy.model.person.Name;
import jimmy.mcgymmy.model.person.Phone;
import jimmy.mcgymmy.model.tag.Tag;
>>>>>>> Stashed changes:src/main/java/jimmy/mcgymmy/logic/parser/ParserUtil.java

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
    public static String parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return name;
    }

    /**
     * Gets integer from string value.
     *
     * @param value String containing value of nutrient.
     * @param errorMessage String containing the error message.
     * @return Integer value of string.
     * @throws ParseException if the value is invalid.
     */
    private static int getNutrientValue(String value, String errorMessage) throws ParseException {
        requireNonNull(value);
        String trimmedValue = value.trim();
        //        if (!Protein.isValid(trimmedProtein)) {
        //            throw new ParseException(Protein.MESSAGE_CONSTRAINTS);
        //        }
        int nutrientValue;
        try {
            nutrientValue = Integer.parseInt(trimmedValue);
        } catch (NumberFormatException numberFormatException) {
            throw new ParseException(errorMessage);
        }
        return nutrientValue;
    }

    /**
     * Parses a {@code String protein} into a {@code Protein}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Protein} is invalid.
     */
    public static Protein parseProtein(String protein) throws ParseException {
        int proteinValue = getNutrientValue(protein, Protein.MESSAGE_CONSTRAINTS);
        return new Protein(proteinValue);
    }

    /**
     * Parses a {@code String fat} into a {@code Fat}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Fat} is invalid.
     */
    public static Fat parseFat(String fat) throws ParseException {
        int fatValue = getNutrientValue(fat, Fat.MESSAGE_CONSTRAINTS);
        return new Fat(fatValue);
    }

    /**
     * Parses a {@code String carb} into a {@code Carbohydrate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Carbohydrate} is invalid.
     */
    public static Carbohydrate parseCarb(String carb) throws ParseException {
        int carbValue = getNutrientValue(carb, Carbohydrate.MESSAGE_CONSTRAINTS);
        return new Carbohydrate(carbValue);
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
}
