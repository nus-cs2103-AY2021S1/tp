package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exercise.ExerciseEditCommand;
import seedu.address.logic.commands.lessons.LessonEditCommand;
import seedu.address.model.FitNus;
import seedu.address.model.Model;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.ExerciseNameContainsKeywordsPredicate;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonNameContainsKeywordsPredicate;
import seedu.address.testutil.EditExerciseDescriptorBuilder;
import seedu.address.testutil.EditLessonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_LESSON_NAME_CS2030 = "CS2030";
    public static final String VALID_LESSON_NAME_CS2106 = "CS2106";
    public static final String VALID_LESSON_TAG_EASY = "easy";
    public static final String VALID_LESSON_TAG_LECTURE = "lecture";

    public static final String VALID_EXERCISE_NAME_BENCH = "Bench press";
    public static final String VALID_EXERCISE_NAME_SQUATS = "Squats";
    public static final String VALID_EXERCISE_TAG_CHEST = "chest";
    public static final String VALID_EXERCISE_TAG_LEGS = "Legs";
    public static final String VALID_EXERCISE_TAG_ARMS = "Arms";

    public static final String LESSON_NAME_DESC_CS2030 = " " + PREFIX_LESSON + VALID_LESSON_NAME_CS2030;
    public static final String LESSON_NAME_DESC_CS2106 = " " + PREFIX_LESSON + VALID_LESSON_NAME_CS2106;
    public static final String LESSON_TAG_DESC_EASY = " " + PREFIX_TAG + VALID_LESSON_TAG_EASY;
    public static final String LESSON_TAG_DESC_LECTURE = " " + PREFIX_TAG + VALID_LESSON_TAG_LECTURE;

    public static final String EXERCISE_NAME_DESC_BENCH = " " + PREFIX_EXERCISE + VALID_EXERCISE_NAME_BENCH;
    public static final String EXERCISE_NAME_DESC_SQUATS = " " + PREFIX_EXERCISE + VALID_EXERCISE_NAME_SQUATS;
    public static final String EXERCISE_TAG_DESC_CHEST = " " + PREFIX_TAG + VALID_EXERCISE_TAG_CHEST;
    public static final String EXERCISE_TAG_DESC_LEGS = " " + PREFIX_TAG + VALID_EXERCISE_TAG_LEGS;
    public static final String EXERCISE_TAG_DESC_ARMS = " " + PREFIX_TAG + VALID_EXERCISE_TAG_ARMS;

    public static final String INVALID_LESSON_NAME_DESC = " " + PREFIX_LESSON + "CS3230&"; // '&' not allowed in lessons
    public static final String INVALID_LESSON_TAG_DESC = " " + PREFIX_TAG + "hard*"; // '*' not allowed in tags

    public static final String INVALID_EXERCISE_NAME_DESC = " "
            + PREFIX_EXERCISE + "Bench&"; // '&' not allowed in exercises
    public static final String INVALID_EXERCISE_TAG_DESC = " " + PREFIX_TAG + "Legs*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final LessonEditCommand.EditLessonDescriptor DESC_CS2030;
    public static final LessonEditCommand.EditLessonDescriptor DESC_CS2106;

    public static final ExerciseEditCommand.EditExerciseDescriptor DESC_BENCH;
    public static final ExerciseEditCommand.EditExerciseDescriptor DESC_SQUATS;

    static {
        DESC_CS2030 = new EditLessonDescriptorBuilder().withName(VALID_LESSON_NAME_CS2030)
                .withTags(VALID_LESSON_TAG_LECTURE).build();
        DESC_CS2106 = new EditLessonDescriptorBuilder().withName(VALID_LESSON_NAME_CS2106)
                .withTags(VALID_LESSON_TAG_EASY, VALID_LESSON_TAG_LECTURE).build();
        DESC_BENCH = new EditExerciseDescriptorBuilder().withName(VALID_EXERCISE_NAME_BENCH)
                .withTags(VALID_EXERCISE_TAG_CHEST).build();
        DESC_SQUATS = new EditExerciseDescriptorBuilder().withName(VALID_EXERCISE_NAME_SQUATS)
                .withTags(VALID_EXERCISE_TAG_LEGS).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the fitNUS, filtered lesson list and selected lesson in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        FitNus expectedFitNus = new FitNus(actualModel.getFitNus());
        List<Lesson> expectedFilteredLessonList = new ArrayList<>(actualModel.getFilteredLessonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedFitNus, actualModel.getFitNus());
        assertEquals(expectedFilteredLessonList, actualModel.getFilteredLessonList());
    }

    /**
     * Updates {@code model}'s filtered lesson list to show only the lesson at the given {@code targetIndex} in the
     * {@code model}'s fitNUS.
     */
    public static void showLessonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredLessonList().size());

        Lesson lesson = model.getFilteredLessonList().get(targetIndex.getZeroBased());
        final String[] splitName = lesson.getName().fullName.split("\\s+");
        model.updateFilteredLessonList(new LessonNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredLessonList().size());
    }

    /**
     * Updates {@code model}'s filtered exercise list to show only the exercise at the given {@code targetIndex} in the
     * {@code model}'s fitNUS.
     */
    public static void showExerciseAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredExerciseList().size());

        Exercise exercise = model.getFilteredExerciseList().get(targetIndex.getZeroBased());
        final String[] splitName = exercise.getName().fullName.split("\\s+");
        model.updateFilteredExerciseList(new ExerciseNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredExerciseList().size());
    }
}
