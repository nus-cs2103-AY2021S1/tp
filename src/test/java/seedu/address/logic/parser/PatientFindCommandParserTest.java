package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.patientcommands.PatientFindCommand;
import seedu.address.logic.parser.patientparser.FindCommandParser;
import seedu.address.model.filters.patientfilters.SearchNameFilter;

public class PatientFindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PatientFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        PatientFindCommand expectedPatientFindCommand =
                new PatientFindCommand(new SearchNameFilter(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedPatientFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedPatientFindCommand);
    }

}
