package seedu.internhunter.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE_ABRIDGED;
import static seedu.internhunter.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.internhunter.logic.commands.CommandTestUtil.VALID_INDEX_ONE;
import static seedu.internhunter.logic.commands.util.application.ApplicationCommandTestUtil.INDEX_DESC_FIRST;
import static seedu.internhunter.logic.commands.util.application.ApplicationCommandTestUtil.STATUS_DESC_ACCEPTED;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.ADDRESS_DESC_FACEBOOK;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.EMAIL_DESC_FACEBOOK;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.NAME_DESC_FACEBOOK;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.PHONE_DESC_FACEBOOK;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.JOB_TITLE_DESC_SWE;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.CATEGORY_DESC_EXPERIENCE;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.TITLE_DESC_INTERNSHIP;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.ClearCommand;
import seedu.internhunter.logic.commands.ExitCommand;
import seedu.internhunter.logic.commands.HelpCommand;
import seedu.internhunter.logic.commands.MatchCommand;
import seedu.internhunter.logic.commands.SwitchCommand;
import seedu.internhunter.logic.commands.add.AddCommand;
import seedu.internhunter.logic.commands.delete.DeleteCommand;
import seedu.internhunter.logic.commands.edit.EditCommand;
import seedu.internhunter.logic.commands.find.FindApplicationCommand;
import seedu.internhunter.logic.commands.find.FindCommand;
import seedu.internhunter.logic.commands.find.FindCompanyCommand;
import seedu.internhunter.logic.commands.find.FindProfileCommand;
import seedu.internhunter.logic.commands.list.ListApplicationCommand;
import seedu.internhunter.logic.commands.list.ListCommand;
import seedu.internhunter.logic.commands.list.ListCompanyCommand;
import seedu.internhunter.logic.commands.list.ListProfileCommand;
import seedu.internhunter.logic.commands.view.ViewCommand;
import seedu.internhunter.logic.parser.exceptions.ParseException;

public class MainParserTest {

    private final MainParser parser = new MainParser();

    @Test
    public void parseCommand_add() throws Exception {
        assertTrue(parser.parseCommand(AddCommand.COMMAND_WORD + " " + COMPANY_ALIAS
                + NAME_DESC_FACEBOOK + PHONE_DESC_FACEBOOK + ADDRESS_DESC_FACEBOOK
                + EMAIL_DESC_FACEBOOK) instanceof AddCommand);
        assertTrue(parser.parseCommand(AddCommand.COMMAND_WORD + " " + INTERNSHIP_ALIAS + " "
                + INDEX_FIRST.getOneBased() + JOB_TITLE_DESC_SWE) instanceof AddCommand);
        assertTrue(parser.parseCommand(AddCommand.COMMAND_WORD + " " + APPLICATION_ALIAS + " "
                + INDEX_FIRST.getOneBased() + INDEX_DESC_FIRST) instanceof AddCommand);
        assertTrue(parser.parseCommand(AddCommand.COMMAND_WORD + " " + PROFILE_ALIAS + " "
                + TITLE_DESC_INTERNSHIP + CATEGORY_DESC_EXPERIENCE) instanceof AddCommand); }

