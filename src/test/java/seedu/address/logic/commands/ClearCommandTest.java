package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalExercise.getTypicalExerciseBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.ExerciseBook;
import seedu.address.model.ExerciseModel;
import seedu.address.model.ExerciseModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exercise.TemplateList;

public class ClearCommandTest {
    @Test
    public void execute_emptyExerciseBook_success() {
        ExerciseModel model = new ExerciseModelManager(new ExerciseBookNoWritingStubs());
        ExerciseModel expectedModel = new ExerciseModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyExerciseBook_success() {
        ExerciseBookNoWritingStubs exerciseBook = new ExerciseBookNoWritingStubs();
        exerciseBook.resetData(getTypicalExerciseBook());

        ExerciseModel model = new ExerciseModelManager(exerciseBook, null,  new UserPrefs());

        //Since the methods mutable the exercise book parsed into it
        ExerciseBookNoWritingStubs exerciseBook2 = new ExerciseBookNoWritingStubs();
        exerciseBook2.resetData(getTypicalExerciseBook());

        ExerciseModel expectedModel = new ExerciseModelManager(exerciseBook2, null, new UserPrefs());
        expectedModel.setExerciseBook(new ExerciseBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}

class ExerciseBookNoWritingStubs extends ExerciseBook {
    @Override
    public void resetAllData() {
        super.exercises.resetAll();
        TemplateList.reset();
    }
}

