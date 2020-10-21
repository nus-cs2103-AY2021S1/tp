package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NON_EMPTY_ARGUMENT;

import seedu.address.logic.commands.UnassignallCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UnassignallCommandParser implements Parser<UnassignallCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnassignallCommand
     * and returns an UnassignallCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnassignallCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (!args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_NON_EMPTY_ARGUMENT,
                UnassignallCommand.MESSAGE_USAGE));
        }

        return new UnassignallCommand();
    }

}
