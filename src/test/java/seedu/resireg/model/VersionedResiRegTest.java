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
import seedu.resireg.testutil.AddressBookBuilder;

class VersionedResiRegTest {
    private final ReadOnlyAddressBook resiRegWithAmy = new AddressBookBuilder().withStudent(AMY).build();
    private final ReadOnlyAddressBook resiRegWithBob = new AddressBookBuilder().withStudent(BOB).build();
    private final ReadOnlyAddressBook resiRegWithCarl = new AddressBookBuilder().withStudent(CARL).build();
    private final ReadOnlyAddressBook emptyResiReg = new AddressBookBuilder().build();

    @Test
    public void commit_singleResiReg_noStatesRemovedCurrentStateSaved() {
        VersionedResiReg versionedResiReg = prepareResiRegList(emptyResiReg);

        versionedResiReg.save();
        assertResiRegListStatus(versionedResiReg,
                Collections.singletonList(emptyResiReg),
                emptyResiReg,
                Collections.emptyList());
    }

    @Test
    public void commit_multipleResiRegPtrAtEndOfStateList_noStatesRemovedCurrentStateSaved() {
        VersionedResiReg versionedResiReg = prepareResiRegList(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);

        versionedResiReg.save();
        assertResiRegListStatus(versionedResiReg,
                Arrays.asList(emptyResiReg, resiRegWithAmy, resiRegWithBob),
                resiRegWithBob,
                Collections.emptyList());
    }

