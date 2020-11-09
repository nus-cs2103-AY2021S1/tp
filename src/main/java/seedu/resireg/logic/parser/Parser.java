package seedu.resireg.logic.parser;

import seedu.resireg.logic.commands.Command;
import seedu.resireg.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 *
 * Assumption: the Parser is stateless, ie. an instance behaves identically no matter how many times {@code parse()}
 * is called.
 */
public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException;
}
