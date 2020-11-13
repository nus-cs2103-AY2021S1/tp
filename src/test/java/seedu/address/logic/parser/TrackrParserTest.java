package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAttendanceCommand;
import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.AddTutorialGroupCommand;
import seedu.address.logic.commands.AttendanceBelowCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteAttendanceCommand;
import seedu.address.logic.commands.DeleteModuleCommand;
import seedu.address.logic.commands.DeleteStudentCommand;
//import seedu.address.logic.commands.DeleteTutorialGroupCommand;
import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.logic.commands.EditModuleCommand.EditModuleDescriptor;
//import seedu.address.logic.commands.EditStudentCommand;
//import seedu.address.logic.commands.EditTutorialGroupCommand;
import seedu.address.logic.commands.EditParticipationCommand;
import seedu.address.logic.commands.EditStudentCommand;
//import seedu.address.logic.commands.EditTutorialGroupCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindModuleCommand;
import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.logic.commands.FindTutorialGroupCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListModuleCommand;
import seedu.address.logic.commands.ListStudentCommand;
import seedu.address.logic.commands.ListTutorialGroupCommand;
import seedu.address.logic.commands.ParticipationBelowCommand;
import seedu.address.logic.commands.PreviousViewCommand;
import seedu.address.logic.commands.ViewAttendanceCommand;
import seedu.address.logic.commands.ViewStudentCommand;
import seedu.address.logic.commands.ViewTutorialGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleContainsKeywordsPredicate;
import seedu.address.model.module.ModuleId;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialgroup.TutorialContainsKeywordsPredicate;
import seedu.address.model.tutorialgroup.TutorialGroup;
//import seedu.address.model.tutorialgroup.TutorialGroupId;
import seedu.address.testutil.AttendanceBuilder;
import seedu.address.testutil.AttendanceUtil;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ModuleUtil;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.StudentUtil;
import seedu.address.testutil.TutorialGroupBuilder;
import seedu.address.testutil.TutorialGroupUtil;

public class TrackrParserTest {

    private final TrackrParser parser = new TrackrParser();

    @Test
    public void parseCommand_addModule() throws Exception {
        Module module = new ModuleBuilder().build();
        AddModuleCommand command = (AddModuleCommand) parser.parseCommand(ModuleUtil.getAddModuleCommand(module));
        assertEquals(new AddModuleCommand(module), command);
    }

    @Test
    public void parseCommand_addTutorialGroup() throws Exception {
        TutorialGroup tutorialGroup = new TutorialGroupBuilder().build();
        AddTutorialGroupCommand command = (AddTutorialGroupCommand) parser.parseCommand((
                TutorialGroupUtil.getAddTutorialGroupCommand(tutorialGroup)));
        assertEquals(new AddTutorialGroupCommand(tutorialGroup), command);
    }

    @Test
    public void parseCommand_addStudent() throws Exception {
        Student student = new StudentBuilder().build();
        AddStudentCommand command = (AddStudentCommand) parser.parseCommand(StudentUtil.getAddStudentCommand(student));
        assertEquals(new AddStudentCommand(student), command);
    }

