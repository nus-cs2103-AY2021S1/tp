package seedu.address.logic.parser.patient;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_TEMP_RANGE;
import static seedu.address.testutil.command.PatientCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.testutil.command.PatientCommandTestUtil.PERIOD_DESC_AMY;
import static seedu.address.testutil.command.PatientCommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.testutil.command.PatientCommandTestUtil.TEMP_DESC_AMY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.patient.SearchPatientCommand;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.TemperatureRange;

public class SearchPatientCommandParserTest {

    private SearchPatientCommandParser parser = new SearchPatientCommandParser();

    @Test
    void parseError_empty_input() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchPatientCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchPatientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {

        String invalidTempearature = " " + PREFIX_TEMP_RANGE + "36.0-37";
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        //invalid temperatureRange
        assertParseFailure(parser, invalidTempearature, TemperatureRange.MESSAGE_CONSTRAINTS_TEMPERATURERANGE);
    }

    @Test
    public void parse_invalidPrefix_failure() {
        //invalid temperature Prefix
        assertParseFailure(parser, TEMP_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchPatientCommand.MESSAGE_USAGE));

        //invalid period of stay Prefix
        assertParseFailure(parser, PERIOD_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchPatientCommand.MESSAGE_USAGE));

        //invalid phone Prefix
        assertParseFailure(parser, PHONE_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchPatientCommand.MESSAGE_USAGE));
    }
}
