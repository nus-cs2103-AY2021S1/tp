package seedu.pivot.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.model.VersionedPivot.INITIAL_COMMAND;
import static seedu.pivot.model.VersionedPivot.INITIAL_STATE;
import static seedu.pivot.testutil.Assert.assertThrows;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.pivot.logic.commands.Page;
import seedu.pivot.logic.commands.Undoable;
import seedu.pivot.model.VersionedPivot.PivotState;

public class VersionedPivotTest {

    private final Undoable initialCommandResult = null;
    private final String initialCommandMessageResult = "";
    private final PivotTest.PivotStub emptyPivot = new PivotTest.PivotStub(Collections.emptyList());
    private final PivotState initialState = new PivotState(emptyPivot, null, INITIAL_COMMAND);
    private VersionedPivot versionedPivot = new VersionedPivot(emptyPivot);

    @BeforeEach
    void setVersionedPivot() {
        versionedPivot = new VersionedPivot(emptyPivot);
    }

    @Test
    public void constructor() {
        // initialisation of pivotStateList
        List<PivotState> listWithInitialPivot = new ArrayList<>();
        listWithInitialPivot.add(new PivotState(emptyPivot, null, INITIAL_COMMAND));
        assertEquals(listWithInitialPivot, versionedPivot.getPivotStateList());

        // initialisation currentStatePointer
        assertEquals(INITIAL_STATE, versionedPivot.getCurrentStatePointer());
    }

    @Test
    public void commit_validPivotAndCommand_success() {
        String testMessage = "Valid Message";
        Undoable testCommand = new UndoableMainPageStub();
        versionedPivot.commit(getTypicalPivot(), testMessage, testCommand);

        // constructing expected versioned pivot object
        List<PivotState> expectedPivotStateList = new ArrayList<>();
        expectedPivotStateList.add(initialState);
        PivotState newPivotState = new PivotState(getTypicalPivot(), testCommand, testMessage);
        expectedPivotStateList.add(newPivotState);

        int expectedCurrentStatePointer = 1;

        VersionedPivot expectedVersionedPivot = new VersionedPivot(expectedPivotStateList,
                expectedCurrentStatePointer, initialCommandResult, initialCommandMessageResult);

        assertEquals(versionedPivot, expectedVersionedPivot);
    }

    @Test
    public void commit_nullPivot_throwsNullPointerException() {
        String testMessage = "Valid Message";
        Undoable testCommand = new UndoableMainPageStub();
        assertThrows(NullPointerException.class, () -> versionedPivot.commit(null, testMessage, testCommand));
    }

    @Test
    public void commit_nullString_throwsNullPointerException() {
        Undoable testCommand = new UndoableMainPageStub();
        assertThrows(NullPointerException.class, () -> versionedPivot.commit(
                getTypicalPivot(), null, testCommand));
    }

    @Test
    public void updateRedoUndoResult_initialState_resultsUpdated() {
        versionedPivot.updateRedoUndoResult();

        assertEquals(INITIAL_COMMAND, versionedPivot.getCommandMessageResult());
        assertEquals(null, versionedPivot.getCommandResult());
    }

    @Test
    public void updateRedoUndoResult_afterOneCommit_resultsUpdated() {
        String testMessage = "Valid Message";
        Undoable testCommand = new UndoableMainPageStub();
        versionedPivot.commit(getTypicalPivot(), testMessage, testCommand);
        versionedPivot.updateRedoUndoResult();

        assertEquals(testMessage, versionedPivot.getCommandMessageResult());
        assertEquals(testCommand, versionedPivot.getCommandResult());
    }

    @Test
    public void canUndo_atInitialState_returnFalse() {
        assertFalse(versionedPivot.canUndo());
    }

    @Test
    public void canUndo_notAtInitialState_returnTrue() {
        String testMessage = "Valid Message";
        Undoable testCommand = new UndoableMainPageStub();
        versionedPivot.commit(getTypicalPivot(), testMessage, testCommand);
        assertTrue(versionedPivot.canUndo());
    }

