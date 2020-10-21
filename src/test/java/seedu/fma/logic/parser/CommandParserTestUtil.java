package seedu.fma.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.fma.logic.commands.Command;
import seedu.fma.logic.parser.exceptions.ParseException;
import seedu.fma.model.LogBook;

/**
 * Contains helper methods for testing command parsers.
 */
public class CommandParserTestUtil {

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseSuccess(Parser parser, String userInput, Command expectedCommand, LogBook logBook) {
        try {
            Command command = parser.parse(userInput, logBook);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertParseFailure(Parser parser, String userInput, String expectedMessage, LogBook logBook) {
        try {
            parser.parse(userInput, logBook);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }
}
