package seedu.cc.logic.parser;

import static seedu.cc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.accountlevel.SwitchAccountCommand;
import seedu.cc.logic.parser.exceptions.ParseException;
import seedu.cc.logic.parser.util.ParserUtil;

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
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchAccountCommand.MESSAGE_USAGE), pe);
        }
    }

}
