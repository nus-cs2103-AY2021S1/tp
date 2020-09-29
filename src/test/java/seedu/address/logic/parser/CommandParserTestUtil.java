package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.parameter.OptionalParameter;
import seedu.address.logic.parser.parameter.Parameter;

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

    public static Parameter<String> makeDummyParameter(String name, String flag) {
        return new Parameter<>(name, flag, "test", "test", (s)->s);
    }

    public static OptionalParameter<String> makeDummyOptionalParameter(String name, String flag) {
        return new OptionalParameter<>(name, flag, "test", "test", (s)->s);
    }

    public static class ParameterStub<T> extends Parameter<T> {
        private final T value;

        /**
         * Creates a parameter with a fixed flag and value.
         * @param flag flag for parameter
         * @param value fixed value
         */
        public ParameterStub(String flag, T value) {
            super("stub", flag, "stub", "NA", s -> value);
            this.value = value;
        }
        @Override
        public T consume() {
            return this.value;
        }
    }

    public static class OptionalParameterStub<T> extends OptionalParameter<T> {
        private final Optional<T> value;

        /**
         * Creates an optional parameter with a fixed flag and value.
         * @param flag flag for parameter
         * @param value fixed value
         */
        public OptionalParameterStub(String flag, T value) {
            super("stub", flag, "stub", "NA", s -> value);
            this.value = Optional.of(value);
        }

        /**
         * Creates an optional parameter with a fixed flag but no value.
         * @param flag flag for parameter
         */
        public OptionalParameterStub(String flag) {
            super("stub", flag, "stub", "NA", s -> null);
            this.value = Optional.empty();
        }
        @Override
        public Optional<T> getValue() {
            return this.value;
        }
    }
}
