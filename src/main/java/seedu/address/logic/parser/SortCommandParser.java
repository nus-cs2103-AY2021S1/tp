package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public SortCommand parse(String args) throws ParseException {
        ArrayList<String> validSort = new ArrayList<>();
        validSort.add("name");
        validSort.add("nric");
        String trimmedArg = args.trim().toLowerCase();

        if (trimmedArg.isEmpty() || !validSort.contains(trimmedArg)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        return new SortCommand(trimmedArg);
    }
}
