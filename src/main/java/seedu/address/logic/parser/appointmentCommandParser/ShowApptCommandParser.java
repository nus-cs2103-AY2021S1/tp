package seedu.address.logic.parser.appointmentCommandParser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.appointmentcommand.ShowApptCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.NricPredicate;

/**
 * Parses input arguments and creates a new ShowAppt object
 */
public class ShowApptCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the ShowAppt Command
     * and returns a ShowAppt Command object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowApptCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] nameKeywords = trimmedArgs.split("\\s+");

        if (trimmedArgs.isEmpty() || nameKeywords.length != 1 || !Nric.isValidNric(nameKeywords[0])) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowApptCommand.MESSAGE_USAGE));
        }
        return new ShowApptCommand(new NricPredicate(Arrays.asList(nameKeywords)));
    }

}
