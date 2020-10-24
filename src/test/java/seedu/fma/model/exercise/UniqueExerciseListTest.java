package seedu.fma.model.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.testutil.Assert.assertThrows;
import static seedu.fma.testutil.TypicalExercises.EXERCISE_A;
import static seedu.fma.testutil.TypicalExercises.EXERCISE_B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.fma.model.exercise.exceptions.DuplicateExerciseException;
import seedu.fma.model.exercise.exceptions.ExerciseNotFoundException;
import seedu.fma.testutil.ExerciseBuilder;

public class UniqueExerciseListTest {

    private final UniqueExerciseList uniqueExerciseList = new UniqueExerciseList();

    @Test
    public void contains_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.contains(null));
    }

    @Test
    public void contains_exerciseNotInList_returnsFalse() {
        assertFalse(uniqueExerciseList.contains(EXERCISE_A));
    }

    @Test
    public void contains_exerciseInList_returnsTrue() {
        uniqueExerciseList.add(EXERCISE_A);
        assertTrue(uniqueExerciseList.contains(EXERCISE_A));
    }

    @Test
    public void contains_exerciseWithSameIdentityFieldsInList_returnsTrue() {
        uniqueExerciseList.add(EXERCISE_A);
        Exercise editedExerciseA = new ExerciseBuilder(EXERCISE_A).build();
        assertTrue(uniqueExerciseList.contains(editedExerciseA));
    }

    @Test
    public void add_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.add(null));
    }

    @Test
    public void add_duplicateExercise_throwsDuplicateExerciseException() {
        uniqueExerciseList.add(EXERCISE_A);
        assertThrows(DuplicateExerciseException.class, () -> uniqueExerciseList.add(EXERCISE_A));
    }

    @Test
    public void setExercise_nullTargetExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.setExercise(null, EXERCISE_A));
    }

    @Test
    public void setExercise_nullEditedExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.setExercise(EXERCISE_A, null));
    }

    @Test
    public void setExercise_targetExerciseNotInList_throwsExerciseNotFoundException() {
        assertThrows(ExerciseNotFoundException.class, () -> uniqueExerciseList.setExercise(EXERCISE_A, EXERCISE_A));
    }

    @Test
    public void setExercise_editedExerciseIsSameExercise_success() {
        uniqueExerciseList.add(EXERCISE_A);
        uniqueExerciseList.setExercise(EXERCISE_A, EXERCISE_A);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        expectedUniqueExerciseList.add(EXERCISE_A);
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    @Test
    public void setExercise_editedExerciseHasSameIdentity_success() {
        uniqueExerciseList.add(EXERCISE_A);
        Exercise editedExerciseA = new ExerciseBuilder(EXERCISE_A)
                .build();
        uniqueExerciseList.setExercise(EXERCISE_A, editedExerciseA);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        expectedUniqueExerciseList.add(editedExerciseA);
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    @Test
    public void setExercise_editedExerciseHasDifferentIdentity_success() {
        uniqueExerciseList.add(EXERCISE_A);
        uniqueExerciseList.setExercise(EXERCISE_A, EXERCISE_B);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        expectedUniqueExerciseList.add(EXERCISE_B);
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    @Test
    public void setExercise_editedExerciseHasNonUniqueIdentity_throwsDuplicateExerciseException() {
        uniqueExerciseList.add(EXERCISE_A);
        uniqueExerciseList.add(EXERCISE_B);
        assertThrows(DuplicateExerciseException.class, () -> uniqueExerciseList.setExercise(EXERCISE_A, EXERCISE_B));
    }

    @Test
    public void remove_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.remove(null));
    }

    @Test
    public void remove_exerciseDoesNotExist_throwsExerciseNotFoundException() {
        assertThrows(ExerciseNotFoundException.class, () -> uniqueExerciseList.remove(EXERCISE_A));
    }

    @Test
    public void remove_existingExercise_removesExercise() {
        uniqueExerciseList.add(EXERCISE_A);
        uniqueExerciseList.remove(EXERCISE_A);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }


    @Test
    public void setExercises_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.setExercises((List<Exercise>) null));
    }

    @Test
    public void setExercises_list_replacesOwnListWithProvidedList() {
        uniqueExerciseList.add(EXERCISE_A);
        List<Exercise> exerciseList = Collections.singletonList(EXERCISE_B);
        uniqueExerciseList.setExercises(exerciseList);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        expectedUniqueExerciseList.add(EXERCISE_B);
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    @Test
    public void setExercises_listWithDuplicateExercises_throwsDuplicateExerciseException() {
        List<Exercise> listWithDuplicateExercises = Arrays.asList(EXERCISE_A, EXERCISE_A);
        assertThrows(DuplicateExerciseException.class, () -> uniqueExerciseList
                .setExercises(listWithDuplicateExercises));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
                UnsupportedOperationException.class, () -> uniqueExerciseList
                        .asUnmodifiableObservableList().remove(0));
    }

    @Test
    void iterator() {
        uniqueExerciseList.add(EXERCISE_A);
        assertEquals(EXERCISE_A, uniqueExerciseList.iterator().next());
    }

    @Test
    void testHashCode() {
        uniqueExerciseList.add(EXERCISE_A);
        uniqueExerciseList.add(EXERCISE_B);
        assertTrue(uniqueExerciseList.hashCode() == uniqueExerciseList.hashCode());
    }

}
