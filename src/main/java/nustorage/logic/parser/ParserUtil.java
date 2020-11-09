package nustorage.logic.parser;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public static final String MESSAGE_MISSING_INDEX = "Index must be filled.";
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_AMOUNT = "Amount is not a decimal value.";
    public static final String MESSAGE_INVALID_DATETIME = "Date must be of the format yyyy-mm-dd HH:mm";
    public static final String MESSAGE_INVALID_QUANTITY = "Quantity must be an integer,"
            + " more than or equal to zero, and has an upper limit of 2,000,000,000";
    public static final String MESSAGE_INVALID_CHANGE_IN_QUANTITY = "Quantity must be an integer,"
            + " and has an upper limit of 2,000,000,000 and a lower limit of -2,000,000,000";
    public static final String MESSAGE_INVALID_ITEM_COST = "Item cost must be more than or equal to zero,"
            + " and has an upper limit of 2000,000,000"
            + "\nWill be rounded up to the nearest 2 decimal place.";
    public static final String MESSAGE_LONG_ITEM_COST = "Please round up your cost to the nearest 2 decimal place.";
    public static final String MESSAGE_INVALID_YES_NO = "Yes/No input must be one of the following: yes/y/no/n.";
    public static final String MESSAGE_INVALID_ITEM_NAME = "Item name cannot be empty";

    /**
     * Parses {@code a} into an double and returns it
     */
    public static double parseItemCost(String itemCost) throws ParseException {
        requireNonNull(itemCost);
        String trimmedAmount = itemCost.trim();
        try {
            double cost = Double.parseDouble(trimmedAmount);
            if (cost < 0 || cost > 2000000000) {
                throw new ParseException(MESSAGE_INVALID_ITEM_COST);
            }
            return round(cost);
        } catch (NumberFormatException ex) {
            throw new ParseException(MESSAGE_INVALID_ITEM_COST);
        }
    }

    /**
     * Rounds double value to 2 decimal places
     */
    public static double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Parses {@code itemDescription} into an String and returns it. Leading and trailing whitespaces will be
     * trimmed.
     */
    public static String parseItemDescription(String itemDescription) throws ParseException {
        String itemDes = itemDescription.trim();
        if (itemDes.isEmpty()) {
            throw new ParseException((MESSAGE_INVALID_ITEM_NAME));
        }
        return itemDes;
    }

    /**
     * Parses {@code quantity} into an int and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified quantity is invalid
     */
    public static int parseQuantity(String quantity) throws ParseException {
        String trimmedQuantity = quantity.trim();
        try {
            int itemQuantity = Integer.parseInt(trimmedQuantity);
            if (itemQuantity < 0 || itemQuantity > 2000000000) {
                throw new ParseException(MESSAGE_INVALID_QUANTITY);
            }
            return itemQuantity;
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_QUANTITY);
        }
    }

    /**
     * Parses {@code quantity} into an int and returns it. Leading and trailing whitespaces will be trimmed.
     * @param quantity the change in quantity to be parsed
     * @return the parsed change in quantity
     * @throws ParseException if the quantity is invalid
     */
    public static int parseChangeInQuantity(String quantity) throws ParseException {
        String trimmedQuantity = quantity.trim();
        try {
            int itemQuantity = Integer.parseInt(trimmedQuantity);
            return itemQuantity;
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_CHANGE_IN_QUANTITY);
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
        throw new ParseException(MESSAGE_INVALID_YES_NO);
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (trimmedIndex.isEmpty()) {
            throw new ParseException(MESSAGE_MISSING_INDEX);
        }
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }
}
