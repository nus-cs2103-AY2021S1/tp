package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.appointmentcommand.ShowApptCommand;
import seedu.address.logic.parser.appointmentCommandParser.ShowApptCommandParser;
import seedu.address.model.patient.NricPredicate;

public class ShowApptParserTest {
    private ShowApptCommandParser parser = new ShowApptCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String
                .format(MESSAGE_INVALID_COMMAND_FORMAT, ShowApptCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNric_returnsShowApptCommand() {
        // no leading and trailing whitespaces
        ShowApptCommand expectedCommand =
                new ShowApptCommand(new NricPredicate(Arrays.asList("S1234567D")));
        assertParseSuccess(parser, "S1234567D", expectedCommand);
    }

    @Test
    public void parse_invalidNric_throwsParseException() {
        assertParseFailure(parser, "ddwedwe", String
                .format(MESSAGE_INVALID_COMMAND_FORMAT, ShowApptCommand.MESSAGE_USAGE));
    }
}
