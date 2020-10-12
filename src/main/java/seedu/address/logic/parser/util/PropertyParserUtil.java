package seedu.address.logic.parser.util;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.id.Id;
import seedu.address.model.price.Price;
import seedu.address.model.property.Address;
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
     * Parses a {@code String sellerId} into an {@code Id}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sellerId} is invalid.
     */
    public static Id parseSellerId(String sellerId) throws ParseException {
        requireNonNull(sellerId);
        String trimmedSellerId = sellerId.trim();
        if (!Id.isValidId(sellerId)) {
            throw new ParseException(Id.MESSAGE_CONSTRAINTS);
        }
        return new Id(trimmedSellerId);
    }

    /**
     * Parses a {@code String askingPrice} into an {@code Price}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code askingPrice} is invalid.
     */
    public static Price parseAskingPrice(String askingPrice) throws ParseException {
        requireNonNull(askingPrice);
        String trimmedAskingPrice = askingPrice.trim();
        try {
            double doublePrice = Double.parseDouble(trimmedAskingPrice);
            if (!Price.isValidPrice(doublePrice)) {
                throw new ParseException(Price.MESSAGE_CONSTRAINTS);
            }
            return new Price(doublePrice);
        } catch (NumberFormatException e) {
            throw new ParseException(Price.MESSAGE_CONSTRAINTS);
        }
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

}