    @Test
    public void undo_undoPreviousState_success() {
        String testMessage = "Valid Message";
        Undoable testCommand = new UndoableMainPageStub();
        versionedPivot.commit(getTypicalPivot(), testMessage, testCommand);

        // check that previous state is correctly returned
        ReadOnlyPivot currentState = versionedPivot.undo();
        assertEquals(emptyPivot, currentState);

        // constructing expected versioned pivot object
        List<PivotState> expectedPivotStateList = new ArrayList<>();
        expectedPivotStateList.add(initialState);
        PivotState newPivotState = new PivotState(getTypicalPivot(), testCommand, testMessage);
        expectedPivotStateList.add(newPivotState);

        int expectedCurrentStatePointer = INITIAL_STATE;

        VersionedPivot expectedVersionedPivot = new VersionedPivot(expectedPivotStateList,
                expectedCurrentStatePointer, testCommand, testMessage);

        assertEquals(expectedVersionedPivot, versionedPivot);
    }

    @Test
    public void canRedo_atMostRecentState_returnFalse() {
        assertFalse(versionedPivot.canRedo());
    }

    @Test
    public void canRedo_notAtMostRecentState_returnTrue() {
        String testMessage = "Valid Message";
        Undoable testCommand = new UndoableMainPageStub();
        versionedPivot.commit(getTypicalPivot(), testMessage, testCommand);
        versionedPivot.undo();
        assertTrue(versionedPivot.canRedo());
    }

    @Test
    public void redo_redoFollowingState_success() {
        String testMessage = "Valid Message";
        Undoable testCommand = new UndoableMainPageStub();
        versionedPivot.commit(getTypicalPivot(), testMessage, testCommand);
        versionedPivot.undo();

        // check that following state is correctly returned
        ReadOnlyPivot currentState = versionedPivot.redo();
        assertEquals(getTypicalPivot(), currentState);

        // constructing expected versioned pivot object
        List<PivotState> expectedPivotStateList = new ArrayList<>();
        expectedPivotStateList.add(initialState);
        PivotState newPivotState = new PivotState(getTypicalPivot(), testCommand, testMessage);
        expectedPivotStateList.add(newPivotState);

        int expectedCurrentStatePointer = 1;

        VersionedPivot expectedVersionedPivot = new VersionedPivot(expectedPivotStateList,
                expectedCurrentStatePointer, testCommand, testMessage);

        assertEquals(expectedVersionedPivot, versionedPivot);
    }

    @Test
    public void purgeStates_nothingToPurge_listsUnchanged() {
        versionedPivot.purgeStates();

        // constructing expected versioned pivot object
        List<PivotState> expectedPivotStateList = new ArrayList<>();
        expectedPivotStateList.add(initialState);

        int expectedCurrentStatePointer = 0;

        VersionedPivot expectedVersionedPivot = new VersionedPivot(expectedPivotStateList,
                expectedCurrentStatePointer, initialCommandResult, initialCommandMessageResult);

        assertEquals(expectedVersionedPivot, versionedPivot);
    }

    @Test
    public void purgeStates_purgeStatesAfterCurrent_listsChanged() {
        String testMessage = "Valid Message";
        Undoable testCommand = new UndoableMainPageStub();
        versionedPivot.commit(getTypicalPivot(), testMessage, testCommand);
        versionedPivot.undo();
        versionedPivot.purgeStates();

        // constructing expected versioned pivot object
        List<PivotState> expectedPivotStateList = new ArrayList<>();
        expectedPivotStateList.add(initialState);

        int expectedCurrentStatePointer = 0;

        VersionedPivot expectedVersionedPivot = new VersionedPivot(expectedPivotStateList,
                expectedCurrentStatePointer, initialCommandResult, initialCommandMessageResult);

        assertEquals(expectedVersionedPivot, versionedPivot);
    }


    @Test
    public void isMainPageCommand_mainPageCommand_returnTrue() {
        String testMessage = "Valid Message";
        Undoable testCommand = new UndoableMainPageStub();
        versionedPivot.commit(getTypicalPivot(), testMessage, testCommand);
        versionedPivot.updateRedoUndoResult();
        assertTrue(versionedPivot.isMainPageCommand());
    }

    @Test
    public void isMainPageCommand_casePageCommand_returnFalse() {
        String testMessage = "Valid Message";
        Undoable testCommand = new UndoableCasePageStub();
        versionedPivot.commit(getTypicalPivot(), testMessage, testCommand);
        versionedPivot.updateRedoUndoResult();
        assertFalse(versionedPivot.isMainPageCommand());
    }


    private class UndoableMainPageStub implements Undoable {
        @Override
        public Page getPage() {
            return Page.MAIN;
        }
    }

    private class UndoableCasePageStub implements Undoable {
        @Override
        public Page getPage() {
            return Page.CASE;
        }
    }
}
