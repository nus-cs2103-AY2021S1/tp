package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.TypicalPatients.AMY;
import static seedu.address.testutil.TypicalPatients.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.testutil.CliniCalBuilder;

class VersionedCliniCalTest {

    private final ReadOnlyCliniCal cliniCalWithAmy = new CliniCalBuilder().withPatient(AMY).build();
    private final ReadOnlyCliniCal cliniCalWithAmyBob = new CliniCalBuilder().withPatient(AMY).withPatient(BOB).build();
    private final ReadOnlyCliniCal emptyCliniCal = new CliniCalBuilder().build();

    @Test
    public void commit_emptyCliniCal() {
        VersionedCliniCal versionedCliniCal = prepareCliniCalList(emptyCliniCal);

        versionedCliniCal.commit(emptyCliniCal, "");
        assertCliniCalListStatus(versionedCliniCal,
            Collections.singletonList(emptyCliniCal),
            emptyCliniCal,
            Collections.emptyList());
    }

    @Test
    public void commit_multipleCliniCal() {
        VersionedCliniCal versionedCliniCal = prepareCliniCalList(emptyCliniCal, cliniCalWithAmy);
        versionedCliniCal.commit(cliniCalWithAmyBob, "");
        assertCliniCalListStatus(versionedCliniCal,
            Arrays.asList(emptyCliniCal, cliniCalWithAmy),
            cliniCalWithAmyBob,
            Collections.emptyList());
    }

    @Test
    public void undo_atEndOfStateList() {
        VersionedCliniCal versionedCliniCal = prepareCliniCalList(emptyCliniCal, cliniCalWithAmy, cliniCalWithAmyBob);
        versionedCliniCal.undo();
        assertCliniCalListStatus(versionedCliniCal,
            Collections.singletonList(emptyCliniCal),
            cliniCalWithAmy,
            Collections.singletonList(cliniCalWithAmyBob));
    }

    @Test
    public void undo_notAtStartOfStateList() {
        VersionedCliniCal versionedCliniCal = prepareCliniCalList(emptyCliniCal, cliniCalWithAmy, cliniCalWithAmyBob);
        shiftCurrentStatePointerLeftwards(versionedCliniCal, 1);
        versionedCliniCal.undo();
        assertCliniCalListStatus(versionedCliniCal,
            Collections.emptyList(),
            emptyCliniCal,
            Arrays.asList(cliniCalWithAmy, cliniCalWithAmyBob));
    }

    @Test
    public void redo_atStartOfStateList() {
        VersionedCliniCal versionedCliniCal = prepareCliniCalList(emptyCliniCal, cliniCalWithAmy, cliniCalWithAmyBob);
        shiftCurrentStatePointerLeftwards(versionedCliniCal, 2);
        versionedCliniCal.redo();
        assertCliniCalListStatus(versionedCliniCal,
            Collections.singletonList(emptyCliniCal),
            cliniCalWithAmy,
            Collections.singletonList(cliniCalWithAmyBob));
    }

    @Test
    public void redo_notAtEndOfStateList() {
        VersionedCliniCal versionedCliniCal = prepareCliniCalList(emptyCliniCal, cliniCalWithAmy, cliniCalWithAmyBob);
        shiftCurrentStatePointerLeftwards(versionedCliniCal, 1);
        versionedCliniCal.redo();
        assertCliniCalListStatus(versionedCliniCal,
            Arrays.asList(emptyCliniCal, cliniCalWithAmy),
            cliniCalWithAmyBob,
            Collections.emptyList());
    }

    @Test
    public void canUndo_singleAtStartOfStateList() {
        VersionedCliniCal versionedCliniCal = prepareCliniCalList(emptyCliniCal);
        assertFalse(versionedCliniCal.canUndoCliniCal());
    }

    @Test
    public void canUndo_multipleAtStartOfStateList() {
        VersionedCliniCal versionedCliniCal = prepareCliniCalList(emptyCliniCal, cliniCalWithAmy, cliniCalWithAmyBob);
        shiftCurrentStatePointerLeftwards(versionedCliniCal, 2);
        assertFalse(versionedCliniCal.canUndoCliniCal());
    }

    @Test
    public void canRedo_singleAtEndOfStateList() {
        VersionedCliniCal versionedCliniCal = prepareCliniCalList(emptyCliniCal);
        assertFalse(versionedCliniCal.canRedoCliniCal());
    }

