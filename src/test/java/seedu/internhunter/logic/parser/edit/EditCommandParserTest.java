package seedu.internhunter.logic.parser.edit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE;
import static seedu.internhunter.logic.commands.CommandTestUtil.VALID_INDEX_ONE;
import static seedu.internhunter.logic.commands.util.application.ApplicationCommandTestUtil.STATUS_DESC_ACCEPTED;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.NAME_DESC_FACEBOOK;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.JOB_TITLE_DESC_SWE;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.TITLE_DESC_INTERNSHIP;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.edit.EditApplicationCommand;
import seedu.internhunter.logic.commands.edit.EditCommand;
import seedu.internhunter.logic.commands.edit.EditCompanyCommand;
import seedu.internhunter.logic.commands.edit.EditInternshipCommand;
import seedu.internhunter.logic.commands.edit.EditProfileCommand;
import seedu.internhunter.logic.parser.exceptions.ParseException;

/**
 * Tests the edit command parser for the edit commands. Unit testing is done separately for each edit item command
 * parser.
 */
public class EditCommandParserTest {

    private EditCommandParser editCommandParser;

    @BeforeEach
    public void setUp() {
        editCommandParser = new EditCommandParser();
    }

    @Test
    public void parse_blankItemType_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, expectedMessage, () -> editCommandParser.parse("  "));
    }

    @Test
    public void parse_invalidTypes_throwsParseException() {
        // invalid item type
        assertThrows(ParseException.class, MESSAGE_INVALID_ITEM_TYPE, () -> editCommandParser.parse("appx"));
        assertThrows(ParseException.class, MESSAGE_INVALID_ITEM_TYPE, () -> editCommandParser.parse("null 1"));
    }

    @Test
    public void parse_correctFormat_success() throws ParseException {
        assertTrue(editCommandParser.parse(COMPANY_ALIAS + " "
                + INDEX_FIRST.getOneBased() + NAME_DESC_FACEBOOK) instanceof EditCompanyCommand);
        assertTrue(editCommandParser.parse(INTERNSHIP_ALIAS + " "
                + INDEX_FIRST.getOneBased() + VALID_INDEX_ONE + JOB_TITLE_DESC_SWE) instanceof EditInternshipCommand);
        assertTrue(editCommandParser.parse(APPLICATION_ALIAS + " "
                + INDEX_FIRST.getOneBased() + STATUS_DESC_ACCEPTED) instanceof EditApplicationCommand);
        assertTrue(editCommandParser.parse(PROFILE_ALIAS + " "
                + INDEX_FIRST.getOneBased() + TITLE_DESC_INTERNSHIP) instanceof EditProfileCommand);
    }

}
