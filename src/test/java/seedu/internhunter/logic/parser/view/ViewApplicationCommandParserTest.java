package seedu.internhunter.logic.parser.view;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.view.ViewApplicationCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the ViewApplicationCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the ViewApplicationCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the GeneralParserUtil, and
 * therefore should be covered by the GeneralParserUtilTest.
 */
public class ViewApplicationCommandParserTest {

    private ViewApplicationCommandParser parser;

    @BeforeEach
    public void setUp() {
        parser = new ViewApplicationCommandParser();
    }

    @Test
    public void parse_validArgs_returnsViewApplicationCommand() {
        assertParseSuccess(parser, "1", new ViewApplicationCommand(INDEX_FIRST));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        final String messageExpected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewApplicationCommand.MESSAGE_USAGE);

        // empty argument
        assertParseFailure(parser, "", messageExpected);
        // blank argument
        assertParseFailure(parser, "  ", messageExpected);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", MESSAGE_INVALID_INDEX);
    }

}
