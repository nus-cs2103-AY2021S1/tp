package seedu.address.model.routine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExercises.BENCH_PRESS;
import static seedu.address.testutil.TypicalExercises.SQUATS;
import static seedu.address.testutil.TypicalRoutines.LEG_DAY;
import static seedu.address.testutil.TypicalRoutines.UPPER_BODY;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.exceptions.ExerciseNotFoundException;
import seedu.address.model.routine.exceptions.DuplicateRoutineException;
import seedu.address.model.routine.exceptions.RoutineNotFoundException;
import seedu.address.model.util.Name;

public class UniqueRoutineListTest {

    private final UniqueRoutineList uniqueRoutineList = new UniqueRoutineList();

    @Test
    public void contains_nullRoutine_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.contains(null));
    }

    @Test
    public void contains_routineNotInList_returnsFalse() {
        assertFalse(uniqueRoutineList.contains(LEG_DAY));
    }

    @Test
    public void contains_routineInList_returnsTrue() {
        uniqueRoutineList.add(LEG_DAY);
        assertTrue(uniqueRoutineList.contains(LEG_DAY));
    }

    @Test
    public void contains_routineWithSameIdentityFieldsInList_returnsTrue() {
        uniqueRoutineList.add(LEG_DAY);
        Name copyName = new Name("Leg Day");
        Routine copyRoutine = new Routine(copyName);
        assertTrue(uniqueRoutineList.contains(copyRoutine));
    }

    @Test
    public void add_nullRoutine_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.add(null));
    }

    @Test
    public void add_duplicateRoutine_throwsDuplicateRoutineException() {
        uniqueRoutineList.add(LEG_DAY);
        assertThrows(DuplicateRoutineException.class, () -> uniqueRoutineList.add(LEG_DAY));
    }

    @Test
    public void setRoutine_nullTargetRoutine_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.setRoutine(null, LEG_DAY));
    }

    @Test
    public void setRoutine_nullEditedRoutine_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.setRoutine(LEG_DAY, null));
    }

    @Test
    public void setRoutine_targetRoutineNotInList_throwsRoutineNotFoundException() {
        assertThrows(RoutineNotFoundException.class, () -> uniqueRoutineList.setRoutine(LEG_DAY, LEG_DAY));
    }

    @Test
    public void setRoutine_editedRoutineIsSameRoutine_success() {
        uniqueRoutineList.add(LEG_DAY);
        uniqueRoutineList.setRoutine(LEG_DAY, LEG_DAY);
        UniqueRoutineList expectedUniqueRoutineList = new UniqueRoutineList();
        expectedUniqueRoutineList.add(LEG_DAY);
        assertEquals(expectedUniqueRoutineList, uniqueRoutineList);
    }

    @Test
    public void setRoutine_editedRoutineHasSameIdentity_success() {
        uniqueRoutineList.add(LEG_DAY);
        Name copyName = new Name("Leg Day");
        Routine copyRoutine = new Routine(copyName);
        uniqueRoutineList.setRoutine(LEG_DAY, copyRoutine);
        UniqueRoutineList expectedUniqueRoutineList = new UniqueRoutineList();
        expectedUniqueRoutineList.add(copyRoutine);
        assertEquals(expectedUniqueRoutineList, uniqueRoutineList);
    }

    @Test
    public void setRoutine_editedRoutineHasDifferentIdentity_success() {
        uniqueRoutineList.add(LEG_DAY);
        uniqueRoutineList.setRoutine(LEG_DAY, UPPER_BODY);
        UniqueRoutineList expectedUniqueRoutineList = new UniqueRoutineList();
        expectedUniqueRoutineList.add(UPPER_BODY);
        assertEquals(expectedUniqueRoutineList, uniqueRoutineList);
    }

    @Test
    public void setRoutine_editedRoutineHasNonUniqueIdentity_throwsDuplicateRoutineException() {
        uniqueRoutineList.add(LEG_DAY);
        uniqueRoutineList.add(UPPER_BODY);
        assertThrows(DuplicateRoutineException.class, () -> uniqueRoutineList.setRoutine(LEG_DAY, UPPER_BODY));
    }

    @Test
    public void remove_nullRoutine_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.remove(null));
    }

    @Test
    public void remove_routineDoesNotExist_throwsRoutineNotFoundException() {
        assertThrows(RoutineNotFoundException.class, () -> uniqueRoutineList.remove(LEG_DAY));
    }

    @Test
    public void remove_existingRoutine_removesRoutine() {
        uniqueRoutineList.add(LEG_DAY);
        uniqueRoutineList.remove(LEG_DAY);
        UniqueRoutineList expectedUniqueRoutineList = new UniqueRoutineList();
        assertEquals(expectedUniqueRoutineList, uniqueRoutineList);
    }

    @Test
    public void setRoutines_nullUniqueRoutineList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.setRoutines((UniqueRoutineList) null));
    }

    @Test
    public void setRoutines_uniqueRoutineList_replacesOwnListWithProvideduniqueRoutineList() {
        uniqueRoutineList.add(LEG_DAY);
        UniqueRoutineList expectedUniqueRoutineList = new UniqueRoutineList();
        expectedUniqueRoutineList.add(UPPER_BODY);
        uniqueRoutineList.setRoutines(expectedUniqueRoutineList);
        assertEquals(expectedUniqueRoutineList, uniqueRoutineList);
    }

    @Test
    public void setRoutines_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.setRoutines((List<Routine>) null));
    }

    @Test
    public void setRoutines_list_replacesOwnListWithProvidedList() {
        uniqueRoutineList.add(LEG_DAY);
        List<Routine> routineList = Collections.singletonList(UPPER_BODY);
        uniqueRoutineList.setRoutines(routineList);
        UniqueRoutineList expectedUniqueRoutineList = new UniqueRoutineList();
        expectedUniqueRoutineList.add(UPPER_BODY);
        assertEquals(expectedUniqueRoutineList, uniqueRoutineList);
    }

    @Test
    public void setRoutines_listWithDuplicateRoutines_throwsDuplicateRoutineException() {
        List<Routine> listWithDuplicateRoutines = Arrays.asList(LEG_DAY, LEG_DAY);
        assertThrows(DuplicateRoutineException.class, () -> uniqueRoutineList.setRoutines(listWithDuplicateRoutines));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueRoutineList
                .asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void addExercise() {
        UniqueRoutineList uniqueRoutineList = new UniqueRoutineList();
        Routine legDay = new Routine(new Name("Leg Day"));
        assertThrows(RoutineNotFoundException.class, () -> uniqueRoutineList.addExercise(LEG_DAY, SQUATS));
        uniqueRoutineList.add(legDay);
        uniqueRoutineList.addExercise(legDay, SQUATS);

        Iterator<Routine> iterator = uniqueRoutineList.iterator();

        assertTrue(iterator.next().hasExercise(SQUATS));
    }

    @Test
    public void testCheckSize() {
        assertEquals(uniqueRoutineList.checkSize(), 0);
        uniqueRoutineList.add(LEG_DAY);
        assertEquals(uniqueRoutineList.checkSize(), 1);
    }

    @Test
    public void testIterator() {
        uniqueRoutineList.add(LEG_DAY);
        uniqueRoutineList.add(UPPER_BODY);

        Iterator<Routine> iterator = uniqueRoutineList.iterator();

        Routine routine = iterator.next();
        assertEquals(routine, LEG_DAY);

        Routine routine1 = iterator.next();
        assertEquals(routine1, UPPER_BODY);
    }

    @Test
    public void testHashCode() {
        UniqueRoutineList uniqueRoutineList1 = new UniqueRoutineList();
        uniqueRoutineList1.add(UPPER_BODY);
        assertEquals(uniqueRoutineList.hashCode(), uniqueRoutineList.hashCode());
        assertNotEquals(uniqueRoutineList.hashCode(), uniqueRoutineList1.hashCode());
    }

    @Test
    public void testRetrieveRoutine() {
        uniqueRoutineList.add(UPPER_BODY);
        uniqueRoutineList.add(LEG_DAY);

        assertEquals(uniqueRoutineList.retrieveRoutine(UPPER_BODY), UPPER_BODY);
        assertNotEquals(uniqueRoutineList.retrieveRoutine(UPPER_BODY), LEG_DAY);
    }

    @Test
    public void testDelete() {
        uniqueRoutineList.add(UPPER_BODY);

        assertThrows(RoutineNotFoundException.class, () -> uniqueRoutineList.deleteExerciseFromRoutine(
                LEG_DAY, BENCH_PRESS
        ));

        assertThrows(ExerciseNotFoundException.class, () -> uniqueRoutineList.deleteExerciseFromRoutine(
                UPPER_BODY, BENCH_PRESS
        ));

        uniqueRoutineList.addExercise(UPPER_BODY, BENCH_PRESS);
        assertTrue(UPPER_BODY.hasExercise(BENCH_PRESS));

        uniqueRoutineList.deleteExerciseFromRoutine(UPPER_BODY, BENCH_PRESS);
        assertFalse(UPPER_BODY.hasExercise(BENCH_PRESS));

        uniqueRoutineList.addExercise(UPPER_BODY, BENCH_PRESS);
        uniqueRoutineList.add(LEG_DAY);
        uniqueRoutineList.addExercise(LEG_DAY, BENCH_PRESS);

        uniqueRoutineList.deleteExercise(BENCH_PRESS);
        assertFalse(UPPER_BODY.hasExercise(BENCH_PRESS));
        assertFalse(LEG_DAY.hasExercise(BENCH_PRESS));
    }

    @Test
    public void testSetExercise() {
        uniqueRoutineList.add(UPPER_BODY);
        uniqueRoutineList.add(LEG_DAY);
        uniqueRoutineList.addExercise(UPPER_BODY, BENCH_PRESS);
        uniqueRoutineList.addExercise(LEG_DAY, BENCH_PRESS);

        Exercise newExercise = new Exercise(new Name("Bench"), new HashSet<>());

        uniqueRoutineList.setExercise(BENCH_PRESS, newExercise);

        for (Routine routine : uniqueRoutineList) {
            assertTrue(routine.hasExercise(newExercise));
            assertFalse(routine.hasExercise(BENCH_PRESS));
        }
    }
}
