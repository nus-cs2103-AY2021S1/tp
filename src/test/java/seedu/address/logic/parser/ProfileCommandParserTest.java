package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ProfileCommand;

public class ProfileCommandParserTest {

    private ProfileCommandParser parser = new ProfileCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        assertParseSuccess(parser, "81246415 123 Choa Chu Kang Loop",
                new ProfileCommand("123 Choa Chu Kang Loop ", "81246415"));
        assertParseSuccess(parser, "812 122 Tampines Way",
                new ProfileCommand("122 Tampines Way ", "812"));
        assertParseSuccess(parser, "88888888 125 Ridge View Residential College National University of "
                        + "Singapore, Singapore- 676767",
                new ProfileCommand("125 Ridge View Residential College National University of "
                        + "Singapore, Singapore- 676767 ", "88888888"));
    }

    @Test
    public void parse_addressMissing_failure() {
        String format = ProfileCommand.MESSAGE_USAGE;
        assertParseFailure(parser, "8888", format);
        assertParseFailure(parser, "8909890", format);
        assertParseFailure(parser, "88890890", format);
    }

    @Test
    public void parse_invalidPhoneNumber_failure() {
        String illegalPhoneNumber = ProfileCommand.MESSAGE_USAGE;
        assertParseFailure(parser, "88 123 Choa Chu Kang Loop", illegalPhoneNumber);
        assertParseFailure(parser, "8889aaaa 1234 Tampines way", illegalPhoneNumber);
    }
}