    @Test
    public void parseCommand_delete() throws Exception {
        assertTrue(parser.parseCommand(DeleteCommand.COMMAND_WORD + " " + COMPANY_ALIAS + " "
                + INDEX_FIRST.getOneBased()) instanceof DeleteCommand);
        assertTrue(parser.parseCommand(DeleteCommand.COMMAND_WORD + " " + INTERNSHIP_ALIAS + " "
                + INDEX_FIRST.getOneBased() + INDEX_DESC_FIRST) instanceof DeleteCommand);
        assertTrue(parser.parseCommand(DeleteCommand.COMMAND_WORD + " " + APPLICATION_ALIAS + " "
                + INDEX_FIRST.getOneBased()) instanceof DeleteCommand);
        assertTrue(parser.parseCommand(DeleteCommand.COMMAND_WORD + " " + PROFILE_ALIAS + " "
                + INDEX_FIRST.getOneBased()) instanceof DeleteCommand);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        assertTrue(parser.parseCommand(EditCommand.COMMAND_WORD + " " + COMPANY_ALIAS + " "
                + INDEX_FIRST.getOneBased() + NAME_DESC_FACEBOOK) instanceof EditCommand);
        assertTrue(parser.parseCommand(EditCommand.COMMAND_WORD + " " + INTERNSHIP_ALIAS + " "
                + INDEX_FIRST.getOneBased() + VALID_INDEX_ONE + JOB_TITLE_DESC_SWE) instanceof EditCommand);
        assertTrue(parser.parseCommand(EditCommand.COMMAND_WORD + " " + APPLICATION_ALIAS + " "
                + INDEX_FIRST.getOneBased() + STATUS_DESC_ACCEPTED) instanceof EditCommand);
        assertTrue(parser.parseCommand(EditCommand.COMMAND_WORD + " " + PROFILE_ALIAS + " "
                + INDEX_FIRST.getOneBased() + TITLE_DESC_INTERNSHIP) instanceof EditCommand);
    }

    @Test
    public void parseCommand_view_success() throws Exception {
        assertTrue(parser.parseCommand(ViewCommand.COMMAND_WORD + " " + COMPANY_ALIAS + " "
                + INDEX_FIRST.getOneBased()) instanceof ViewCommand);
        assertTrue(parser.parseCommand(ViewCommand.COMMAND_WORD + " " + APPLICATION_ALIAS + " "
                + INDEX_FIRST.getOneBased()) instanceof ViewCommand);
        assertTrue(parser.parseCommand(ViewCommand.COMMAND_WORD + " " + PROFILE_ALIAS + " "
                + INDEX_FIRST.getOneBased()) instanceof ViewCommand);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand("clear") instanceof ClearCommand);
        assertTrue(parser.parseCommand("clear 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_match_success() throws Exception {
        assertTrue(parser.parseCommand("match") instanceof MatchCommand);
        assertTrue(parser.parseCommand("match ") instanceof MatchCommand);
        assertTrue(parser.parseCommand("match 3") instanceof MatchCommand);
    }

    @Nested
    class MainParserToFindParserTest {

        @Test
        public void parseCommand_findValidTypes_returnsTrue() throws ParseException {
            assertTrue(parser.parseCommand("find me software") instanceof FindProfileCommand);
            assertTrue(parser.parseCommand("find me 3") instanceof FindProfileCommand);
            assertTrue(parser.parseCommand("find com hardware") instanceof FindCompanyCommand);
            assertTrue(parser.parseCommand("find com 4") instanceof FindCompanyCommand);
            assertTrue(parser.parseCommand("find app developers") instanceof FindApplicationCommand);
            assertTrue(parser.parseCommand("find app 2") instanceof FindApplicationCommand);
        }

        @Test
        public void parseCommand_findMissingTypes_throwsParseException() {
            String invalidMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);
            assertThrows(ParseException.class, invalidMessage, () -> parser.parseCommand("find"));
            assertThrows(ParseException.class, invalidMessage, () -> parser.parseCommand("find "));
        }

        @Test
        public void parseCommand_findInvalidTypes_throwsParseException() {
            assertThrows(ParseException.class,
                    MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, () -> parser.parseCommand("find Com"));
            assertThrows(ParseException.class,
                    MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, () -> parser.parseCommand("find App"));
            assertThrows(ParseException.class,
                    MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, () -> parser.parseCommand("find Me"));
            assertThrows(ParseException.class,
                    MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, () -> parser.parseCommand("find Hello"));
            assertThrows(ParseException.class,
                    MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, () -> parser.parseCommand("find 1"));
        }

        @Test
        public void parseCommand_missingDescription_throwsParseException() {
            // missing description for app
            String appMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindApplicationCommand.MESSAGE_USAGE);
            assertThrows(ParseException.class, appMessage, () -> parser.parseCommand("find app "));
            assertThrows(ParseException.class, appMessage, () -> parser.parseCommand("find app"));

            // missing description for com
            String comMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCompanyCommand.MESSAGE_USAGE);
            assertThrows(ParseException.class, comMessage, () -> parser.parseCommand("find com "));
            assertThrows(ParseException.class, comMessage, () -> parser.parseCommand("find com"));

