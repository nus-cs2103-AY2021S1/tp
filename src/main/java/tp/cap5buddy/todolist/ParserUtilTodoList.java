package tp.cap5buddy.todolist;

import tp.cap5buddy.logic.parser.exception.ParseException;
import tp.cap5buddy.util.StringUtil;

/** Temporary class for ParserUtil for TodoList since the current
 *  version does not have a general ParserUtil class.
 **/
public class ParserUtilTodoList {
    /**
     * Parses index into an integer.
     *
     * @param input user input.
     * @return a zero based index as integer.
     * @throws ParseException if index is invalid.
     */
    public static int parseIndex(String input) throws ParseException {
        if (!StringUtil.isNonZeroUnsignedInteger(input)) {
            throw new ParseException("Index is invalid");
        }
        int index = Integer.parseInt(input);
        return index - 1;
    }

    /**
     * Parses description into a Description object.
     *
     * @param input user input.
     * @return a Description object.
     * @throws ParseException if description is invalid.
     */
    public static Description parseDescription(String input) throws ParseException {
        if (!Description.isValidDescription(input)) {
            throw new ParseException("Task description is invalid");
        }
        return new Description(input);
    }

    /**
     * Parses type.
     *
     * @param input user input.
     * @return a Type object.
     * @throws ParseException if the type is invalid.
     */
    public static Type parseType(String input) throws ParseException {
        String inputAllUpperCase = input.toUpperCase();
        switch(inputAllUpperCase) {
        case("ASSIGNMENT"):
            return Type.ASSIGNMENT;
        case("LAB"):
            return Type.LAB;
        case("TUTORIAL"):
            return Type.TUTORIAL;
        case("PROJECT"):
            return Type.PROJECT;
        case("STUDY"):
            return Type.STUDY;
        case("DAILY"):
            return Type.DAILY;
        default:
            throw new ParseException("Task type is invalid");
        }
    }

    /**
     * Parses date into a Date object.
     *
     * @param input user input.
     * @return a Date object.
     * @throws ParseException if the date is invalid.
     */
    public static Date parseDate(String input) throws ParseException {
        if (!Date.isValidDate(input)) {
            throw new ParseException("Task date is invalid");
        }
        return new Date(input);
    }

    /**
     * Parses priority.
     *
     * @param input user input.
     * @return a Priority object.
     * @throws ParseException if the priority is invalid.
     */
    public static Priority parsePriority(String input) throws ParseException {
        String inputAllUpperCase = input.toUpperCase();
        switch(inputAllUpperCase) {
        case("HIGHEST"):
            return Priority.HIGHEST;
        case("HIGH"):
            return Priority.HIGH;
        case("NORMAL"):
            return Priority.NORMAL;
        case("LOW"):
            return Priority.LOW;
        default:
            throw new ParseException("Task priority is invalid");
        }
    }

    /**
     * Parses criterion.
     *
     * @param input user input.
     * @return a Criterion object.
     * @throws ParseException if the criterion is invalid.
     */
    public static Criterion parseCriterion(String input) throws ParseException {
        String inputAllUpperCase = input.toUpperCase();
        switch(inputAllUpperCase) {
        case("DESCRIPTION"):
        case("DESC"):
            return Criterion.DESCRIPTION;
        case("DATE"):
        case("DEADLINE"):
            return Criterion.DATE;
        case("PRIORITY"):
        case("PRIO"):
            return Criterion.PRIORITY;
        default:
            throw new ParseException("Criterion for sorting is invalid");
        }
    }

    /**
     * Parses keyword.
     *
     * @param input user input.
     * @return the keyword as string.
     * @throws ParseException if the keyword is more than one word.
     */
    public static String parseKeyWord(String input) throws ParseException {
        if (input.trim().length() > 1) {
            throw new ParseException("Keyword can only be one word");
        }
        return input;
    }
}
