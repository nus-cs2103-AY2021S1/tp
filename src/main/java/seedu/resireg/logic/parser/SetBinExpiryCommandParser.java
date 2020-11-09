package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.resireg.logic.commands.SetBinExpiryCommand;
import seedu.resireg.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class SetBinExpiryCommandParser implements Parser<SetBinExpiryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RestoreCommand
     * and returns a RestoreCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetBinExpiryCommand parse(String args) throws ParseException {
        try {
            int daysStoredInBin = ParserUtil.parseDaysAsInt(args);
            return new SetBinExpiryCommand(daysStoredInBin);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBinExpiryCommand.HELP.getFullMessage()), pe);
        }
    }

}
