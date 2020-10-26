package seedu.address.logic.parser;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;

public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public HelpCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        String trimmedInput = userInput.trim().toLowerCase();
        return new HelpCommand(trimmedInput);
    }
}
