package seedu.address.logic.parser.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.property.FindPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.price.PriceFilter;
import seedu.address.model.property.Address;
import seedu.address.model.property.IsClosedDeal;
import seedu.address.model.property.IsRental;
import seedu.address.model.property.PropertyName;
import seedu.address.model.property.PropertyType;

/**
 * Contains utility methods used for parsing strings in the various *PropertyCommandParser classes.
 */
public class PropertyParserUtil {

    /**
     * Parses a {@code String name} into a {@code PropertyName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static PropertyName parsePropertyName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!PropertyName.isValidPropertyName(trimmedName)) {
            throw new ParseException(PropertyName.MESSAGE_CONSTRAINTS);
        }
        return new PropertyName(trimmedName);
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
     * Parses a {@code String type} into a {@code PropertyType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code type} is invalid.
     */
    public static PropertyType parsePropertyType(String type) throws ParseException {
        requireNonNull(type);
        String trimmedType = type.trim();
        if (!PropertyType.isValidPropertyType(trimmedType)) {
            throw new ParseException(PropertyType.MESSAGE_CONSTRAINTS);
        }
        return new PropertyType(trimmedType);
    }

    /**
     * Parses a {@code String isRental} into a IsRental.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code isRental} is invalid.
     */
    public static IsRental parseIsRental(String isRental) throws ParseException {
        requireNonNull(isRental);
        String trimmedIsRental = isRental.trim();
        if (!IsRental.isValidIsRental(trimmedIsRental)) {
            throw new ParseException(IsRental.MESSAGE_CONSTRAINTS);
        }
        return new IsRental(trimmedIsRental);
    }

    /**
     * Parses the String arguments into a List of String.
     *
     * @param args The arguments.
     * @return The List of String separated by words.
     * @throws ParseException If arguments is empty.
     */
    public static List<String> parseKeywords(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPropertyCommand.MESSAGE_USAGE));
        }
        String[] keywords = trimmedArgs.split("\\s+");
        return Arrays.asList(keywords);
    }

    /**
     * Parses the String priceFilter into a {@code PriceFilter} object.
     *
     * @param priceFilter The String priceFilter.
     * @return A {@code PriceFilter} object.
     * @throws ParseException If the priceFilter is in an invalid format.
     */
    public static PriceFilter parsePriceFilter(String priceFilter) throws ParseException {
        String trimmed = priceFilter.trim();
        requireNonNull(priceFilter);
        if (!PriceFilter.isValidPriceFilter(trimmed)) {
            throw new ParseException(PriceFilter.MESSAGE_CONSTRAINTS);
        }
        try {
            return new PriceFilter(trimmed);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }
    }

    /**
     * Parses the String isClosedDeal into a {@code IsClosedDeal} object.
     *
     * @param isClosedDeal The String isClosedDeal.
     * @return The {@code IsClosedDeal} object.
     * @throws ParseException If the isClosedDeal is in an invalid format.
     */
    public static IsClosedDeal parseIsClosedDeal(String isClosedDeal) throws ParseException {
        requireNonNull(isClosedDeal);
        String trimmed = isClosedDeal.trim();
        if (!IsClosedDeal.isValidIsClosedDeal(isClosedDeal)) {
            throw new ParseException(IsClosedDeal.MESSAGE_CONSTRAINTS);
        }
        return new IsClosedDeal(trimmed);
    }

}
