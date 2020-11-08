package seedu.address.logic.parser.patient;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_TEMP_RANGE;

import seedu.address.logic.commands.patient.SearchPatientCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SearchPatientCommand object
 */
public class SearchPatientCommandParser implements Parser<SearchPatientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SearchPatientCommand
     * and returns an SearchPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SearchPatientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TEMP_RANGE);

        if (!ParserUtil.isExactlyOnePrefixPresent(argMultimap, PREFIX_NAME, PREFIX_TEMP_RANGE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchPatientCommand.MESSAGE_USAGE));
        }

        SearchPatientCommand.SearchPatientDescriptor searchPatientDescriptor =
                new SearchPatientCommand.SearchPatientDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String nameWithoutPrefix = argMultimap.getValue(PREFIX_NAME).get();
            searchPatientDescriptor.setStringName(nameWithoutPrefix);
            searchPatientDescriptor.setName(PatientParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_TEMP_RANGE).isPresent()) {
            searchPatientDescriptor.setTemperatureRange(
                    PatientParserUtil.parseTemperatureRange(argMultimap.getValue(PREFIX_TEMP_RANGE).get()));
        }

        return new SearchPatientCommand(searchPatientDescriptor);
    }
}
