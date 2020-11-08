package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ProfileCommand;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Phone;

public class ProfileCommandParserTest {

    private ProfileCommandParser parser = new ProfileCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        assertParseSuccess(parser, "81246415 123 Choa Chu Kang Loop",
                new ProfileCommand(new Phone("81246415"), new Address("123 Choa Chu Kang Loop")));
        assertParseSuccess(parser, "81234567 122 Tampines Way",
                new ProfileCommand(new Phone("81234567"), new Address("122 Tampines Way")));
        assertParseSuccess(parser, "88888888 125 Ridge View Residential College National University of "
                        + "Singapore, Singapore- 676767",
                new ProfileCommand(new Phone("88888888"),
                        new Address("125 Ridge View Residential College National University of Singapore,"
                                + " Singapore- 676767")));
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
        String illegalPhoneNumber = Phone.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser, "88 123 Choa Chu Kang Loop", illegalPhoneNumber);
        assertParseFailure(parser, "8889aaaa 1234 Tampines way", illegalPhoneNumber);
        assertParseFailure(parser, "00000000 234 Hong Kong Village", illegalPhoneNumber);
    }
}
