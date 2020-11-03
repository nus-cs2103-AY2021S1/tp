package seedu.expense.logic.parser;

import static seedu.expense.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.expense.logic.commands.ResetAliasCommand;
import seedu.expense.logic.parser.exceptions.ParseException;

/**
 * Creates a new ResetAliasCommand object
 */
public class ResetAliasCommandParser implements Parser<ResetAliasCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AliasCommand
     * and returns an AliasCommand object for execution. Arguments must be empty as
     * the ResetAliasCommand takes in no argument in its constructor (it is a complete
     * deletion command).
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ResetAliasCommand parse(String args) throws ParseException {
        if (!args.trim().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, ResetAliasCommand.MESSAGE_USAGE));
        }
        return new ResetAliasCommand();
    }

}
