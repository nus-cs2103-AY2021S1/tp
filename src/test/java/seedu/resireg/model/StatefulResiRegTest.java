package seedu.resireg.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.Assert.assertThrows;
import static seedu.resireg.testutil.TypicalStudents.AMY;
import static seedu.resireg.testutil.TypicalStudents.BOB;
import static seedu.resireg.testutil.TypicalStudents.CARL;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.resireg.model.exceptions.NoRedoableStateException;
import seedu.resireg.model.exceptions.NoUndoableStateException;
import seedu.resireg.testutil.ResiRegBuilder;

class StatefulResiRegTest {
    private final ReadOnlyResiReg resiRegWithAmy = new ResiRegBuilder().withStudent(AMY).build();
    private final ReadOnlyResiReg resiRegWithBob = new ResiRegBuilder().withStudent(BOB).build();
    private final ReadOnlyResiReg resiRegWithCarl = new ResiRegBuilder().withStudent(CARL).build();
    private final ReadOnlyResiReg emptyResiReg = new ResiRegBuilder().build();

    @Test
    public void save_singleResiReg_noStatesRemovedCurrentStateSaved() {
        StatefulResiReg statefulResiReg = prepareResiReg(emptyResiReg);

        assertResiRegStatesStatus(statefulResiReg,
                emptyResiReg,
                Collections.emptyList(),
                Collections.emptyList());
    }

    @Test
    public void save_multipleResiRegStates_noStatesRemovedCurrentStateSaved() {
        StatefulResiReg statefulResiReg = prepareResiReg(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);

        assertResiRegStatesStatus(statefulResiReg,
                resiRegWithBob,
                Arrays.asList(emptyResiReg, resiRegWithAmy),
                Collections.emptyList());
    }

