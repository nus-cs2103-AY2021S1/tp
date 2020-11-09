package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.patientcommands.HelpCommand;
import seedu.address.logic.commands.patientcommands.PatientAddCommand;
import seedu.address.logic.commands.patientcommands.PatientDeleteCommand;
import seedu.address.logic.commands.patientcommands.PatientEditCommand;
import seedu.address.logic.commands.patientcommands.PatientEditCommand.EditPatientDescriptor;
import seedu.address.logic.commands.patientcommands.PatientFindCommand;
import seedu.address.logic.commands.patientcommands.PatientListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.filters.patientfilters.SearchNameFilter;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.EditPatientDescriptorBuilder;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.PatientUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Patient patient = new PatientBuilder().build();
        PatientAddCommand command = (PatientAddCommand) parser.parseCommand(PatientUtil.getAddCommand(patient));
        assertEquals(new PatientAddCommand(patient), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        PatientDeleteCommand command = (PatientDeleteCommand) parser.parseCommand(
                PatientDeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PATIENT.getOneBased());
        assertEquals(new PatientDeleteCommand(INDEX_FIRST_PATIENT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Patient patient = new PatientBuilder().build();
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(patient).build();
        PatientEditCommand command = (PatientEditCommand) parser.parseCommand(PatientEditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PATIENT.getOneBased() + " " + PatientUtil.getEditPatientDescriptorDetails(descriptor));
        assertEquals(new PatientEditCommand(INDEX_FIRST_PATIENT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        PatientFindCommand command = (PatientFindCommand) parser.parseCommand(
                PatientFindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new PatientFindCommand(new SearchNameFilter(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(PatientListCommand.COMMAND_WORD) instanceof PatientListCommand);
        assertTrue(parser.parseCommand(PatientListCommand.COMMAND_WORD + " 3") instanceof PatientListCommand);
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
}
