package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.SalesUpdateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Amount;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MINIMUM_AMOUNT_AS_STRING = "0";
    public static final String MAXIMUM_AMOUNT_AS_STRING = "999";
    public static final String NEGATIVE_SIGN = "-";
    public static final String MESSAGE_INVALID_AMOUNT = "Amount has to be a non-negative integer.\n"
            + "Please note that record to the nearest KG/L is sufficient for inventory keeping.";
    public static final int MAXIMUM_AMOUNT = 999;
    public static final int MINIMUM_AMOUNT = 0;
    public static final String MESSAGE_EXCEED_MAXIMUM_AMOUNT = "The amount entered is greater than "
            + "999 KG / L, which is the maximum capacity for any kind of ingredient for one stall.\n"
            + "Please double check the current amount and enter the actual current amount !";
    public static final String MESSAGE_EXCEED_MAXIMUM_LENGTH = "The amount entered has at least four "
            + "digits(inclusive of Decimal Point), "
            + "which is either more than the maximum capacity of storage\n"
            + " (999 KG / L) for any one ingredient or contains decimal part."
            + "\nPlease record the amount to nearest KG/L and remove any excess leading zeros !\n"
            + "Please note that only amounts with less than four digits will be accepted.\n"
            + "For example : 0000000000000000009 is not acceptable, but 009 is.";
    public static final String MESSAGE_INVALID_INGREDIENT_NAME = "The ingredient is not found, it "
            + "has to be chosen from : " + Arrays.toString(IngredientName.INGREDIENTS)
            + "\nPlease note that ingredient names are CASE-SENSITIVE to ensure consistency.";
    public static final String MESSAGE_INVALID_NUMBER_SOLD = "The entered number of drinks sold should be a "
            + "non-negative integer.";

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
     * Parses a string representing amount into an amount.
     */
    public static Amount parseAmount(String amount) throws ParseException {
        requireNonNull(amount);
        String trimmedAmount = amount.trim();
        if (trimmedAmount.contains(NEGATIVE_SIGN)) {
            throw new ParseException(MESSAGE_INVALID_AMOUNT);
        }
        if (trimmedAmount.length() > MAXIMUM_AMOUNT_AS_STRING.length()) {
            throw new ParseException(MESSAGE_EXCEED_MAXIMUM_LENGTH);
        }
        if (!StringUtil.isUnsignedInteger(trimmedAmount)) {
            throw new ParseException(MESSAGE_INVALID_AMOUNT);
        }
        if (Integer.parseInt(trimmedAmount) > MAXIMUM_AMOUNT) {
            throw new ParseException(MESSAGE_EXCEED_MAXIMUM_AMOUNT);
        }
        if (Integer.parseInt(trimmedAmount) == MINIMUM_AMOUNT) {
            return new Amount(MINIMUM_AMOUNT_AS_STRING);
        }
        return new Amount(trimmedAmount);
    }
    /**
     * Parses a string representing ingredient into an ingredient name.
     */
    public static IngredientName parseIngredientName(String ingredient) throws ParseException {
        requireNonNull(ingredient);
        String trimmedIngredient = ingredient.trim();
        if (!IngredientName.isValidIngredientName(trimmedIngredient)) {
            throw new ParseException(MESSAGE_INVALID_INGREDIENT_NAME);
        }
        return new IngredientName(trimmedIngredient);
    }

    /**
     * Parses a string representing ingredient into an ingredient.
     */
    public static Ingredient parseIngredient(String ingredient) throws ParseException {
        requireNonNull(ingredient);
        String trimmedIngredient = ingredient.trim();
        if (!IngredientName.isValidIngredientName(trimmedIngredient)) {
            throw new ParseException(MESSAGE_INVALID_INGREDIENT_NAME);
        }
        return new Ingredient(new IngredientName(trimmedIngredient));
    }

    /**
     * Parses a {@code String numberSold} into an {@code Integer}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code numberSold} is invalid.
     */
    public static Integer parseNumberSold(String numberSold) throws ParseException {
        requireNonNull(numberSold);
        String trimmedNumberSold = numberSold.trim();
        if (StringUtil.isIntegerOverflow(trimmedNumberSold)) {
            throw new ParseException(SalesUpdateCommand.MESSAGE_MAX_NUM_ALLOWED_EXCEEDED);
        }

        if (!StringUtil.isUnsignedInteger(trimmedNumberSold)) {
            throw new ParseException(MESSAGE_INVALID_NUMBER_SOLD);
        }
        return Integer.parseInt(trimmedNumberSold);
    }
}