            // missing description for me
            String meMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindProfileCommand.MESSAGE_USAGE);
            assertThrows(ParseException.class, meMessage, () -> parser.parseCommand("find me "));
            assertThrows(ParseException.class, meMessage, () -> parser.parseCommand("find me"));
        }
    }

    @Nested
    class MainParserToListParserTest {

        @Test
        public void parseCommand_listValidTypes_returnsTrue() throws ParseException {
            assertTrue(parser.parseCommand("list me") instanceof ListProfileCommand);
            assertTrue(parser.parseCommand("list com") instanceof ListCompanyCommand);
            assertTrue(parser.parseCommand("list app") instanceof ListApplicationCommand);
        }

        @Test
        public void parseCommand_listMissingTypes_throwsParseException() {
            String invalidMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE);
            assertThrows(ParseException.class, invalidMessage, () -> parser.parseCommand("list"));
            assertThrows(ParseException.class, invalidMessage, () -> parser.parseCommand("list "));
        }

        @Test
        public void parseCommand_listInvalidTypes_throwsParseException() {
            assertThrows(ParseException.class,
                    MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, () -> parser.parseCommand("list Com"));
            assertThrows(ParseException.class,
                    MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, () -> parser.parseCommand("list App"));
            assertThrows(ParseException.class,
                    MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, () -> parser.parseCommand("list Me"));
            assertThrows(ParseException.class,
                    MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, () -> parser.parseCommand("list hello"));
            assertThrows(ParseException.class,
                    MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, () -> parser.parseCommand("list 1"));
        }

        @Test
        public void parseCommand_listExcessInput_throwsParseException() {
            String message = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.EXCESS_MESSAGE);
            assertThrows(ParseException.class, message, () -> parser.parseCommand("list com hello"));
            assertThrows(ParseException.class, message, () -> parser.parseCommand("list app 1"));
            assertThrows(ParseException.class, message, () -> parser.parseCommand("list me great"));
        }

    }

    @Nested
    class MainParserToSwitchParserTest {

        @Test
        public void parseCommand_switchValidTypes_returnsTrue() throws ParseException {
            assertTrue(parser.parseCommand("switch me") instanceof SwitchCommand);
            assertTrue(parser.parseCommand("switch com") instanceof SwitchCommand);
            assertTrue(parser.parseCommand("switch app") instanceof SwitchCommand);
        }

        @Test
        public void parseCommand_switchMissingTypes_throwsParseException() {
            String invalidMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchCommand.MESSAGE_USAGE);
            assertThrows(ParseException.class, invalidMessage, () -> parser.parseCommand("switch"));
        }

        @Test
        public void parseCommand_switchInvalidTypes_throwsParseException() {
            assertThrows(ParseException.class,
                    MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, () -> parser.parseCommand("switch hello"));
            assertThrows(ParseException.class,
                    MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, () -> parser.parseCommand("switch 1"));
            assertThrows(ParseException.class,
                    MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, () -> parser.parseCommand("switch Com"));
            assertThrows(ParseException.class,
                    MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, () -> parser.parseCommand("switch App"));
            assertThrows(ParseException.class,
                    MESSAGE_INVALID_ITEM_TYPE_ABRIDGED, () -> parser.parseCommand("switch Me"));
        }

        @Test
        public void parseCommand_switchExcessInput_throwsParseException() {
            assertThrows(ParseException.class,
                    SwitchCommand.EXCESS_MESSAGE, () -> parser.parseCommand("switch com hello"));
            assertThrows(ParseException.class,
                    SwitchCommand.EXCESS_MESSAGE, () -> parser.parseCommand("switch app great"));
            assertThrows(ParseException.class,
                    SwitchCommand.EXCESS_MESSAGE, () -> parser.parseCommand("switch me ok"));
        }
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand("exit") instanceof ExitCommand);
        assertTrue(parser.parseCommand("exit 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand("help") instanceof HelpCommand);
        assertTrue(parser.parseCommand("help 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        String errorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, errorMessage, () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

}
