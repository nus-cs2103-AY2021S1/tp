package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents an interface through which other Parsers will implement.
 */
public interface PageParser {
    Command parseCommand(String commandText) throws ParseException;
}
