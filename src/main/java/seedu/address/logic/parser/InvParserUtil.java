package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.storage.LocationStorage;


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
     * no
     *
     * @param name no
     * @return no
     */
    public static String parseName(String name) {
        requireNonNull(name);
        return name.trim();
    }

    /**
     * no
     *
     * @param quantity no
     * @return no
     */
    public static int parseQuantity(String quantity) {
        requireNonNull(quantity);
        return Integer.parseInt(quantity.trim());
    }

    /**
     * no
     *
     * @param description no
     * @return no
     */
    public static String parseDescription(String description) {
        requireNonNull(description);
        return description.trim();
    }

    /**
     * no
     * @param location no
     * @return no
     */
    public static int parseLocation(String location) {
        requireNonNull(location);
        String trimmedLocation = location.trim();
        HashMap<String, Integer> locations = LocationStorage.getLocations();
        Integer result = locations.get(trimmedLocation);
        if (result == null) {
            // it's a two way map
            LocationStorage.getLocationIds().put(locations.size() + 1, trimmedLocation);
            locations.put(trimmedLocation, locations.size() + 1);
            return locations.get(trimmedLocation);
        } else {
            return result;
        }
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<String>}.
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

