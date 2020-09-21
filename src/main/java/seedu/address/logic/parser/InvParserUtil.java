package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Quantity;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class InvParserUtil {

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
     * Parses a {@code String name} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static String parseName(String name) {
        requireNonNull(name);
        return name.trim();
    }

    /**
     * Parses a {@code String quantity} into a {@code Quantity}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code quantity} is invalid.
     */
    public static Quantity parseQuantity(String quantity) throws ParseException {
        requireNonNull(quantity);
        String trimmedQuantity = quantity.trim();
        if (!Quantity.isValidQuantity(trimmedQuantity)) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        }
        return new Quantity(trimmedQuantity);
    }

    /**
     * Parses a {@code String description} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static String parseDescription(String description) {
        requireNonNull(description);
        return description.trim();
    }

    /**
     * Parses a {@code String location} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static int parseLocation(String location) {
        requireNonNull(location);
        String trimmedLocation = location.trim();
        // TODO
        return 1;
    }

    /**
     * Parses {@code Collection<String> locations} into a {@code Set<String>}.
     */
    public static Set<Integer> parseLocations(Collection<String> locations) {
        requireNonNull(locations);
        final Set<Integer> locationSet = new HashSet<>();
        for (String locationName : locations) {
            locationSet.add(parseLocation(locationName));
        }
        return locationSet;
    }
}

