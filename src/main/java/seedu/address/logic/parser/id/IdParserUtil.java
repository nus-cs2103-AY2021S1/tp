package seedu.address.logic.parser.id;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.id.SellerId;

public class IdParserUtil {

    /**
     * Parses a {@code String propertyId} into an {@code PropertyId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code propertyId} is invalid.
     */
    public static PropertyId parsePropertyId(String propertyId) throws ParseException {
        requireNonNull(propertyId);
        String trimmedPropertyId = propertyId.trim();
        if (!PropertyId.isValidId(propertyId)) {
            throw new ParseException(PropertyId.MESSAGE_CONSTRAINTS);
        }
        return new PropertyId(trimmedPropertyId);
    }

    /**
     * Parses a {@code String sellerId} into an {@code sellerId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sellerId} is invalid.
     */
    public static SellerId parseSellerId(String sellerId) throws ParseException {
        requireNonNull(sellerId);
        String trimmedSellerId = sellerId.trim();
        if (!SellerId.isValidId(sellerId)) {
            throw new ParseException(SellerId.MESSAGE_CONSTRAINTS);
        }
        return new SellerId(trimmedSellerId);
    }

    /**
     * Parses a {@code String bidderId} into an {@code BidderId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code bidderId} is invalid.
     */
    public static BidderId parseBidderId(String bidderId) throws ParseException {
        requireNonNull(bidderId);
        String trimmedBidderId = bidderId.trim();
        if (!BidderId.isValidId(bidderId)) {
            throw new ParseException(BidderId.MESSAGE_CONSTRAINTS);
        }
        return new BidderId(trimmedBidderId);
    }

    /**
     * Parses a {@code String bidderId} into an {@code List<BidderId>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code bidderId} is invalid.
     */
    public static BidderId parseBidderIdList(String bidderId) throws ParseException {
        requireNonNull(bidderId);
        String trimmedBidderId = bidderId.trim();
        if (!BidderId.isValidId(bidderId)) {
            throw new ParseException(BidderId.MESSAGE_CONSTRAINTS);
        }
        return new BidderId(trimmedBidderId);
    }
}
