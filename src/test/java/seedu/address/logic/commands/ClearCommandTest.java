package seedu.address.logic.commands;


import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalExercise.getTypicalExerciseBook;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import seedu.address.model.ExerciseBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exercise.TemplateList;

public class ClearCommandTest {
    @Test
    public void execute_emptyExerciseBook_success() {
        Model model = new ModelManager(new ExerciseBookNoWritingStubs());
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyExerciseBook_success() {
        ExerciseBookNoWritingStubs exerciseBook = new ExerciseBookNoWritingStubs();
        exerciseBook.resetData(getTypicalExerciseBook());

        Model model = new ModelManager(exerciseBook, null,  new UserPrefs());

        //Since the methods mutable the exercise book parsed into it
        ExerciseBookNoWritingStubs exerciseBook2 = new ExerciseBookNoWritingStubs();
        exerciseBook2.resetData(getTypicalExerciseBook());

        Model expectedModel = new ModelManager(exerciseBook2, null, new UserPrefs());
        expectedModel.setExerciseBook(new ExerciseBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    private class ExerciseBookNoWritingStubs extends ExerciseBook {
        @Override
        public void resetAllData() throws IOException {
            super.exercises.resetAll();
            TemplateList.reset();
        }
    }
}



