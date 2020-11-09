package seedu.address.logic.parser.event;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.schedulercommands.ViewEventCommand;
import seedu.address.logic.parser.schedulerparsers.ViewEventParser;



public class ViewEventParserTest {

    private ViewEventParser parser = new ViewEventParser();

    @Test
    public void parse_validArgs_returnsViewCommand() {
        assertParseSuccess(parser, "1", new ViewEventCommand(INDEX_FIRST_MODULE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_missingIndexField_throwsParseException() {
        assertParseFailure(parser, "", MESSAGE_INVALID_INDEX);
    }
}
