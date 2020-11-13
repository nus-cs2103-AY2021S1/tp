package seedu.jarvis.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.jarvis.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.jarvis.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.jarvis.logic.commands.Command;
import seedu.jarvis.logic.commands.view.ViewAllStudentsCommand;
import seedu.jarvis.logic.commands.view.ViewCommand;
import seedu.jarvis.logic.commands.view.ViewConsultationsCommand;
import seedu.jarvis.logic.commands.view.ViewMasteryChecksCommand;
import seedu.jarvis.logic.commands.view.ViewMissionDeadlineCommand;
import seedu.jarvis.logic.commands.view.ViewOneStudentCommand;
import seedu.jarvis.logic.commands.view.ViewPastConsultationsCommand;
import seedu.jarvis.logic.commands.view.ViewPastMasteryChecksCommand;
import seedu.jarvis.logic.commands.view.ViewQuestDeadlineCommand;
import seedu.jarvis.logic.commands.view.ViewTaskListCommand;
import seedu.jarvis.logic.commands.view.ViewUngradedMissionCommand;
import seedu.jarvis.logic.commands.view.ViewUngradedQuestCommand;
import seedu.jarvis.logic.commands.view.ViewUpcomingConsultationsCommand;
import seedu.jarvis.logic.commands.view.ViewUpcomingMasteryChecksCommand;
import seedu.jarvis.logic.parser.exceptions.ParseException;
import seedu.jarvis.model.student.Name;
import seedu.jarvis.testutil.TypicalStudents;

public class ViewCommandParserTest {

    /**
     * Test for invalid empty string input
     */
    @Test
    public void parse_emptyStringInput_throwsParseException() {
        ViewCommandParser parser = new ViewCommandParser();
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidCommandFormat_throwsParseException() {
        ViewCommandParser parser = new ViewCommandParser();
        assertParseFailure(parser, "-2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_viewMissionDeadline_success() throws ParseException {
        ViewCommandParser parser = new ViewCommandParser();
        Command command = parser.parse("-m");
        assertTrue(command instanceof ViewMissionDeadlineCommand);
    }

    @Test
    public void parse_viewAllStudent_success() throws ParseException {
        ViewCommandParser parser = new ViewCommandParser();
        Command command = parser.parse("-s");
        assertTrue(command instanceof ViewAllStudentsCommand);
    }

    @Test
    public void parse_viewOneStudent_success() throws ParseException {
        ViewCommandParser parser = new ViewCommandParser();
        Name studentName = TypicalStudents.ALICE.getName();
        Command command = parser.parse("-s " + studentName.fullName);
        assertTrue(command instanceof ViewOneStudentCommand);
    }

    @Test
    public void parse_viewQuestDeadline_success() throws ParseException {
        ViewCommandParser parser = new ViewCommandParser();
        Command command = parser.parse("-q");
        assertTrue(command instanceof ViewQuestDeadlineCommand);
    }

    @Test
    public void parse_viewUngradedMissionDeadline_success() throws ParseException {
        ViewCommandParser parser = new ViewCommandParser();
        Command command = parser.parse("-um");
        assertTrue(command instanceof ViewUngradedMissionCommand);
    }

    @Test
    public void parse_viewUngradedQuestDeadline_success() throws ParseException {
        ViewCommandParser parser = new ViewCommandParser();
        Command command = parser.parse("-uq");
        assertTrue(command instanceof ViewUngradedQuestCommand);
    }

    @Test
    public void parse_viewTaskList_success() throws ParseException {
        ViewCommandParser parser = new ViewCommandParser();
        Command command = parser.parse("-t");
        assertTrue(command instanceof ViewTaskListCommand);
    }

    @Test
    public void parse_viewTodoList_success() throws ParseException {
        ViewCommandParser parser = new ViewCommandParser();
        Command command = parser.parse("-tt");
        assertTrue(command instanceof ViewTaskListCommand);
    }

    @Test
    public void parse_viewEventList_success() throws ParseException {
        ViewCommandParser parser = new ViewCommandParser();
        Command command = parser.parse("-te");
        assertTrue(command instanceof ViewTaskListCommand);
    }

    @Test
    public void parse_viewDeadlineList_success() throws ParseException {
        ViewCommandParser parser = new ViewCommandParser();
        Command command = parser.parse("-td");
        assertTrue(command instanceof ViewTaskListCommand);
    }

    @Test
    public void parse_viewConsultationList_success() throws ParseException {
        ViewCommandParser parser = new ViewCommandParser();
        Command command = parser.parse("-c");
        assertTrue(command instanceof ViewConsultationsCommand);
    }

    @Test
    public void parse_viewUpcomingConsultation_success() throws ParseException {
        ViewCommandParser parser = new ViewCommandParser();
        Command command = parser.parse("-cu");
        assertTrue(command instanceof ViewUpcomingConsultationsCommand);
    }

    @Test
    public void parse_viewPastConsultation_success() throws ParseException {
        ViewCommandParser parser = new ViewCommandParser();
        Command command = parser.parse("-cp");
        assertTrue(command instanceof ViewPastConsultationsCommand);
    }

    @Test
    public void parse_viewMasteryChecks_success() throws ParseException {
        ViewCommandParser parser = new ViewCommandParser();
        Command command = parser.parse("-mc");
        assertTrue(command instanceof ViewMasteryChecksCommand);
    }

    @Test
    public void parse_viewUpcomingMasteryChecks_success() throws ParseException {
        ViewCommandParser parser = new ViewCommandParser();
        Command command = parser.parse("-mcu");
        assertTrue(command instanceof ViewUpcomingMasteryChecksCommand);
    }

    @Test
    public void parse_viewPastMasteryChecks_success() throws ParseException {
        ViewCommandParser parser = new ViewCommandParser();
        Command command = parser.parse("-mcp");
        assertTrue(command instanceof ViewPastMasteryChecksCommand);
    }

    @Test
    public void parse_viewInvalidCommand_failure() {
        ViewCommandParser parser = new ViewCommandParser();
        assertThrows(ParseException.class, () -> parser.parse("-randomTag"));

        try {
            parser.parse("-randomTag");
        } catch (ParseException pe) {
            assertEquals(pe.getMessage(), String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }
    }
}
