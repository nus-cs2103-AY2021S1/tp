package seedu.internhunter.logic.parser.view;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE_ABRIDGED;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.view.ViewApplicationCommand;
import seedu.internhunter.logic.commands.view.ViewCommand;
import seedu.internhunter.logic.commands.view.ViewCompanyCommand;
import seedu.internhunter.logic.commands.view.ViewProfileCommand;
import seedu.internhunter.logic.parser.exceptions.ParseException;

/**
 * Tests the view command parser for the view commands. Unit testing is done separately for each view item command
 * parser.
 */
public class ViewCommandParserTest {

    private ViewCommandParser viewCommandParser;

    @BeforeEach
    public void setUp() {
        viewCommandParser = new ViewCommandParser();
    }

    @Test
    public void parse_blankItemType_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, expectedMessage, () -> viewCommandParser.parse("  "));
    }

    @Test
    public void parse_invalidTypes_throwsParseException() {
        // invalid item type
        assertThrows(ParseException.class, MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, () ->
                viewCommandParser.parse(INTERNSHIP_ALIAS + " " + INDEX_FIRST.getOneBased()));
        assertThrows(ParseException.class, MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, () ->
                        viewCommandParser.parse("null" + " " + INDEX_FIRST.getOneBased()));
    }

    @Test
    public void parse_correctFormat_success() throws ParseException {
        // item type app, index present
        assertTrue(viewCommandParser.parse(COMPANY_ALIAS + " " + INDEX_FIRST.getOneBased())
                instanceof ViewCompanyCommand);
        assertTrue(viewCommandParser.parse(APPLICATION_ALIAS + " " + INDEX_FIRST.getOneBased())
                instanceof ViewApplicationCommand);
        assertTrue(viewCommandParser.parse(PROFILE_ALIAS + " " + INDEX_FIRST.getOneBased())
                instanceof ViewProfileCommand);
    }

}
