package seedu.fma.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_C;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_E;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_R;
import static seedu.fma.model.util.SampleDataUtil.getSampleExercises;
import static seedu.fma.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import seedu.fma.commons.core.index.Index;
import seedu.fma.logic.commands.exceptions.CommandException;
import seedu.fma.model.LogBook;
import seedu.fma.model.Model;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.log.Comment;
import seedu.fma.model.log.Log;
import seedu.fma.model.log.Rep;
import seedu.fma.model.util.Calories;
import seedu.fma.model.util.Name;
import seedu.fma.model.util.NameContainsKeywordsPredicate;
import seedu.fma.testutil.ExerciseBuilder;
import seedu.fma.testutil.LogBuilder;

/**
 * Contains helper methods for testing commands.
 */

public class CommandTestUtil {
    public static final String EXERCISE_A = "Sit ups";
    public static final String EXERCISE_B = "Jumping jacks";

    public static final String VALID_REP_A_STR = "5";
    public static final String VALID_REP_B_STR = "10";

    public static final String VALID_COMMENT_A_STR = "This exercise is tough";
    public static final String VALID_COMMENT_B_STR = "Easy";

    public static final Rep VALID_REP_A = new Rep(VALID_REP_A_STR);
    public static final Rep VALID_REP_B = new Rep(VALID_REP_B_STR);

    public static final Name VALID_EXERCISE_NAME_A = new Name(EXERCISE_A);
    public static final Name VALID_EXERCISE_NAME_B = new Name(EXERCISE_B);

    public static final String VALID_CALORIES_A_STR = new ExerciseBuilder(getSampleExercises()[1])
            .build().getCaloriesPerRep().toString();

    public static final String VALID_CALORIES_B_STR = new ExerciseBuilder(getSampleExercises()[3])
            .build().getCaloriesPerRep().toString();

    public static final Calories VALID_CALORIES_A = new Calories(Integer.parseInt(VALID_CALORIES_A_STR));
    public static final Calories VALID_CALORIES_B = new Calories(Integer.parseInt(VALID_CALORIES_B_STR));

    public static final Exercise VALID_EXERCISE_A = new ExerciseBuilder(getSampleExercises()[1]).build();
    public static final Exercise VALID_EXERCISE_B = new ExerciseBuilder(getSampleExercises()[3]).build();

    public static final Comment VALID_COMMENT_A = new Comment(VALID_COMMENT_A_STR);
    public static final Comment VALID_COMMENT_B = new Comment(VALID_COMMENT_B_STR);

    public static final int VALID_YEAR_A = 2021;
    public static final int VALID_MONTH_A = 2; // February
    public static final int VALID_DAY_A = 2;
    public static final int VALID_HOUR_A = 2;
    public static final int VALID_MINUTE_A = 2;
    public static final LocalDateTime VALID_DATE_TIME_A = LocalDateTime.of(
            VALID_YEAR_A, VALID_MONTH_A, VALID_DAY_A, VALID_HOUR_A, VALID_MINUTE_A
    );
    public static final int VALID_YEAR_B = 2020;
    public static final int VALID_MONTH_B = 3; // March
    public static final int VALID_DAY_B = 4;
    public static final int VALID_HOUR_B = 6;
    public static final int VALID_MINUTE_B = 7;
    public static final LocalDateTime VALID_DATE_TIME_B = LocalDateTime.of(
            VALID_YEAR_B, VALID_MONTH_B, VALID_DAY_B, VALID_HOUR_B, VALID_MINUTE_B
    );

    public static final String EXERCISE_DESC_A = " " + PREFIX_E + EXERCISE_A;
    public static final String EXERCISE_DESC_B = " " + PREFIX_E + EXERCISE_B;
    public static final String REP_DESC_A = " " + PREFIX_R + VALID_REP_A;
    public static final String REP_DESC_B = " " + PREFIX_R + VALID_REP_B;
    public static final String COMMENT_DESC_A = " " + PREFIX_C + VALID_COMMENT_A_STR;
    public static final String COMMENT_DESC_B = " " + PREFIX_C + VALID_COMMENT_B_STR;
    public static final String CALORIES_DESC_A = " " + PREFIX_C + "90";

    // '?' not allowed in exercise names
    public static final String INVALID_EXERCISE_DESC = " " + PREFIX_E + "Sit ups?";
    public static final String INVALID_REP_DESC = " " + PREFIX_R + "911a"; // 'a' not allowed in rep
    public static final String INVALID_COMMENT_DESC = " " + PREFIX_C + "    "; // comment cannot be blank

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final Log VALID_LOG_A;
    public static final Log VALID_LOG_B;

    public static final EditCommand.EditLogDescriptor EDIT_LOG_DESCRIPTOR_A;
    public static final EditCommand.EditLogDescriptor EDIT_LOG_DESCRIPTOR_B;

    public static final EditExCommand.EditExDescriptor EDIT_EX_DESCRIPTOR_A;
    public static final EditExCommand.EditExDescriptor EDIT_EX_DESCRIPTOR_B;

    static {
        VALID_LOG_A = new LogBuilder().withExercise(VALID_EXERCISE_A)
                .withReps(VALID_REP_A_STR).withComment(VALID_COMMENT_A_STR).build();
        VALID_LOG_B = new LogBuilder().withExercise(VALID_EXERCISE_B)
                .withReps(VALID_REP_B_STR).withComment(VALID_COMMENT_B_STR).build();

        EDIT_LOG_DESCRIPTOR_A = new EditCommand.EditLogDescriptor();
        EDIT_LOG_DESCRIPTOR_A.setExercise(VALID_EXERCISE_A);

        EDIT_LOG_DESCRIPTOR_B = new EditCommand.EditLogDescriptor();
        EDIT_LOG_DESCRIPTOR_B.setRep(new Rep(VALID_REP_B_STR));

        EDIT_EX_DESCRIPTOR_A = new EditExCommand.EditExDescriptor();
        EDIT_EX_DESCRIPTOR_A.setExerciseName(VALID_EXERCISE_NAME_A);

        EDIT_EX_DESCRIPTOR_B = new EditExCommand.EditExDescriptor();
        EDIT_EX_DESCRIPTOR_B.setCaloriesPerRep(VALID_CALORIES_B);
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
     * - the address book, filtered log list and selected log in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        LogBook expectedLogBook = new LogBook(actualModel.getLogBook());
        List<Log> expectedFilteredList = new ArrayList<>(actualModel.getFilteredLogList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedLogBook, actualModel.getLogBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredLogList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the log at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showLogAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredLogList().size());

        Log log = model.getFilteredLogList().get(targetIndex.getZeroBased());
        Predicate<Log> firstPredicate = new NameContainsKeywordsPredicate(Collections.singletonList(log.toString()));
        model.updateFilteredLogList(firstPredicate);
        assertEquals(1, model.getFilteredLogList().size());
    }
}

