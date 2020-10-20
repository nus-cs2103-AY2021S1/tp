package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteAccountCommand;
import seedu.address.logic.commands.SwitchAccountCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.ParserUtil;

/**
 * Parses input arguments and creates a new DeleteAccountCommand object
 */
public class SwitchAccountCommandParser implements Parser<SwitchAccountCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SwitchAccountCommand
     * and returns a SwitchAccountCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SwitchAccountCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SwitchAccountCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAccountCommand.MESSAGE_USAGE), pe);
        }
    }

}
