//package seedu.pivot.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.pivot.model.VersionedPivot.INITIAL_COMMAND;
//import static seedu.pivot.model.VersionedPivot.INITIAL_STATE;
//import static seedu.pivot.testutil.Assert.assertThrows;
//import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class VersionedPivotTest {
//
//    private final PivotTest.PivotStub emptyPivot = new PivotTest.PivotStub(Collections.emptyList());
//    private VersionedPivot versionedPivot = new VersionedPivot(emptyPivot);
//
//    @BeforeEach
//    void setVersionedPivot() {
//        versionedPivot = new VersionedPivot(emptyPivot);
//    }
//
//    @Test
//    public void constructor() {
//        // initialisation of pivotStateList
//        List<ReadOnlyPivot> listWithInitialPivot = new ArrayList<>();
//        listWithInitialPivot.add(emptyPivot);
//        assertEquals(listWithInitialPivot, versionedPivot.getPivotStateList());
//
//        // initialisation of commands
//        List<String> listWithInitialCommand = new ArrayList<>();
//        listWithInitialCommand.add(INITIAL_COMMAND);
//        assertEquals(listWithInitialCommand, versionedPivot.getCommands());
//
//        // initialisation currentStatePointer
//        assertEquals(INITIAL_STATE, versionedPivot.getCurrentStatePointer());
//    }
//
//    @Test
//    public void commit_validPivotAndCommand_success() {
//        String testCommand = "Valid Command";
//        versionedPivot.commit(getTypicalPivot(), testCommand);
//
//        // constructing expected versioned pivot object
//        List<ReadOnlyPivot> expectedPivotStateList = new ArrayList<>();
//        expectedPivotStateList.add(emptyPivot);
//        expectedPivotStateList.add(getTypicalPivot());
//
//        List<String> expectedCommands = new ArrayList<>();
//        expectedCommands.add(INITIAL_COMMAND);
//        expectedCommands.add(testCommand);
//
//        int expectedCurrentStatePointer = 1;
//
//        VersionedPivot expectedVersionedPivot = new VersionedPivot(
//                expectedPivotStateList, expectedCommands, expectedCurrentStatePointer);
//
//        assertEquals(versionedPivot, expectedVersionedPivot);
//    }
//
//    @Test
//    public void commit_nullPivot_throwsNullPointerException() {
//        String testCommand = "Valid Command";
//        assertThrows(NullPointerException.class, () -> versionedPivot.commit(null, testCommand));
//    }
//
//    @Test
//    public void commit_nullString_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> versionedPivot.commit(getTypicalPivot(), null));
//    }
//
//    @Test
//    public void canUndo_atInitialState_returnFalse() {
//        assertFalse(versionedPivot.canUndo());
//    }
//
//    @Test
//    public void canUndo_notAtInitialState_returnTrue() {
//        String testCommand = "Valid Command";
//        versionedPivot.commit(getTypicalPivot(), testCommand);
//        assertTrue(versionedPivot.canUndo());
//    }
//
//    @Test
//    public void undo_undoPreviousState_success() {
//        String testCommand = "Valid Command";
//        versionedPivot.commit(getTypicalPivot(), testCommand);
//
//        // check that previous state is correctly returned
//        ReadOnlyPivot currentState = versionedPivot.undo();
//        assertEquals(emptyPivot, currentState);
//
//        // constructing expected versioned pivot object
//        List<ReadOnlyPivot> expectedPivotStateList = new ArrayList<>();
//        expectedPivotStateList.add(emptyPivot);
//        expectedPivotStateList.add(getTypicalPivot());
//
//        List<String> expectedCommands = new ArrayList<>();
//        expectedCommands.add(INITIAL_COMMAND);
//        expectedCommands.add(testCommand);
//
//        int expectedCurrentStatePointer = INITIAL_STATE;
//
//        VersionedPivot expectedVersionedPivot = new VersionedPivot(
//                expectedPivotStateList, expectedCommands, expectedCurrentStatePointer);
//
//        assertEquals(expectedVersionedPivot, versionedPivot);
//    }
//
//    @Test
//    public void canRedo_atMostRecentState_returnFalse() {
//        assertFalse(versionedPivot.canRedo());
//    }
//
//    @Test
//    public void canRedo_notAtMostRecentState_returnTrue() {
//        String testCommand = "Valid Command";
//        versionedPivot.commit(getTypicalPivot(), testCommand);
//        versionedPivot.undo();
//        assertTrue(versionedPivot.canRedo());
//    }
//
//    @Test
//    public void redo_redoFollowingState_success() {
//        String testCommand = "Valid Command";
//        versionedPivot.commit(getTypicalPivot(), testCommand);
//        versionedPivot.undo();
//
//        // check that following state is correctly returned
//        ReadOnlyPivot currentState = versionedPivot.redo();
//        assertEquals(getTypicalPivot(), currentState);
//
//        // constructing expected versioned pivot object
//        List<ReadOnlyPivot> expectedPivotStateList = new ArrayList<>();
//        expectedPivotStateList.add(emptyPivot);
//        expectedPivotStateList.add(getTypicalPivot());
//
//        List<String> expectedCommands = new ArrayList<>();
//        expectedCommands.add(INITIAL_COMMAND);
//        expectedCommands.add(testCommand);
//
//        int expectedCurrentStatePointer = 1;
//
//        VersionedPivot expectedVersionedPivot = new VersionedPivot(
//                expectedPivotStateList, expectedCommands, expectedCurrentStatePointer);
//
//        assertEquals(expectedVersionedPivot, versionedPivot);
//    }
//
//    @Test
//    public void purgeStates_nothingToPurge_listsUnchanged() {
//        versionedPivot.purgeStates();
//
//        // constructing expected versioned pivot object
//        List<ReadOnlyPivot> expectedPivotStateList = new ArrayList<>();
//        expectedPivotStateList.add(emptyPivot);
//
//        List<String> expectedCommands = new ArrayList<>();
//        expectedCommands.add(INITIAL_COMMAND);
//
//        int expectedCurrentStatePointer = 0;
//
//        VersionedPivot expectedVersionedPivot = new VersionedPivot(
//                expectedPivotStateList, expectedCommands, expectedCurrentStatePointer);
//
//        assertEquals(expectedVersionedPivot, versionedPivot);
//    }
//
//    @Test
//    public void purgeStates_purgeStatesAfterCurrent_listsChanged() {
//        String testCommand = "Valid Command";
//        versionedPivot.commit(getTypicalPivot(), testCommand);
//        versionedPivot.undo();
//        versionedPivot.purgeStates();
//
//        // constructing expected versioned pivot object
//        List<ReadOnlyPivot> expectedPivotStateList = new ArrayList<>();
//        expectedPivotStateList.add(emptyPivot);
//
//        List<String> expectedCommands = new ArrayList<>();
//        expectedCommands.add(INITIAL_COMMAND);
//
//        int expectedCurrentStatePointer = 0;
//
//        VersionedPivot expectedVersionedPivot = new VersionedPivot(
//                expectedPivotStateList, expectedCommands, expectedCurrentStatePointer);
//
//        assertEquals(expectedVersionedPivot, versionedPivot);
//    }
//}
