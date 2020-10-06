package jimmy.mcgymmy.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import jimmy.mcgymmy.commons.core.index.Index;
import jimmy.mcgymmy.commons.util.StringUtil;
import jimmy.mcgymmy.logic.parser.exceptions.ParseException;
import jimmy.mcgymmy.model.food.Protein;
import jimmy.mcgymmy.model.food.Carbohydrate;
import jimmy.mcgymmy.model.food.Fat;
import jimmy.mcgymmy.model.tag.Tag;

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
        name = name.trim();
        //        String trimmedName = name.trim();
        //        if (!Name.isValidName(trimmedName)) {
        //            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        //        }
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
    public static jimmy.mcgymmy.model.food.Fat parseFat(String fat) throws ParseException {
        int fatValue = getNutrientValue(fat, jimmy.mcgymmy.model.food.Fat.MESSAGE_CONSTRAINTS);
        return new Fat(fatValue);
    }

    /**
     * Parses a {@code String carb} into a {@code Carbohydrate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Carbohydrate} is invalid.
     */
    public static jimmy.mcgymmy.model.food.Carbohydrate parseCarb(String carb) throws ParseException {
        int carbValue = getNutrientValue(carb, jimmy.mcgymmy.model.food.Carbohydrate.MESSAGE_CONSTRAINTS);
        return new Carbohydrate(carbValue);
    }


    /**
     * Parses a {@code String carb} into an {@code Carbohydrate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code carb} is invalid.
     */
    public static Carbohydrate parseCarbohydrate(String carb) throws ParseException {
        requireNonNull(carb);
        String trimmedCarb = carb.trim();
        if (!Carbohydrate.isValid(trimmedCarb)) {
            throw new ParseException(Carbohydrate.MESSAGE_CONSTRAINTS);
        }
        return new Carbohydrate(Integer.parseInt(trimmedCarb));
    }

    /**
     * Parses a {@code String email} into an {@code Fat}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Fat parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Fat.isValid(trimmedEmail)) {
            throw new ParseException(Fat.MESSAGE_CONSTRAINTS);
        }
        return new Fat(Integer.parseInt(trimmedEmail));
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