    @Test
    public void parseCommand_addAttendance() throws Exception {
        Attendance attendance = new AttendanceBuilder().build();
        AddAttendanceCommand command =
            (AddAttendanceCommand) parser.parseCommand(AttendanceUtil.getAddAttendanceCommand(attendance));
        assertEquals(new AddAttendanceCommand(INDEX_FIRST_PERSON, new int[]{2}), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteModule() throws Exception {
        DeleteModuleCommand command = (DeleteModuleCommand) parser.parseCommand(
                DeleteModuleCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteModuleCommand(INDEX_FIRST_PERSON), command);
    }

    //    Todo: deleteTutorialGroupTest
    //    @Test
    //    public void parseCommand_deleteTutorialGroup() throws Exception {
    //        DeleteTutorialGroupCommand command = (DeleteTutorialGroupCommand) parser.parseCommand(
    //                DeleteTutorialGroupCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
    //        assertEquals(new DeleteTutorialGroupCommand(INDEX_FIRST_PERSON), command);
    //    }

    @Test
    public void parseCommand_deleteStudent() throws Exception {
        DeleteStudentCommand command = (DeleteStudentCommand) parser.parseCommand(
                DeleteStudentCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteStudentCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deleteAttendance() throws Exception {
        DeleteAttendanceCommand command = (DeleteAttendanceCommand) parser.parseCommand(
                DeleteAttendanceCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()
                + " week/2");
        assertEquals(new DeleteAttendanceCommand(INDEX_FIRST_PERSON, new int[]{2}), command);
    }

    //    Example edit Test for reference, delete after
    //    @Test
    //    public void parseCommand_edit() throws Exception {
    //        Person person = new PersonBuilder().build();
    //        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
    //        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
    //                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
    //        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    //    }

    @Test
    public void parseCommand_editModule() throws Exception {
        EditModuleCommand command = (EditModuleCommand) parser.parseCommand(EditModuleCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + "m/CS21");
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        editModuleDescriptor.setModuleId(new ModuleId("CS21"));
        assertEquals(new EditModuleCommand(INDEX_FIRST_PERSON, editModuleDescriptor), command);
    }

    //    Todo: EditTutorialGroup test
    //    @Test
    //    public void parseCommand_editTutorialGroup() throws Exception {
    //        TutorialGroup tutorialGroup = new TutorialGroupBuilder().build();
    //        EditTutorialGroupCommand toTest = new EditTutorialGroupCommand(INDEX_FIRST_PERSON,
    //                new TutorialGroupId("T03"), tutorialGroup.getDayOfWeek(),
    //                tutorialGroup.getStartTime(), tutorialGroup.getEndTime());
    //        EditTutorialGroupCommand command =
    //                (EditTutorialGroupCommand) parser.parseCommand(EditTutorialGroupCommand.COMMAND_WORD
    //                        + " " + INDEX_FIRST_PERSON.getOneBased() + " " + "tg/T03 day/MON start/15:00 end/17:00");
    //        assertEquals(toTest, command);
    //    }

    //    Todo: EditStudent test
    @Test
    public void parseCommand_editStudent() throws Exception {
        Student student = new StudentBuilder().build();
        EditStudentCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditStudentCommand command = (EditStudentCommand) parser.parseCommand(
                EditStudentCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()
                        + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new EditStudentCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_editParticipation() throws Exception {
        EditParticipationCommand command = (EditParticipationCommand) parser.parseCommand(
                EditParticipationCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + "score/2");
        assertEquals(new EditParticipationCommand(INDEX_FIRST_PERSON, "2"), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findModule() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindModuleCommand command = (FindModuleCommand) parser.parseCommand(
                FindModuleCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindModuleCommand(new ModuleContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findTutorialGroup() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindTutorialGroupCommand command = (FindTutorialGroupCommand) parser.parseCommand(
                FindTutorialGroupCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindTutorialGroupCommand(new TutorialContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findStudentGroup() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindStudentCommand command = (FindStudentCommand) parser.parseCommand(
                FindStudentCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindStudentCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listModule() throws Exception {
        assertTrue(parser.parseCommand(ListModuleCommand.COMMAND_WORD) instanceof ListModuleCommand);
        assertTrue(parser.parseCommand(ListModuleCommand.COMMAND_WORD + " 3") instanceof ListModuleCommand);
    }

    @Test
    public void parseCommand_listTutorialGroup() throws Exception {
        assertTrue(parser.parseCommand(ListTutorialGroupCommand.COMMAND_WORD) instanceof ListTutorialGroupCommand);
        assertTrue(parser.parseCommand(
                ListTutorialGroupCommand.COMMAND_WORD + " 3") instanceof ListTutorialGroupCommand);
    }

    @Test
    public void parseCommand_listStudent() throws Exception {
        assertTrue(parser.parseCommand(ListStudentCommand.COMMAND_WORD) instanceof ListStudentCommand);
        assertTrue(parser.parseCommand(ListStudentCommand.COMMAND_WORD + " 3") instanceof ListStudentCommand);
    }

    @Test
    public void parseCommand_attendanceBelow() throws Exception {
        assertTrue(parser.parseCommand(
                AttendanceBelowCommand.COMMAND_WORD + " 3") instanceof AttendanceBelowCommand);
    }

    @Test
    public void attendanceBelow_emptyIndex_throwsParseException() {
        assertThrows(ParseException.class, "Invalid command format! \n"
                + AttendanceBelowCommand.MESSAGE_USAGE, () -> parser.parseCommand(
                AttendanceBelowCommand.COMMAND_WORD));
    }

    @Test
    public void parseCommand_participationBelow() throws Exception {
        assertTrue(parser.parseCommand(
                ParticipationBelowCommand.COMMAND_WORD + " 3") instanceof ParticipationBelowCommand);
    }

    @Test
    public void participationBelow_emptyIndex_throwsParseException() {
        assertThrows(ParseException.class, "Invalid command format! \n"
                + ParticipationBelowCommand.MESSAGE_USAGE, () -> parser.parseCommand(
                ParticipationBelowCommand.COMMAND_WORD));
    }

    @Test
    public void parseCommand_viewTutorialGroup() throws Exception {
        ViewTutorialGroupCommand command = (ViewTutorialGroupCommand) parser.parseCommand(
                ViewTutorialGroupCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new ViewTutorialGroupCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_viewStudent() throws Exception {
        ViewStudentCommand command = (ViewStudentCommand) parser.parseCommand(
                ViewStudentCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new ViewStudentCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_viewAttendance() throws Exception {
        ViewAttendanceCommand command = (ViewAttendanceCommand) parser.parseCommand(
                ViewAttendanceCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new ViewAttendanceCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_previousView() throws Exception {
        assertTrue(parser.parseCommand(PreviousViewCommand.COMMAND_WORD) instanceof PreviousViewCommand);
        assertTrue(parser.parseCommand(PreviousViewCommand.COMMAND_WORD + " 3") instanceof PreviousViewCommand);
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
