package nustorage.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import nustorage.commons.core.index.Index;
import nustorage.commons.util.StringUtil;
import nustorage.logic.parser.exceptions.ParseException;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_AMOUNT = "Amount is not a decimal value.";
    public static final String MESSAGE_INVALID_DATETIME = "Date must be of the format yyyy-mm-dd HH:mm";
    public static final String MESSAGE_INVALID_QUANITY = "Quantity is not a non-zero integer.";
    public static final String MESSAGE_INVALID_ITEM_COST = "Item cost must be a positive numerical value.";
    public static final String MESSAGE_INVALID_YESNO = "Yes/No input must be one of the following: yes/y/no/n.";

    /**
     * Parses {@code a} into an double and returns it
     */
    public static double parseItemCost(String itemCost) throws ParseException {
        requireNonNull(itemCost);
        String trimmedAmount = itemCost.trim();
        try {
            double cost = Double.parseDouble(trimmedAmount);
            if (cost <= 0) {
                throw new ParseException(MESSAGE_INVALID_ITEM_COST);
            }
            return cost;
        } catch (NumberFormatException ex) {
            throw new ParseException(MESSAGE_INVALID_ITEM_COST);
        }
    }

    /**
     * Parses {@code itemDescription} into an String and returns it. Leading and trailing whitespaces will be
     * trimmed.
     */
    public static String parseItemDescription(String itemDescription) {
        return itemDescription.trim();
    }

    /**
     * Parses {@code quantity} into an int and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified quantity is invalid
     */
    public static int parseQuantity(String quantity) throws ParseException {
        String trimmedQuantity = quantity.trim();
        try {
            return Integer.parseInt(trimmedQuantity);
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_QUANITY);
        }
    }

    /**
     * Parses {@code amount} into an {@code double} and returns it.
     * @throws ParseException if the specified amount is invalid (non-double value).
     */
    public static double parseAmount(String amount) throws ParseException {
        requireNonNull(amount);
        String trimmedAmount = amount.trim();
        try {
            return Double.parseDouble(trimmedAmount);
        } catch (NumberFormatException ex) {
            throw new ParseException(MESSAGE_INVALID_AMOUNT);
        }
    }

    /**
     * Parses {@code datetimeList} into an {@code LocalDateTime} and returns it.
     * @throws ParseException if the specified input is invalid (not correctly formatted).
     */
    public static LocalDateTime parseDatetime(String datetime) throws ParseException {
        requireNonNull(datetime);

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        String[] datetimeArray = datetime.split(" ");

        if (datetimeArray.length == 1) {

            try {
                date = LocalDate.parse(datetimeArray[0]);
            } catch (DateTimeParseException ex1) {
                try {
                    time = LocalTime.parse(datetimeArray[0]);
                } catch (DateTimeParseException ex2) {
                    throw new ParseException(MESSAGE_INVALID_DATETIME);
                }
            }

        } else if (datetimeArray.length > 1) {

            try {
                date = LocalDate.parse(datetimeArray[0]);
                time = LocalTime.parse(datetimeArray[1]);
            } catch (DateTimeParseException ex1) {
                try {
                    date = LocalDate.parse(datetimeArray[1]);
                    time = LocalTime.parse(datetimeArray[0]);
                } catch (DateTimeParseException ex2) {
                    throw new ParseException(MESSAGE_INVALID_DATETIME);
                }
            }
        }

        return LocalDateTime.of(date, time);
    }

    /**
     * Parses {@code yesNoString} into an {@code boolean} and returns it.
     * @throws ParseException if the specified index is invalid (does not correspond to a y/n input)
     */
    public static boolean parseYesNo(String yesNoString) throws ParseException {
        if (yesNoString.equals("yes") || yesNoString.equals("y")) {
            return true;
        }
        if (yesNoString.equals("no") || yesNoString.equals("n")) {
            return false;
        }
        throw new ParseException(MESSAGE_INVALID_YESNO);
    }

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
}