    @Test
    public void save_multipleResiRegStatesAndUndoStackIsNotEmpty_redoStackClearedCurrentStateSaved() {
        StatefulResiReg statefulResiReg = prepareResiReg(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        decreaseUndoStatesStack(statefulResiReg, 2);

        statefulResiReg.resetData(resiRegWithAmy);
        statefulResiReg.save();

        assertResiRegStatesStatus(statefulResiReg,
                resiRegWithAmy,
                Collections.singletonList(emptyResiReg),
                Collections.emptyList()
        );
    }

    @Test
    public void canUndo_multipleResiRegStatesAndUndoStackIsFull_returnsTrue() {
        StatefulResiReg statefulResiReg = prepareResiReg(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);

        assertTrue(statefulResiReg.canUndo());
    }

    @Test
    public void canUndo_multipleResiRegStatesAndUndoStackIsNotEmpty_returnsTrue() {
        StatefulResiReg statefulResiReg = prepareResiReg(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        decreaseUndoStatesStack(statefulResiReg, 1);

        assertTrue(statefulResiReg.canUndo());
    }

    @Test
    public void canUndo_singleResiReg_returnsFalse() {
        StatefulResiReg statefulResiReg = prepareResiReg(emptyResiReg);

        assertFalse(statefulResiReg.canUndo());
    }

    @Test
    public void canUndo_multipleResiRegStatesUndoStackIsEmpty_returnsFalse() {
        StatefulResiReg statefulResiReg = prepareResiReg(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        decreaseUndoStatesStack(statefulResiReg, 2);

        assertFalse(statefulResiReg.canUndo());
    }

    @Test
    public void canRedo_multipleResiRegStatesRedoStackIsNotEmpty_returnsTrue() {
        StatefulResiReg statefulResiReg = prepareResiReg(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        decreaseUndoStatesStack(statefulResiReg, 1);

        assertTrue(statefulResiReg.canRedo());
    }

    @Test
    public void canRedo_multipleResiRegStatesRedoStackIsFull_returnsTrue() {
        StatefulResiReg statefulResiReg = prepareResiReg(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        decreaseUndoStatesStack(statefulResiReg, 2);

        assertTrue(statefulResiReg.canRedo());
    }

    @Test
    public void canRedo_singleResiReg_returnsFalse() {
        StatefulResiReg statefulResiReg = prepareResiReg(emptyResiReg);

        assertFalse(statefulResiReg.canRedo());
    }

    @Test
    public void canRedo_multipleResiRegStatesRedoStackIsEmpty_returnsFalse() {
        StatefulResiReg statefulResiReg = prepareResiReg(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);

        assertFalse(statefulResiReg.canRedo());
    }

    @Test
    public void undo_multipleResiRegStatesUndoStackIsFull_success() {
        StatefulResiReg statefulResiReg = prepareResiReg(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);

        statefulResiReg.undo();
        assertResiRegStatesStatus(statefulResiReg,
                resiRegWithAmy,
                Collections.singletonList(emptyResiReg),
                Collections.singletonList(resiRegWithBob));
    }

    @Test
    public void undo_multipleResiRegPtrUndoStackIsNotEmpty_success() {
        StatefulResiReg statefulResiReg = prepareResiReg(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        decreaseUndoStatesStack(statefulResiReg, 1);

        statefulResiReg.undo();
        assertResiRegStatesStatus(statefulResiReg,
                emptyResiReg,
                Collections.emptyList(),
                Arrays.asList(resiRegWithAmy, resiRegWithBob));
    }

    @Test
    public void undo_singleResiReg_throwsNoUndoableStateException() {
        StatefulResiReg statefulResiReg = prepareResiReg(emptyResiReg);

        assertThrows(NoUndoableStateException.class, statefulResiReg::undo);
    }

    @Test
    public void undo_multipleResiRegStatesUndoStackIsEmpty_throwsNoUndoableStateException() {
        StatefulResiReg statefulResiReg = prepareResiReg(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        decreaseUndoStatesStack(statefulResiReg, 2);

        assertThrows(NoUndoableStateException.class, statefulResiReg::undo);
    }

    @Test
    public void redo_multipleResiRegStatesRedoStackIsNotEmpty_success() {
        StatefulResiReg statefulResiReg = prepareResiReg(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        decreaseUndoStatesStack(statefulResiReg, 1);

        statefulResiReg.redo();
        assertResiRegStatesStatus(statefulResiReg,
                resiRegWithBob,
                Arrays.asList(emptyResiReg, resiRegWithAmy),
                Collections.emptyList());
    }

    @Test
    public void redo_multipleResiRegStatesRedoStackIsFull_success() {
        StatefulResiReg statefulResiReg = prepareResiReg(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        decreaseUndoStatesStack(statefulResiReg, 2);

        statefulResiReg.redo();
        assertResiRegStatesStatus(statefulResiReg,
                resiRegWithAmy,
                Collections.singletonList(emptyResiReg),
                Collections.singletonList(resiRegWithBob));

    }

    @Test
    public void redo_singleResiReg_throwsNoRedoableStateException() {
        StatefulResiReg statefulResiReg = prepareResiReg(emptyResiReg);

        assertThrows(NoRedoableStateException.class, statefulResiReg::redo);
    }

    @Test
    public void redo_multipleResiRegPtrAtEndOfStateList_throwsNoRedoableStateException() {
        StatefulResiReg statefulResiReg = prepareResiReg(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);

        assertThrows(NoRedoableStateException.class, statefulResiReg::redo);
    }

    @Test
    public void equals() {
        StatefulResiReg statefulResiReg = prepareResiReg(resiRegWithAmy, resiRegWithBob);

        // same values -> returns true
        StatefulResiReg cp = prepareResiReg(resiRegWithAmy, resiRegWithBob);
        assertTrue(statefulResiReg.equals(cp));

        // same object -> returns true
        assertTrue(statefulResiReg.equals(statefulResiReg));

        // null -> returns false
        assertFalse(statefulResiReg.equals(null));

        // different types -> returns false
        assertFalse(statefulResiReg.equals(""));

        // different states -> returns false
        StatefulResiReg diff = prepareResiReg(resiRegWithAmy, resiRegWithCarl);
        assertFalse(statefulResiReg.equals(diff));

        // different current state -> returns false
        StatefulResiReg diffStatePtr = prepareResiReg(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        decreaseUndoStatesStack(statefulResiReg, 1);
        assertFalse(statefulResiReg.equals(diffStatePtr));
    }

    /**
     * Asserts that {@code versionedResiReg} currently points to {@code expectedCurrentState},
     * states before {@code versionedResiReg#currentStatePtr} is equal to
     * {@code expectedStatesBeforePtr} and states after {@code versionedResiReg#currentStatePtr}
     * is equal to {@code expectedStatesAfterPtr}
     */
    private void assertResiRegStatesStatus(StatefulResiReg statefulResiReg,
                                         ReadOnlyResiReg expectedCurrentState,
                                         List<ReadOnlyResiReg> undoStates,
                                         List<ReadOnlyResiReg> redoStates) {

        // check state currently pointing to is correct
        assertEquals(new ResiReg(statefulResiReg), expectedCurrentState);

        // undo until beginning
        while (statefulResiReg.canUndo()) {
            statefulResiReg.undo();
        }

        // check undo states are correct
        for (var expectedResiReg : undoStates) {
            assertEquals(expectedResiReg, new ResiReg(statefulResiReg));
            statefulResiReg.redo();
        }

        // check redo states are correct
        for (var expectedResiReg : redoStates) {
            statefulResiReg.redo();
            assertEquals(expectedResiReg, new ResiReg(statefulResiReg));
        }

        // check that there are no more states to redo
        assertFalse(statefulResiReg.canRedo());

        // revert to current state
        redoStates.forEach(unused -> statefulResiReg.undo());
    }

    /**
     * Creates and returns a {@code StatefulResiReg} with the {@code resiRegStates} added into it, and
     * all of {@code StatefulResiReg#currState}, {@code StatefulResiReg#redoStatesStack}
     * and {@code StatefulResiReg#undoStatesStack} updated.
     */
    private StatefulResiReg prepareResiReg(ReadOnlyResiReg ...resiRegStates) {
        assertFalse(resiRegStates.length == 0);

        StatefulResiReg statefulResiReg = new StatefulResiReg(resiRegStates[0]);
        for (int i = 1; i < resiRegStates.length; i++) {
            statefulResiReg.resetData(resiRegStates[i]);
            statefulResiReg.save();
        }

        return statefulResiReg;
    }

    /**
     * Decreases the size of {@code StatefulResiReg#undoStatesStack} by {@code ctr} and
     * accordingly increase the size of {@code StatefulResiReg#redoStatesStack} by {@code ctr}
     */
    private void decreaseUndoStatesStack(StatefulResiReg statefulResiReg, int ctr) {
        for (int i = 0; i < ctr; i++) {
            statefulResiReg.undo();
        }
    }
}
