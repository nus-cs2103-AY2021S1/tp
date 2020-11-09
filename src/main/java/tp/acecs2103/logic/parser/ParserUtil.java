package tp.acecs2103.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import tp.acecs2103.commons.util.StringUtil;
import tp.acecs2103.logic.parser.exceptions.ParseException;
import tp.acecs2103.model.task.TaskCategory;
import tp.acecs2103.model.task.WeekNumber;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_WEEKNUMBER = "Invalid week number. Week number cannot be empty, "
            + "and can only take integer value in range [1,13].\n"
            + "Please try again!";

    public static final String MESSAGE_INVALID_DEADLINE = "Invalid deadline format. A deadline should be in "
            + "the form of YYYY-MM-DD.\nNote boundary for year, month and days.\n"
            + "E.g. 2020-15-0 is an invalid deadline. \nPlease try again!";

    public static final String MESSAGE_INVALID_CATEGORY = "Invalid category format. "
            + "There are only 4 categories for CS2103/T tasks: \n Ip / Tp / Topic / Admin\n"
            + "Note the capitalisation. \nPlease try again!";

    public static final String MESSAGE_INVALID_TYPE = "Invalid type. "
            + "\nPlease try again!";

    public static final String MESSAGE_MISSING_WEEKNUMBER = "Week number is missing in your command. "
            + "\nPlease try again!";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static String parseIndex(String oneBasedIndex) throws ParseException {
        requireNonNull(oneBasedIndex);
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_WEEKNUMBER);
        }
        return trimmedIndex;
    }

    /**
     * Parses a week number.
     * @param weekNumber
     * @throws ParseException
     */
    public static int parseWeekNumber(String weekNumber) throws ParseException {
        requireNonNull(weekNumber);
        String trimmedWN = weekNumber.trim();
        if (trimmedWN.equals("")) {
            throw new ParseException(MESSAGE_MISSING_WEEKNUMBER);
        }
        try {
            Integer.parseInt(trimmedWN);
        } catch (Exception e) {
            throw new ParseException(MESSAGE_INVALID_WEEKNUMBER);
        }

        if (!WeekNumber.isValidWeekNumber(trimmedWN)) {
            throw new ParseException(MESSAGE_INVALID_WEEKNUMBER);
        }
        return Integer.parseInt(trimmedWN);

    }

    /**
     * Parses a description.
     * @param description
     * @throws ParseException
     */
    public static String parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDes = description.trim();
        return trimmedDes;
    }

    /**
     * Parses official deadline.
     * @param officialDeadline
     * @throws ParseException
     */
    public static LocalDate parseOfficialDeadline(String officialDeadline) throws ParseException {
        requireNonNull(officialDeadline);
        String trimmedDeadline = officialDeadline.trim();
        LocalDate result;
        try {
            result = LocalDate.parse(trimmedDeadline);
        } catch (Exception e) {
            throw new ParseException(MESSAGE_INVALID_DEADLINE);
        }
        return result;
    }

    /**
     * Parses a customized deadline.
     * @param customizedDeadline
     * @throws ParseException
     */
    public static LocalDate parseCustomizedDeadline(String customizedDeadline) throws ParseException {
        requireNonNull(customizedDeadline);
        String trimmedDeadline = customizedDeadline.trim();
        LocalDate result;
        try {
            result = LocalDate.parse(trimmedDeadline);
        } catch (Exception e) {
            throw new ParseException(MESSAGE_INVALID_DEADLINE);
        }
        return result;
    }

    /**
     * Parses remark.
     * @param remark
     * @throws ParseException
     */
    public static String parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedDes = remark.trim();
        return trimmedDes;
    }

    /**
     * Parses a type.
     * @param type
     * @throws ParseException
     */
    public static String parseType(String type) throws ParseException {
        requireNonNull(type);
        String trimmedType = type.trim();
        if (trimmedType.isEmpty()) {
            throw new ParseException(MESSAGE_INVALID_TYPE);
        }
        return trimmedType;
    }

    /**
     * Parses a category.
     */
    public static TaskCategory parseCategory(String category) throws ParseException {
        switch (category) {
        case "Admin":
            return TaskCategory.ADMIN;
        case "Topic":
            return TaskCategory.TOPIC;
        case "Ip":
            return TaskCategory.IP;
        case "Tp":
            return TaskCategory.TP;
        default:
            throw new ParseException(MESSAGE_INVALID_CATEGORY);
        }
    }
}
