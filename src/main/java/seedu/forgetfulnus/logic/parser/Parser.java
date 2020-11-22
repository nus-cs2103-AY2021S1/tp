package seedu.forgetfulnus.logic.parser;

import seedu.forgetfulnus.logic.commands.Command;
import seedu.forgetfulnus.logic.parser.exceptions.ParseException;
import seedu.forgetfulnus.logic.parser.exceptions.ParseZeroException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseZeroException if the user input is '0'.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException, ParseZeroException;
}
