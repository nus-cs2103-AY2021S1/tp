package seedu.address.logic.parser.patientparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.patientcommands.PatientFindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.filters.patientfilters.SearchNameFilter;

/**
 * Parses input arguments and creates a new PatientFindCommand object
 */
public class FindCommandParser implements Parser<PatientFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PatientFindCommand
     * and returns a PatientFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PatientFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PatientFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new PatientFindCommand(new SearchNameFilter(Arrays.asList(nameKeywords)));
    }

}
