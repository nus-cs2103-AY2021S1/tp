package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains helper methods for testing command parsers.
 */
public class CommandParserTestUtil {

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseSuccess(Parser parser, String userInput, Command expectedCommand) {
        try {
            Command command = parser.parse(userInput);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertParseFailure(Parser parser, String userInput, String expectedMessage) {
        try {
            parser.parse(userInput);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }

    //@@author zeying99

    /**
     * Asserts that the parsing of {@code userInput} by {@code FlashcardParser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertFlashcardParseSuccess(FlashcardParser parser, String userInput, Command expectedCommand) {
        try {
            Command command = parser.parseCommand(userInput);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code FlashcardParser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertFlashcardParseFailure(FlashcardParser parser, String userInput, String expectedMessage) {
        try {
            parser.parseCommand(userInput);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code QuizParser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertQuizParseSuccess(QuizParser parser, String userInput, Command expectedCommand) {
        try {
            Command command = parser.parseCommand(userInput);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code QuizParser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertQuizParseFailure(QuizParser parser, String userInput, String expectedMessage) {
        try {
            parser.parseCommand(userInput);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code PerformanceParser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertPerformanceParseSuccess(PerformanceParser parser, String userInput,
                                                     Command expectedCommand) {
        try {
            Command command = parser.parseCommand(userInput);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code PerformanceParser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertPerformanceParseFailure(PerformanceParser parser, String userInput,
                                                     String expectedMessage) {
        try {
            parser.parseCommand(userInput);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }
}
