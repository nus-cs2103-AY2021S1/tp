package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Quantity;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ItemParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String DEFAULT_QUANTITY = "0";
    public static final String DEFAULT_DESCRIPTION = "None";
    public static final Quantity DEFAULT_QUANTITY_TYPED = new Quantity(DEFAULT_QUANTITY);
    public static final String REGEX_ENTRIES = " , |, |,";
    public static final Index DEFAULT_INDEX = Index.fromOneBased(1);

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
     * Parses a {@code String number} into a {@code int}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static int parseInt(String number) {
        requireNonNull(number);
        return Integer.parseInt(number.trim());
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
     * Parses {@code Collection<String> locations} into a {@code Set<String>}
     * Defensively flatmap parses Strings in location field in case
     * -l is used multiple times
     * @param locations String location(s)
     * @return Set of tags used
     */
    public static Set<String> parseLocations(Collection<String> locations) {
        requireNonNull(locations);
        return locations.stream()
                .flatMap(x -> Arrays.stream(x.split(REGEX_ENTRIES)))
                .collect(Collectors.toSet());
    }

    /**
     * Parses {@code Collection<String> tag} into a {@code Set<Tag>}
     * Defensively flatmap parses Strings in location field in case
     * -t is used multiple times
     * @param tags String tags
     * @return Set of tags used
     */
    public static Set<Tag> parseTags(Collection<String> tags) {
        requireNonNull(tags);
        return tags.stream()
                .flatMap(x -> Arrays.stream(x.split(REGEX_ENTRIES)))
                .map(String::strip)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}

