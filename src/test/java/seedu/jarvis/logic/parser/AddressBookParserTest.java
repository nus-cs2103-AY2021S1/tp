package seedu.jarvis.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.jarvis.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.jarvis.logic.commands.CommandTestUtil.EDIT_STUDENT;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.jarvis.logic.commands.ExitCommand;
import seedu.jarvis.logic.commands.HelpCommand;
import seedu.jarvis.logic.commands.add.AddConsultationCommand;
import seedu.jarvis.logic.commands.add.AddMasteryCheckCommand;
import seedu.jarvis.logic.commands.add.AddTaskCommand;
import seedu.jarvis.logic.commands.delete.DeleteConsultationCommand;
import seedu.jarvis.logic.commands.delete.DeleteMasteryCheckCommand;
import seedu.jarvis.logic.commands.delete.DeleteTaskCommand;
import seedu.jarvis.logic.commands.edit.EditStudentCommand;
import seedu.jarvis.logic.commands.edit.EditStudentCommand.EditPersonDescriptor;
import seedu.jarvis.logic.commands.view.ViewAllStudentsCommand;
import seedu.jarvis.logic.commands.view.ViewConsultationsCommand;
import seedu.jarvis.logic.commands.view.ViewMasteryChecksCommand;
import seedu.jarvis.logic.commands.view.ViewMissionDeadlineCommand;
import seedu.jarvis.logic.commands.view.ViewPastConsultationsCommand;
import seedu.jarvis.logic.commands.view.ViewPastMasteryChecksCommand;
import seedu.jarvis.logic.commands.view.ViewQuestDeadlineCommand;
import seedu.jarvis.logic.commands.view.ViewTaskListCommand;
import seedu.jarvis.logic.commands.view.ViewUngradedMissionCommand;
import seedu.jarvis.logic.commands.view.ViewUngradedQuestCommand;
import seedu.jarvis.logic.commands.view.ViewUpcomingConsultationsCommand;
import seedu.jarvis.logic.commands.view.ViewUpcomingMasteryChecksCommand;
import seedu.jarvis.logic.parser.exceptions.ParseException;
import seedu.jarvis.model.student.Student;
import seedu.jarvis.testutil.EditStudentDescriptorBuilder;
import seedu.jarvis.testutil.StudentBuilder;
import seedu.jarvis.testutil.StudentUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        // Parse adding of tasks
        assertTrue(parser.parseCommand("add -t Workout") instanceof AddTaskCommand);

        // Parse adding of consultations
        assertTrue(parser.parseCommand("add -c John Doe d/2020-09-20 t/13:30") instanceof AddConsultationCommand);

        // Parse adding of mastery checks
        assertTrue(parser.parseCommand("add -mc John Doe d/2020-09-20 t/13:30") instanceof AddMasteryCheckCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        // Parse deleting of tasks
        assertTrue(parser.parseCommand("delete -t T1") instanceof DeleteTaskCommand);

        // Parse deleting of consultations
        assertTrue(parser.parseCommand("delete -c 3") instanceof DeleteConsultationCommand);

        // Parse deleting of mastery checks
        assertTrue(parser.parseCommand("delete -mc 2") instanceof DeleteMasteryCheckCommand);
    }

    @Test
    public void parseCommand_editStudent() throws Exception {
        Student student = new StudentBuilder().build();
        EditPersonDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditStudentCommand command = (EditStudentCommand) parser.parseCommand(
                EditStudentCommand.COMMAND_WORD + " " + EDIT_STUDENT + INDEX_FIRST.getOneBased() + " "
                + StudentUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditStudentCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_view() throws Exception {
        // View all students
        assertTrue(parser.parseCommand(ViewAllStudentsCommand.SAMPLE_COMMAND) instanceof ViewAllStudentsCommand);

        // View mission deadlines
        assertTrue(parser.parseCommand("view -m") instanceof ViewMissionDeadlineCommand);

        // View quest deadlines
        assertTrue(parser.parseCommand("view -q") instanceof ViewQuestDeadlineCommand);

        // View ungraded missions
        assertTrue(parser.parseCommand("view -um") instanceof ViewUngradedMissionCommand);

        // View ungraded quests
        assertTrue(parser.parseCommand("view -uq") instanceof ViewUngradedQuestCommand);

        // View all consultations command
        assertTrue(parser.parseCommand("view -c") instanceof ViewConsultationsCommand);

        // View past consultations command
        assertTrue(parser.parseCommand("view -cp") instanceof ViewPastConsultationsCommand);

        // View upcoming consultations command
        assertTrue(parser.parseCommand("view -cu") instanceof ViewUpcomingConsultationsCommand);

        // View all mastery checks command
        assertTrue(parser.parseCommand("view -mc") instanceof ViewMasteryChecksCommand);

        // View past mastery checks command
        assertTrue(parser.parseCommand("view -mcp") instanceof ViewPastMasteryChecksCommand);

        // View upcoming mastery checks command
        assertTrue(parser.parseCommand("view -mcu") instanceof ViewUpcomingMasteryChecksCommand);

        // View all tasks
        assertTrue(parser.parseCommand("view -t") instanceof ViewTaskListCommand);

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