    @Test
    public void commit_multipleResiRegPtrNotAtEndOfStateList_statesAfterPtrRemovedCurrentStateSaved() {
        VersionedResiReg versionedResiReg = prepareResiRegList(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        shiftCurrentStatePtrLeft(versionedResiReg, 2);

        versionedResiReg.save();
        assertResiRegListStatus(versionedResiReg,
                Collections.singletonList(emptyResiReg),
                emptyResiReg,
                Collections.emptyList());
    }

    @Test
    public void canUndo_multipleResiRegPtrAtEndOfStateList_returnsTrue() {
        VersionedResiReg versionedResiReg = prepareResiRegList(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);

        assertTrue(versionedResiReg.canUndo());
    }

    @Test
    public void canUndo_multipleResiRegPtrAtStartOfStateList_returnsTrue() {
        VersionedResiReg versionedResiReg = prepareResiRegList(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        shiftCurrentStatePtrLeft(versionedResiReg, 1);

        assertTrue(versionedResiReg.canUndo());
    }

    @Test
    public void canUndo_singleResiReg_returnsFalse() {
        VersionedResiReg versionedResiReg = prepareResiRegList(emptyResiReg);

        assertFalse(versionedResiReg.canUndo());
    }

    @Test
    public void canUndo_multipleResiRegPtrAtStartOfStateList_returnsFalse() {
        VersionedResiReg versionedResiReg = prepareResiRegList(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        shiftCurrentStatePtrLeft(versionedResiReg, 2);

        assertFalse(versionedResiReg.canUndo());
    }

    @Test
    public void canRedo_multipleResiRegPtrNotAtEndOfStateList_returnsTrue() {
        VersionedResiReg versionedResiReg = prepareResiRegList(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        shiftCurrentStatePtrLeft(versionedResiReg, 1);

        assertTrue(versionedResiReg.canRedo());
    }

    @Test
    public void canRedo_multipleResiRegPtrAtStartOfStateList_returnsTrue() {
        VersionedResiReg versionedResiReg = prepareResiRegList(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        shiftCurrentStatePtrLeft(versionedResiReg, 2);

        assertTrue(versionedResiReg.canRedo());
    }

    @Test
    public void canRedo_singleResiReg_returnsFalse() {
        VersionedResiReg versionedResiReg = prepareResiRegList(emptyResiReg);

        assertFalse(versionedResiReg.canRedo());
    }

    @Test
    public void canRedo_multipleResiRegPtrAtEndOfStateList_returnsFalse() {
        VersionedResiReg versionedResiReg = prepareResiRegList(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);

        assertFalse(versionedResiReg.canRedo());
    }

    @Test
    public void undo_multipleResiRegPtrAtEndOfStateList_success() {
        VersionedResiReg versionedResiReg = prepareResiRegList(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);

        versionedResiReg.undo();
        assertResiRegListStatus(versionedResiReg,
                Collections.singletonList(emptyResiReg),
                resiRegWithAmy,
                Collections.singletonList(resiRegWithBob));
    }

    @Test
    public void undo_multipleResiRegPtrNotAtStartOfStateList_success() {
        VersionedResiReg versionedResiReg = prepareResiRegList(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        shiftCurrentStatePtrLeft(versionedResiReg, 1);

        versionedResiReg.undo();
        assertResiRegListStatus(versionedResiReg,
                Collections.emptyList(),
                emptyResiReg,
                Arrays.asList(resiRegWithAmy, resiRegWithBob));
    }

    @Test
    public void undo_singleResiReg_throwsNoUndoableStateException() {
        VersionedResiReg versionedResiReg = prepareResiRegList(emptyResiReg);

        assertThrows(NoUndoableStateException.class, versionedResiReg::undo);
    }

    @Test
    public void undo_multipleResiRegPtrAtStartOfStateList_throwsNoUndoableStateException() {
        VersionedResiReg versionedResiReg = prepareResiRegList(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        shiftCurrentStatePtrLeft(versionedResiReg, 2);

        assertThrows(NoUndoableStateException.class, versionedResiReg::undo);
    }

    @Test
    public void redo_multipleResiRegPtrNotAtEndOfStateList_success() {
        VersionedResiReg versionedResiReg = prepareResiRegList(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        shiftCurrentStatePtrLeft(versionedResiReg, 1);

        versionedResiReg.redo();
        assertResiRegListStatus(versionedResiReg,
                Arrays.asList(emptyResiReg, resiRegWithAmy),
                resiRegWithBob,
                Collections.emptyList());
    }

    @Test
    public void redo_multipleResiRegPtrAtStartOfStateList_success() {
        VersionedResiReg versionedResiReg = prepareResiRegList(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        shiftCurrentStatePtrLeft(versionedResiReg, 2);

        versionedResiReg.redo();
        assertResiRegListStatus(versionedResiReg,
                Collections.singletonList(emptyResiReg),
                resiRegWithAmy,
                Collections.singletonList(resiRegWithBob));

    }

    @Test
    public void redo_singleResiReg_throwsNoRedoableStateException() {
        VersionedResiReg versionedResiReg = prepareResiRegList(emptyResiReg);

        assertThrows(NoRedoableStateException.class, versionedResiReg::redo);
    }

    @Test
    public void redo_multipleResiRegPtrAtEndOfStateList_throwsNoRedoableStateException() {
        VersionedResiReg versionedResiReg = prepareResiRegList(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);

        assertThrows(NoRedoableStateException.class, versionedResiReg::redo);
    }

    @Test
    public void equals() {
        VersionedResiReg versionedResiReg = prepareResiRegList(resiRegWithAmy, resiRegWithBob);

        // same values -> returns true
        VersionedResiReg cp = prepareResiRegList(resiRegWithAmy, resiRegWithBob);
        assertTrue(versionedResiReg.equals(cp));

        // same object -> returns true
        assertTrue(versionedResiReg.equals(versionedResiReg));

        // null -> returns false
        assertFalse(versionedResiReg.equals(null));

        // different types -> returns false
        assertFalse(versionedResiReg.equals(""));

        // different state list -> returns false
        VersionedResiReg diff = prepareResiRegList(resiRegWithAmy, resiRegWithCarl);
        assertFalse(versionedResiReg.equals(diff));

        // different index of curr ptr -> returns false
        VersionedResiReg diffStatePtr = prepareResiRegList(
                emptyResiReg, resiRegWithAmy, resiRegWithBob);
        shiftCurrentStatePtrLeft(versionedResiReg, 1);
        assertFalse(versionedResiReg.equals(diffStatePtr));
    }

    /**
     * Asserts that {@code versionedResiReg} currently points to {@code expectedCurrentState},
     * states before {@code versionedResiReg#currentStatePtr} is equal to
     * {@code expectedStatesBeforePtr} and states after {@code versionedResiReg#currentStatePtr}
     * is equal to {@code expectedStatesAfterPtr}
     */
    private void assertResiRegListStatus(VersionedResiReg versionedResiReg,
                                         List<ReadOnlyAddressBook> expectedStatesBeforePtr,
                                         ReadOnlyAddressBook expectedCurrentState,
                                         List<ReadOnlyAddressBook> expectedStatesAfterPtr) {
        // check state currently pointing to is correct
        assertEquals(new AddressBook(versionedResiReg), expectedCurrentState);

        // shift ptr to start of state list
        while (versionedResiReg.canUndo()) {
            versionedResiReg.undo();
        }

        // check states before ptr are correct
        for (var expectedResiReg : expectedStatesBeforePtr) {
            assertEquals(expectedResiReg, new AddressBook(versionedResiReg));
            versionedResiReg.redo();
        }

        // check states after ptr are correct
        for (var expectedResiReg : expectedStatesAfterPtr) {
            versionedResiReg.redo();
            assertEquals(expectedResiReg, new AddressBook(versionedResiReg));
        }

        // check that there are not more states after ptr
        assertFalse(versionedResiReg.canRedo());

        // revert ptr to original position
        expectedStatesAfterPtr.forEach(unused -> versionedResiReg.undo());
    }

    /**
     * Creates and returns a {@code VersionedResiReg} with the {@code resiRegStates} added into it, and
     * the {@code VersionedResiReg#currentStatePtr} at the end of the list.
     */
    private VersionedResiReg prepareResiRegList(ReadOnlyAddressBook ...resiRegStates) {
        assertFalse(resiRegStates.length == 0);

        VersionedResiReg versionedResiReg = new VersionedResiReg(resiRegStates[0]);
        for (int i = 1; i < resiRegStates.length; i++) {
            versionedResiReg.resetData(resiRegStates[i]);
            versionedResiReg.save();
        }

        return versionedResiReg;
    }

    /**
     * Shifts the {@code versionedResiReg#currentStatePtr} by {@code shift} to the left of the list.
     */
    private void shiftCurrentStatePtrLeft(VersionedResiReg versionedResiReg, int ctr) {
        for (int i = 0; i < ctr; i++) {
            versionedResiReg.undo();
        }
    }
}
