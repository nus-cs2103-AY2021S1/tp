package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExercises.SPRINTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.address.testutil.TypicalLessons.MA1521;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddHeightCommand;
import seedu.address.logic.commands.AddWeightCommand;
import seedu.address.logic.commands.CalorieAddCommand;
import seedu.address.logic.commands.CalorieMinusCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.exercise.ExerciseAddCommand;
import seedu.address.logic.commands.exercise.ExerciseDeleteCommand;
import seedu.address.logic.commands.exercise.ExerciseEditCommand;
import seedu.address.logic.commands.exercise.ExerciseEditCommand.EditExerciseDescriptor;
import seedu.address.logic.commands.exercise.ExerciseFindCommand;
import seedu.address.logic.commands.exercise.ExerciseListCommand;
import seedu.address.logic.commands.lessons.LessonAddCommand;
import seedu.address.logic.commands.lessons.LessonDeleteCommand;
import seedu.address.logic.commands.lessons.LessonEditCommand;
import seedu.address.logic.commands.lessons.LessonEditCommand.EditLessonDescriptor;
import seedu.address.logic.commands.lessons.LessonFindCommand;
import seedu.address.logic.commands.lessons.LessonListCommand;
import seedu.address.logic.commands.routines.RoutineAddExerciseCommand;
import seedu.address.logic.commands.routines.RoutineCreateCommand;
import seedu.address.logic.commands.routines.RoutineDeleteCommand;
import seedu.address.logic.commands.routines.RoutineDeleteExerciseCommand;
import seedu.address.logic.commands.routines.RoutineFindCommand;
import seedu.address.logic.commands.routines.RoutineListCommand;
import seedu.address.logic.commands.routines.RoutineViewCommand;
import seedu.address.logic.commands.timetable.TimetableAddLessonCommand;
import seedu.address.logic.commands.timetable.TimetableAddRoutineCommand;
import seedu.address.logic.commands.timetable.TimetableDeleteSlotCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.body.Height;
import seedu.address.model.body.Weight;
import seedu.address.model.calorie.Calorie;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.ExerciseNameContainsKeywordsPredicate;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonNameContainsKeywordsPredicate;
import seedu.address.model.routine.Routine;
import seedu.address.model.routine.RoutineNameContainsKeywordsPredicate;
import seedu.address.model.timetable.Slot;
import seedu.address.model.util.Name;
import seedu.address.testutil.EditExerciseDescriptorBuilder;
import seedu.address.testutil.EditLessonDescriptorBuilder;
import seedu.address.testutil.ExerciseBuilder;
import seedu.address.testutil.ExerciseUtil;
import seedu.address.testutil.LessonBuilder;
import seedu.address.testutil.LessonUtil;
import seedu.address.testutil.RoutineUtil;
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
    public void parseCommand_addHeight() throws Exception {
        Height height = new Height(170);
        String userInput = AddHeightCommand.COMMAND_WORD + " " + PREFIX_HEIGHT + height.getHeight();
        AddHeightCommand actualCommand = (AddHeightCommand) parser.parseCommand(userInput);
        AddHeightCommand expectedCommand = new AddHeightCommand(height);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_addWeight() throws Exception {
        Weight weight = new Weight(70);
        String userInput = AddWeightCommand.COMMAND_WORD + " " + PREFIX_WEIGHT + weight.getWeight();
        AddWeightCommand actualCommand = (AddWeightCommand) parser.parseCommand(userInput);
        AddWeightCommand expectedCommand = new AddWeightCommand(weight);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_calorieAdd() throws Exception {
        Calorie calorie = new Calorie(1000);
        String userInput = CalorieAddCommand.COMMAND_WORD + " " + PREFIX_CALORIE + calorie.getCalorie();
        CalorieAddCommand actualCommand = (CalorieAddCommand) parser.parseCommand(userInput);
        CalorieAddCommand expectedCommand = new CalorieAddCommand(calorie);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_calorieMinus() throws Exception {
        Calorie calorie = new Calorie(1000);
        String userInput = CalorieMinusCommand.COMMAND_WORD + " " + PREFIX_CALORIE + calorie.getCalorie();
        CalorieMinusCommand actualCommand = (CalorieMinusCommand) parser.parseCommand(userInput);
        CalorieMinusCommand expectedCommand = new CalorieMinusCommand(calorie);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_exerciseAdd() throws Exception {
        Exercise exercise = new ExerciseBuilder().build();
        ExerciseAddCommand actualCommand =
                (ExerciseAddCommand) parser.parseCommand(ExerciseUtil.getExerciseAddCommand(exercise));
        ExerciseAddCommand expectedCommand = new ExerciseAddCommand(exercise);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_exerciseDelete() throws Exception {
        ExerciseDeleteCommand actualCommand = (ExerciseDeleteCommand) parser.parseCommand(
                ExerciseUtil.getExerciseDeleteCommand(INDEX_FIRST_EXERCISE));
        ExerciseDeleteCommand expectedCommand = new ExerciseDeleteCommand(INDEX_FIRST_EXERCISE);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_exerciseEdit() throws Exception {
        Exercise exercise = new ExerciseBuilder().build();

        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder(exercise).build();
        ExerciseEditCommand actualCommand = (ExerciseEditCommand) parser.parseCommand(
                ExerciseUtil.getExerciseEditCommand(INDEX_FIRST_EXERCISE, descriptor));
        ExerciseEditCommand expectedCommand = new ExerciseEditCommand(INDEX_FIRST_EXERCISE, descriptor);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_exerciseFind() throws Exception {
        String keyword = "keyword";
        ExerciseFindCommand actualCommand =
                (ExerciseFindCommand) parser.parseCommand(ExerciseUtil.getExerciseFindCommand(keyword));
        ExerciseNameContainsKeywordsPredicate predicate =
                new ExerciseNameContainsKeywordsPredicate(Collections.singletonList(keyword));
        ExerciseFindCommand expectedCommand = new ExerciseFindCommand(predicate);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_exerciseList() throws Exception {
        String userInput = ExerciseListCommand.COMMAND_WORD;
        ExerciseListCommand actualCommand = (ExerciseListCommand) parser.parseCommand(userInput);
        ExerciseListCommand expectedCommand = new ExerciseListCommand();
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_routineCreate() throws Exception {
        Routine legDay = new Routine(new Name("Leg Day"));
        String userInput = RoutineUtil.getRoutineCreateCommand(legDay);
        RoutineCreateCommand actualCommand = (RoutineCreateCommand) parser.parseCommand(userInput);
        RoutineCreateCommand expectedCommand = new RoutineCreateCommand(legDay);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_routineDelete() throws Exception {
        String userInput = RoutineUtil.getRoutineDeleteCommand(INDEX_FIRST);
        RoutineDeleteCommand actualCommand = (RoutineDeleteCommand) parser.parseCommand(userInput);
        RoutineDeleteCommand expectedCommand = new RoutineDeleteCommand(INDEX_FIRST);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_routineFind() throws Exception {
        String keyword = "keyword";
        String userInput = RoutineUtil.getRoutineFindCommand(keyword);
        RoutineFindCommand actualCommand = (RoutineFindCommand) parser.parseCommand(userInput);
        RoutineNameContainsKeywordsPredicate predicate =
                new RoutineNameContainsKeywordsPredicate(Collections.singletonList(keyword));
        RoutineFindCommand expectedCommand = new RoutineFindCommand(predicate);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_routineList() throws Exception {
        String userInput = RoutineListCommand.COMMAND_WORD;
        RoutineListCommand actualCommand = (RoutineListCommand) parser.parseCommand(userInput);
        RoutineListCommand expectedCommand = new RoutineListCommand();
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_routineAddExercise() throws Exception {
        Routine legDay = new Routine(new Name("Leg Day"));
        String userInput = RoutineUtil.getRoutineAddExerciseCommand(legDay, SPRINTS);
        RoutineAddExerciseCommand actualCommand = (RoutineAddExerciseCommand) parser.parseCommand(userInput);
        RoutineAddExerciseCommand expectedCommand = new RoutineAddExerciseCommand(legDay, SPRINTS);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_routineDeleteExercise() throws Exception {
        Routine legDay = new Routine(new Name("Leg Day"));
        String userInput = RoutineUtil.getRoutineDeleteExerciseCommand(legDay, SPRINTS);
        RoutineDeleteExerciseCommand actualCommand = (RoutineDeleteExerciseCommand) parser.parseCommand(userInput);
        RoutineDeleteExerciseCommand expectedCommand = new RoutineDeleteExerciseCommand(legDay, SPRINTS);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_routineView() throws Exception {
        String userInput = RoutineUtil.getRoutineViewCommand(INDEX_FIRST);
        RoutineViewCommand actualCommand = (RoutineViewCommand) parser.parseCommand(userInput);
        RoutineViewCommand expectedCommand = new RoutineViewCommand(INDEX_FIRST);
        assertEquals(expectedCommand, actualCommand);
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
    public void parseCommand_lessonFind() throws Exception {
        String keyword = "keyword";
        String userInput = LessonFindCommand.COMMAND_WORD + " " + keyword;
        LessonFindCommand actualCommand = (LessonFindCommand) parser.parseCommand(userInput);
        LessonNameContainsKeywordsPredicate predicate =
                new LessonNameContainsKeywordsPredicate(Collections.singletonList(keyword));
        LessonFindCommand expectedCommand = new LessonFindCommand(predicate);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_lessonList() throws Exception {
        String userInput = LessonListCommand.COMMAND_WORD;
        LessonListCommand actualCommand = (LessonListCommand) parser.parseCommand(userInput);
        LessonListCommand expectedCommand = new LessonListCommand();
        assertEquals(expectedCommand, actualCommand);
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
