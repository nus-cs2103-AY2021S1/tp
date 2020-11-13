package seedu.internhunter.logic.parser.view;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.view.ViewProfileCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the ViewCompanyCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the ViewCompanyCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the GeneralParserUtil, and
 * therefore should be covered by the GeneralParserUtilTest.
 */
public class ViewProfileCommandParserTest {

    private ViewProfileCommandParser viewProfileCommandParser;

    @BeforeEach
    public void setUp() {
        viewProfileCommandParser = new ViewProfileCommandParser();
    }

    @Test
    public void parse_validArgs_returnsViewProfileCommand() {
        assertParseSuccess(viewProfileCommandParser, "1", new ViewProfileCommand(INDEX_FIRST));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        final String messageExpected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewProfileCommand.MESSAGE_USAGE);

        // empty argument
        assertParseFailure(viewProfileCommandParser, "", messageExpected);
        // blank argument
        assertParseFailure(viewProfileCommandParser, "  ", messageExpected);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(viewProfileCommandParser, "a", MESSAGE_INVALID_INDEX);
    }

}
