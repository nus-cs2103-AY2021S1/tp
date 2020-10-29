package seedu.pivot.logic.parser;

import static seedu.pivot.commons.core.UserMessages.MESSAGE_INCORRECT_CASE_PAGE;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_INCORRECT_MAIN_PAGE;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.commands.Command.TYPE_ARCHIVE;
import static seedu.pivot.logic.commands.Command.TYPE_CASE;
import static seedu.pivot.logic.commands.Command.TYPE_DOC;
import static seedu.pivot.logic.commands.Command.TYPE_SUSPECT;
import static seedu.pivot.logic.commands.Command.TYPE_VICTIM;
import static seedu.pivot.logic.commands.Command.TYPE_WITNESS;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.pivot.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.pivot.logic.commands.ListCommand;
import seedu.pivot.logic.commands.ListTabCommand;
import seedu.pivot.logic.commands.archivecommands.ListArchiveCommand;
import seedu.pivot.logic.commands.casecommands.ListCaseCommand;
import seedu.pivot.logic.state.StateManager;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_validArgs_returnsListCaseCommand() {
        StateManager.resetState();
        assertParseSuccess(parser, "case", new ListCaseCommand());
    }

    @Test
    public void parse_validArgs_returnsListArchiveCommand() {
        StateManager.resetState();
        assertParseSuccess(parser, "archive", new ListArchiveCommand());
    }

    @Test
    public void parse_validArgs_returnsListTabCommand() {
        StateManager.setState(INDEX_FIRST_PERSON);
        assertParseSuccess(parser, TYPE_DOC, new ListTabCommand(TYPE_DOC));
        assertParseSuccess(parser, TYPE_SUSPECT, new ListTabCommand(TYPE_SUSPECT));
        assertParseSuccess(parser, TYPE_WITNESS, new ListTabCommand(TYPE_WITNESS));
        assertParseSuccess(parser, TYPE_VICTIM, new ListTabCommand(TYPE_VICTIM));
        StateManager.resetState();
    }

    @Test
    public void parse_invalidArgsAtMainPage_throwsParseException() {
        StateManager.resetState(); // At Main Page
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListCommand.MESSAGE_USAGE_MAIN_PAGE));
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListCommand.MESSAGE_USAGE_MAIN_PAGE));
    }

    @Test
    public void parse_invalidArgsAtCasePage_throwsParseException() {
        StateManager.setState(INDEX_FIRST_PERSON); // At Case Page
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListCommand.MESSAGE_USAGE_CASE_PAGE));
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListCommand.MESSAGE_USAGE_CASE_PAGE));
        StateManager.resetState(); // At Main Page
    }

    @Test
    public void parse_casePageArgsAtMainPage_throwsParseException() {
        StateManager.resetState(); // At Main Page
        assertParseFailure(parser, TYPE_DOC, MESSAGE_INCORRECT_CASE_PAGE);
        assertParseFailure(parser, TYPE_SUSPECT, MESSAGE_INCORRECT_CASE_PAGE);
        assertParseFailure(parser, TYPE_VICTIM, MESSAGE_INCORRECT_CASE_PAGE);
        assertParseFailure(parser, TYPE_WITNESS, MESSAGE_INCORRECT_CASE_PAGE);
    }

    @Test
    public void parse_mainPageArgsAtCasePage_throwsParseException() {
        StateManager.setState(INDEX_FIRST_PERSON); // At Case Page
        assertParseFailure(parser, TYPE_CASE, MESSAGE_INCORRECT_MAIN_PAGE);
        assertParseFailure(parser, TYPE_ARCHIVE, MESSAGE_INCORRECT_MAIN_PAGE);
        StateManager.resetState(); // At Main Page
    }
}
