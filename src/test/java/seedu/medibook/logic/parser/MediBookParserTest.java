package seedu.medibook.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medibook.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.medibook.testutil.Assert.assertThrows;
import static seedu.medibook.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.medibook.logic.commands.AccessCommand;
import seedu.medibook.logic.commands.AddCommand;
import seedu.medibook.logic.commands.AddNoteCommand;
import seedu.medibook.logic.commands.ClearCommand;
import seedu.medibook.logic.commands.DeleteCommand;
import seedu.medibook.logic.commands.DeleteNoteCommand;
import seedu.medibook.logic.commands.EditCommand;
import seedu.medibook.logic.commands.EditNoteCommand;
import seedu.medibook.logic.commands.ExitCommand;
import seedu.medibook.logic.commands.FindCommand;
import seedu.medibook.logic.commands.HelpCommand;
import seedu.medibook.logic.commands.ListCommand;
import seedu.medibook.logic.parser.exceptions.ParseException;
import seedu.medibook.model.commonfields.Date;
import seedu.medibook.model.medicalnote.Content;
import seedu.medibook.model.patient.FieldContainsKeywordsPredicate;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.testutil.EditPatientDescriptorBuilder;
import seedu.medibook.testutil.PatientBuilder;
import seedu.medibook.testutil.PatientUtil;

public class MediBookParserTest {

    private final MediBookParser parser = new MediBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Patient patient = new PatientBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PatientUtil.getAddCommand(patient));
        assertEquals(new AddCommand(patient), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_access() throws Exception {
        AccessCommand command = (AccessCommand) parser.parseCommand(
                AccessCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new AccessCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Patient patient = new PatientBuilder().build();
        EditCommand.EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(patient).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + PatientUtil.getEditPatientDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_NAME
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new FieldContainsKeywordsPredicate(keywords, PREFIX_NAME)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_addnote() throws Exception {
        Date date = new Date("19-02-2020", true);
        Content content = new Content("Patient is good.");
        AddNoteCommand command = (AddNoteCommand) parser.parseCommand(AddNoteCommand.COMMAND_WORD + " "
                + PREFIX_DATE + "19-02-2020 " + PREFIX_CONTENT + "Patient is good.");

        assertEquals(new AddNoteCommand(date, content), command);
    }

    @Test
    public void parseCommand_deletenote() throws Exception {
        DeleteNoteCommand command = (DeleteNoteCommand) parser.parseCommand(
                DeleteNoteCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteNoteCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_editnote() throws Exception {
        EditNoteCommand.EditNoteDescriptor descriptor = new EditNoteCommand.EditNoteDescriptor();

        descriptor.setDate(new Date("19-02-2020", true));
        descriptor.setContent(new Content("Patient is good."));

        EditNoteCommand command = (EditNoteCommand) parser.parseCommand(
                EditNoteCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased() + " "
                        + PREFIX_DATE + "19-02-2020 "
                        + PREFIX_CONTENT + "Patient is good.");
        assertEquals(new EditNoteCommand(INDEX_FIRST, descriptor), command);
    }
}
