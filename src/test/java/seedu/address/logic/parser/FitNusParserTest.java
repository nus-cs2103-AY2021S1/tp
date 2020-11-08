package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.address.testutil.TypicalLessons.MA1521;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.lessons.LessonAddCommand;
import seedu.address.logic.commands.lessons.LessonDeleteCommand;
import seedu.address.logic.commands.lessons.LessonEditCommand;
import seedu.address.logic.commands.lessons.LessonEditCommand.EditLessonDescriptor;
import seedu.address.logic.commands.timetable.TimetableAddLessonCommand;
import seedu.address.logic.commands.timetable.TimetableAddRoutineCommand;
import seedu.address.logic.commands.timetable.TimetableDeleteSlotCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.routine.Routine;
import seedu.address.model.timetable.Slot;
import seedu.address.model.util.Name;
import seedu.address.testutil.EditLessonDescriptorBuilder;
import seedu.address.testutil.LessonBuilder;
import seedu.address.testutil.LessonUtil;
import seedu.address.testutil.SlotBuilder;
import seedu.address.testutil.SlotUtil;

public class FitNusParserTest {

    private final FitNusParser parser = new FitNusParser();

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
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
    public void parseCommand_lessonAdd() throws Exception {
        Lesson lesson = new LessonBuilder().build();
        LessonAddCommand command = (LessonAddCommand) parser.parseCommand(LessonUtil.getLessonAddCommand(lesson));
        assertEquals(new LessonAddCommand(lesson), command);
    }

    @Test
    public void parseCommand_lessonDelete() throws Exception {
        LessonDeleteCommand command = (LessonDeleteCommand) parser.parseCommand(
                LessonDeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_LESSON.getOneBased());
        assertEquals(new LessonDeleteCommand(INDEX_FIRST_LESSON), command);
    }

    @Test
    public void parseCommand_lessonEdit() throws Exception {
        Lesson lesson = new LessonBuilder().build();
        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder(lesson).build();
        LessonEditCommand command = (LessonEditCommand) parser.parseCommand(LessonEditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_LESSON.getOneBased() + " " + LessonUtil.getEditLessonDescriptorDetails(descriptor));
        assertEquals(new LessonEditCommand(INDEX_FIRST_LESSON, descriptor), command);
    }

    @Test
    public void parseCommand_timetableAddRoutine() throws Exception {
        Routine legDay = new Routine(new Name("Leg Day"));
        Slot slot = new SlotBuilder().withActivity(legDay).build();
        TimetableAddRoutineCommand expectedCommand = new TimetableAddRoutineCommand(
                legDay, slot.getDay(), slot.getDuration());
        TimetableAddRoutineCommand actualCommand =
                (TimetableAddRoutineCommand) parser.parseCommand(SlotUtil.getTimetableAddRoutineCommand(slot));
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_timetableAddLesson() throws Exception {
        Slot slot = new SlotBuilder().withActivity(MA1521).build();
        TimetableAddLessonCommand expectedCommand = new TimetableAddLessonCommand(
                MA1521, slot.getDay(), slot.getDuration());
        TimetableAddLessonCommand actualCommand =
                (TimetableAddLessonCommand) parser.parseCommand(SlotUtil.getTimetableAddLessonCommand(slot));
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_timetableDeleteSlot() throws Exception {
        Slot slot = new SlotBuilder().build();
        TimetableDeleteSlotCommand expectedCommand = new TimetableDeleteSlotCommand(slot);
        TimetableDeleteSlotCommand actualCommand =
                (TimetableDeleteSlotCommand) parser.parseCommand(SlotUtil.getTimetablDeleteSlotCommand(slot));
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseCommand("unknownCommand"));
    }
}
