package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExercise.SIT_UP;

import java.util.Collections;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.exercise.Exercise;
import seedu.address.testutil.ExerciseBuilder;
import seedu.address.testutil.TypicalExercise;

public class ExerciseBookTest {
    private static final Logger logger = LogsCenter.getLogger(ExerciseModelManager.class);

    private final ExerciseBook exerciseBook = new ExerciseBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), exerciseBook.getExerciseList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        TypicalExercise.getTypicalExerciseBook();
        ExerciseBook newData = TypicalExercise.getTypicalExerciseBook();
        exerciseBook.resetData(newData);
        assertEquals(newData, exerciseBook);
    }


    @Test
    public void hasPerson_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseBook.hasExercise(null));
    }

    @Test
    public void hasExercise_exerciseNotInExerciseBook_returnsFalse() {
        assertFalse(exerciseBook.hasExercise(SIT_UP));
    }

    @Test
    public void hasExercise_exerciseInExerciseBook_returnsTrue() {
        exerciseBook.addExercise(SIT_UP);
        assertTrue(exerciseBook.hasExercise(SIT_UP));
    }
    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        exerciseBook.addExercise(SIT_UP);
        Exercise editedSitUp = new ExerciseBuilder(SIT_UP).withMuscleTags("Chest")
                .build();
        assertTrue(exerciseBook.hasExercise(editedSitUp));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> exerciseBook.getExerciseList().remove(0));
    }
}
