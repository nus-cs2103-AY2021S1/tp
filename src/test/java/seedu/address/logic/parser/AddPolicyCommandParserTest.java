package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.POLICY_DESCRIPTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPolicies.LIFE_TIME;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.policy.Policy;

public class AddPolicyCommandParserTest {

    private AddPolicyCommandParser parser = new AddPolicyCommandParser();

    @Test
    public void parse_success() {
        Policy expectedPolicy = LIFE_TIME;
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + POLICY_NAME_DESC_AMY + POLICY_DESCRIPTION_DESC_AMY,
                new AddPolicyCommand(expectedPolicy));
    }

    @Test
    public void parse_missingPolicyFields_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(PREAMBLE_WHITESPACE));
        assertThrows(ParseException.class, () -> parser.parse(PREAMBLE_WHITESPACE + POLICY_NAME_DESC_AMY));
        assertThrows(ParseException.class, () -> parser.parse(PREAMBLE_WHITESPACE + POLICY_DESCRIPTION_DESC_AMY));
    }
}
