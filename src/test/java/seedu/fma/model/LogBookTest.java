package seedu.fma.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.testutil.Assert.assertThrows;
import static seedu.fma.testutil.TypicalLogs.LOG_A;
import static seedu.fma.testutil.TypicalLogs.LOG_B;
import static seedu.fma.testutil.TypicalLogs.getTypicalLogBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.exercise.exceptions.ExerciseNotFoundException;
import seedu.fma.model.log.Comment;
import seedu.fma.model.log.Log;
import seedu.fma.model.log.Rep;
import seedu.fma.model.log.exceptions.DuplicateLogException;
import seedu.fma.model.util.Calories;
import seedu.fma.model.util.Name;
import seedu.fma.testutil.LogBuilder;



public class LogBookTest {
    private final LogBook fmaBook = new LogBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), fmaBook.getLogList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fmaBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyLogBook_replacesData() {
        LogBook newData = getTypicalLogBook();
        fmaBook.resetData(newData);
        assertEquals(newData, fmaBook);
    }

    @Test
    public void resetData_withDuplicateLogs_throwsDuplicateLogException() {
        // Two logs with the same identity fields
        Log editedLog = new LogBuilder(LOG_A)
                .build();
        List<Log> newLogs = Arrays.asList(LOG_A, editedLog);
        LogBookStub newData = new LogBookStub(newLogs);

        assertThrows(DuplicateLogException.class, () -> fmaBook.resetData(newData));
    }

    @Test
    public void hasLog_nullLog_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fmaBook.hasLog(null));
    }

    @Test
    public void hasLog_logNotInLogBook_returnsFalse() {
        assertFalse(fmaBook.hasLog(LOG_A));
    }

    @Test
    public void hasLog_logInLogBook_returnsTrue() {
        fmaBook.addLog(LOG_A);
        assertTrue(fmaBook.hasLog(LOG_A));
    }

    @Test
    public void hasLog_logWithSameIdentityFieldsInLogBook_returnsTrue() {
        fmaBook.addLog(LOG_A);
        Log editedLog = new LogBuilder(LOG_A)
                .build();
        assertTrue(fmaBook.hasLog(editedLog));
    }

    @Test
    public void getLogList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> fmaBook.getLogList().remove(0));
    }

    @Test
    public void setExercise_modifyExercise_updateBothExerciseAndLogs() {
        Exercise exerciseA = new Exercise(new Name("Jump"), new Calories(30));
        Log logA = new Log(exerciseA, new Rep(50), new Comment("Hi"));
        fmaBook.addExercise(exerciseA);
        fmaBook.addLog(logA);
        Exercise exerciseB = new Exercise(new Name("Skip"), new Calories(50));
        fmaBook.setExercise(exerciseA, exerciseB);
        assertEquals(fmaBook.getLogList().stream().filter(log -> log.getExercise().equals(exerciseB)).count(), 1);
    }

    @Test
    void testHashCode() {
        fmaBook.addLog(LOG_A);
        fmaBook.addLog(LOG_B);
        assertEquals(fmaBook.hashCode(), fmaBook.hashCode());
    }

    /**
     * A stub ReadOnlyLogBook whose logs list can violate interface constraints.
     */
    private static class LogBookStub implements ReadOnlyLogBook {
        private final ObservableList<Log> logs = FXCollections.observableArrayList();
        private final ObservableList<Exercise> exercises = FXCollections.observableArrayList();

        LogBookStub(Collection<Log> logs) {
            this.logs.setAll(logs);
        }

        @Override
        public ObservableList<Log> getLogList() {
            return logs;
        }

        @Override
        public ObservableList<Exercise> getExerciseList() {
            return exercises;
        }

        @Override
        public Exercise getExercise(Name name) throws ExerciseNotFoundException {
            return null;
        }
    }
}
