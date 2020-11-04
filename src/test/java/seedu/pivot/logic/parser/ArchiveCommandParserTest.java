package seedu.pivot.logic.parser;

import static seedu.pivot.commons.core.UserMessages.MESSAGE_INCORRECT_MAIN_PAGE;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.commands.Command.TYPE_CASE;
import static seedu.pivot.logic.commands.Command.TYPE_DOC;
import static seedu.pivot.logic.parser.ArchiveCommandParser.MESSAGE_CASE_INCORRECT_SECTION_CASE_PAGE;
import static seedu.pivot.logic.parser.ArchiveCommandParser.MESSAGE_CASE_INCORRECT_SECTION_MAIN_PAGE;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.pivot.testutil.TypicalIndexes.FIRST_INDEX;

import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.archivecommands.ArchiveCommand;
import seedu.pivot.logic.state.StateManager;

public class ArchiveCommandParserTest {

    private static Index index = Index.fromZeroBased(FIRST_INDEX.getZeroBased());
    private ArchiveCommandParser parser = new ArchiveCommandParser();

    public void setUpMainPageDefaultSection() {
        StateManager.setDefaultSection();
        StateManager.resetState();
    }

    public void setUpMainPageArchivedSection() {
        StateManager.setArchivedSection();
        StateManager.resetState();
    }

    public void setUpCasePageDefaultSection() {
        StateManager.setDefaultSection();
        StateManager.setState(index);
    }

    public void setUpCasePageArchivedSection() {
        StateManager.setArchivedSection();
        StateManager.setState(index);
    }

    // Tests for Default Page
    @Test
    public void parseDefaultSectionCasePage_emptyArg_throwsParseException() {
        setUpCasePageDefaultSection();
        assertParseFailure(parser, "     ", MESSAGE_INCORRECT_MAIN_PAGE);
        StateManager.resetState();
    }

    @Test
    public void parseDefaultSectionCasePage_validArgs_throwsParseException() {
        setUpCasePageDefaultSection();
        assertParseFailure(parser, TYPE_CASE + " " + FIRST_INDEX, MESSAGE_INCORRECT_MAIN_PAGE);
        StateManager.resetState();
    }

    @Test
    public void parseDefaultSectionMainPage_emptyArg_throwsParseException() {
        setUpMainPageDefaultSection();
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ArchiveCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseDefaultSectionMainPage_invalidCommandFormat_throwsParseException() {
        setUpMainPageDefaultSection();
        assertParseFailure(parser, TYPE_DOC + " 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ArchiveCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "invalidType" + " 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ArchiveCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseDefaultSectionMainPage_validArgs_returnsFindCommand() {
        setUpMainPageDefaultSection();

        // no leading and trailing whitespaces
        ArchiveCommand expectedArchiveCommand = new ArchiveCommand(index);
        assertParseSuccess(parser, TYPE_CASE + " 1", expectedArchiveCommand);

        // multiple whitespaces between parameters
        assertParseSuccess(parser, " " + TYPE_CASE + "  1", expectedArchiveCommand);
    }

    // Test for Archived Section
    @Test
    public void parseArchivedSectionMainPage_validArgs_throwsParseException() {
        setUpMainPageArchivedSection();

        assertParseFailure(parser, TYPE_CASE + " " + FIRST_INDEX,
                MESSAGE_CASE_INCORRECT_SECTION_MAIN_PAGE);

    }

    @Test
    public void parseArchivedSectionCasePage_validArgs_throwsParseException() {
        setUpCasePageArchivedSection();

        assertParseFailure(parser, TYPE_CASE + " " + FIRST_INDEX,
                MESSAGE_CASE_INCORRECT_SECTION_CASE_PAGE);
    }

}