    @Test
    public void canRedo_multipleAtEndOfStateList() {
        VersionedCliniCal versionedCliniCal = prepareCliniCalList(emptyCliniCal, cliniCalWithAmy, cliniCalWithAmyBob);
        assertFalse(versionedCliniCal.canRedoCliniCal());
    }

    @Test
    public void getUndoCommand_multiple() {
        VersionedCliniCal versionedCliniCal = prepareCliniCalList(emptyCliniCal);
        versionedCliniCal.commit(cliniCalWithAmy, String.format(Messages.MESSAGE_UNDONE_REDONE_INPUT,
            AddCommand.COMMAND_WORD,
            AMY));
        versionedCliniCal.undo();
        assertEquals(String.format(Messages.MESSAGE_UNDONE_REDONE_INPUT,
            AddCommand.COMMAND_WORD,
            AMY), versionedCliniCal.getUndoCommand());
    }

    @Test
    public void getRedoCommand_multiple() {
        VersionedCliniCal versionedCliniCal = prepareCliniCalList(emptyCliniCal);
        versionedCliniCal.commit(cliniCalWithAmy, String.format(Messages.MESSAGE_UNDONE_REDONE_INPUT,
            AddCommand.COMMAND_WORD,
            AMY));
        versionedCliniCal.undo();
        versionedCliniCal.redo();
        assertEquals(String.format(Messages.MESSAGE_UNDONE_REDONE_INPUT,
            AddCommand.COMMAND_WORD,
            AMY), versionedCliniCal.getRedoCommand());
    }

    /**
     * Asserts that {@code versionedCliniCal} is currently pointing at {@code expectedCurrentState},
     * states before {@code versionedCliniCal#currentStatePointer} is equal to {@code expectedStatesBeforePointer},
     * and states after {@code versionedCliniCal#currentStatePointer} is equal to {@code expectedStatesAfterPointer}.
     */
    private void assertCliniCalListStatus(VersionedCliniCal versionedCliniCal,
                                             List<ReadOnlyCliniCal> expectedStatesBeforePointer,
                                             ReadOnlyCliniCal expectedCurrentState,
                                             List<ReadOnlyCliniCal> expectedStatesAfterPointer) {

        // check state currently pointing at is correct
        assertEquals(new CliniCal(versionedCliniCal.getCurrentCliniCalState()), expectedCurrentState);

        // shift pointer to start of state list
        while (versionedCliniCal.canUndoCliniCal()) {
            versionedCliniCal.undo();
        }

        // check states before pointer are correct
        for (ReadOnlyCliniCal expectedCliniCal : expectedStatesBeforePointer) {
            assertEquals(expectedCliniCal, new CliniCal(versionedCliniCal.getCurrentCliniCalState()));
            versionedCliniCal.redo();
        }

        // check states after pointer are correct
        for (ReadOnlyCliniCal expectedCliniCal : expectedStatesAfterPointer) {
            versionedCliniCal.redo();
            assertEquals(expectedCliniCal, new CliniCal(versionedCliniCal.getCurrentCliniCalState()));
        }

        // check that there are no more states after pointer
        assertFalse(versionedCliniCal.canRedoCliniCal());

        // revert pointer to original position
        expectedStatesAfterPointer.forEach(unused -> versionedCliniCal.undo());
    }

    /**
     * Creates and returns a {@code VersionedCliniCal} with the {@code cliniCalStates} added into it, and the
     * {@code VersionedCliniCal#currentStatePointer} at the end of list.
     */
    private VersionedCliniCal prepareCliniCalList(ReadOnlyCliniCal... cliniCalStates) {
        assertFalse(cliniCalStates.length == 0);
        VersionedCliniCal versionedCliniCal = new VersionedCliniCal((CliniCal) cliniCalStates[0]);
        for (int i = 1; i < cliniCalStates.length; i++) {
            versionedCliniCal.commit(cliniCalStates[i], "");
        }

        return versionedCliniCal;
    }

    /**
     * Shifts the {@code versionedCliniCal#currentStatePointer} by {@code count} to the left of its list.
     */
    private void shiftCurrentStatePointerLeftwards(VersionedCliniCal versionedCliniCal, int count) {
        for (int i = 0; i < count; i++) {
            versionedCliniCal.undo();
        }
    }
}
