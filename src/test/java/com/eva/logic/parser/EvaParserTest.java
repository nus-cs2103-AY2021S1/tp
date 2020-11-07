package com.eva.logic.parser;

import static com.eva.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.eva.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static com.eva.logic.parser.CliSyntax.PREFIX_STAFF;
import static com.eva.testutil.Assert.assertThrows;
import static com.eva.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static com.eva.testutil.staff.StaffUtil.getAddStaffCommand;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.eva.logic.commands.AddStaffCommand;
import com.eva.logic.commands.ClearCommand;
import com.eva.logic.commands.DeleteStaffCommand;
import com.eva.logic.commands.ExitCommand;
import com.eva.logic.commands.FindApplicantCommand;
import com.eva.logic.commands.FindCommand;
import com.eva.logic.commands.FindStaffCommand;
import com.eva.logic.commands.HelpCommand;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.model.person.NameContainsKeywordsPredicate;
import com.eva.model.person.staff.Staff;
import com.eva.testutil.staff.StaffBuilder;


public class EvaParserTest {

    private final EvaParser parser = new EvaParser();


    @Test
    public void parseCommand_addStaff() throws Exception {
        Staff staff = new StaffBuilder().build();
        AddStaffCommand command = (AddStaffCommand) parser.parseCommand(getAddStaffCommand(staff));
        assertEquals(new AddStaffCommand(staff), command);
    }


    @Test
    public void parseCommand_clear() throws Exception {
        ClearCommand command = (ClearCommand) parser.parseCommand(ClearCommand.COMMAND_WORD + " s-");
        assertEquals(command, new ClearCommand(PREFIX_STAFF));
    }

    @Test
    public void parseCommand_deleteStaff() throws Exception {
        DeleteStaffCommand command = (DeleteStaffCommand) parser.parseCommand(
                DeleteStaffCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteStaffCommand(INDEX_FIRST_PERSON), command);
    }

    // Must edit to fit person and staff
    /*@Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }*/

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findStaff() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindStaffCommand command = (FindStaffCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " s- " + String.join(" ", keywords));
        assertEquals(new FindStaffCommand(new NameContainsKeywordsPredicate<>(keywords)), command);
    }

    @Test
    public void parseCommand_findApplicant() throws Exception {
        List<String> keywords = Arrays.asList("one", "two", "three");
        FindApplicantCommand command = (FindApplicantCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " a- " + String.join(" ", keywords));
        assertEquals(new FindApplicantCommand(new NameContainsKeywordsPredicate<>(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    /*
    TODO
    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }
     */

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
