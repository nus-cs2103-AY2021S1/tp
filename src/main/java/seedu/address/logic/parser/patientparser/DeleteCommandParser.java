package seedu.address.logic.parser.patientparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.patientcommands.PatientDeleteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new PatientDeleteCommand object
 */
public class DeleteCommandParser implements Parser<PatientDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PatientDeleteCommand
     * and returns a PatientDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PatientDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new PatientDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PatientDeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
