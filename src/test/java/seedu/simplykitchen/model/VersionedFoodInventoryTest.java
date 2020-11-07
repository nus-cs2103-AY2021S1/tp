package seedu.simplykitchen.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.testutil.Assert.assertThrows;
import static seedu.simplykitchen.testutil.TypicalFood.APPLE_PIE;
import static seedu.simplykitchen.testutil.TypicalFood.BREAD;
import static seedu.simplykitchen.testutil.TypicalFood.CARROT_CAKE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.testutil.FoodInventoryBuilder;

//Solution below adapted from https://github.com/se-edu/addressbook-level4.
public class VersionedFoodInventoryTest {
    private final ReadOnlyFoodInventory foodInventoryWithApplePie = new FoodInventoryBuilder()
            .withFood(APPLE_PIE).build();
    private final ReadOnlyFoodInventory foodInventoryWithBread = new FoodInventoryBuilder().withFood(BREAD).build();
    private final ReadOnlyFoodInventory foodInventoryWithCarrotCake = new FoodInventoryBuilder()
            .withFood(CARROT_CAKE).build();
    private final ReadOnlyFoodInventory emptyFoodInventory = new FoodInventoryBuilder().build();

    @Test
    public void commit_singleFoodInventory_noStatesRemovedCurrentStateSaved() {
        VersionedFoodInventory versionedFoodInventory = prepareFoodInventoryList(emptyFoodInventory);

        versionedFoodInventory.commit();
        assertFoodInventoryListStatus(versionedFoodInventory,
                Collections.singletonList(emptyFoodInventory),
                emptyFoodInventory,
                Collections.emptyList());
    }

    @Test
    public void commit_multipleFoodInventoryPointerAtEndOfStateList_noStatesRemovedCurrentStateSaved() {
        VersionedFoodInventory versionedFoodInventory = prepareFoodInventoryList(
                emptyFoodInventory, foodInventoryWithApplePie, foodInventoryWithBread);

        versionedFoodInventory.commit();
        assertFoodInventoryListStatus(versionedFoodInventory,
                Arrays.asList(emptyFoodInventory, foodInventoryWithApplePie, foodInventoryWithBread),
                foodInventoryWithBread,
                Collections.emptyList());
    }

    @Test
    public void commit_multipleFoodInventoryPointerNotAtEndOfStateList_statesAfterPointerRemovedCurrentStateSaved() {
        VersionedFoodInventory versionedFoodInventory = prepareFoodInventoryList(
                emptyFoodInventory, foodInventoryWithApplePie, foodInventoryWithBread);
        shiftCurrentStatePointerLeftwards(versionedFoodInventory, 2);

        versionedFoodInventory.commit();
        assertFoodInventoryListStatus(versionedFoodInventory,
                Collections.singletonList(emptyFoodInventory),
                emptyFoodInventory,
                Collections.emptyList());
    }

    @Test
    public void canUndo_multipleFoodInventoryPointerAtEndOfStateList_returnsTrue() {
        VersionedFoodInventory versionedFoodInventory = prepareFoodInventoryList(
                emptyFoodInventory, foodInventoryWithApplePie, foodInventoryWithBread);

        assertTrue(versionedFoodInventory.canUndo());
    }

    @Test
    public void canUndo_multipleFoodInventoryPointerAtStartOfStateList_returnsTrue() {
        VersionedFoodInventory versionedFoodInventory = prepareFoodInventoryList(
                emptyFoodInventory, foodInventoryWithApplePie, foodInventoryWithBread);
        shiftCurrentStatePointerLeftwards(versionedFoodInventory, 1);

        assertTrue(versionedFoodInventory.canUndo());
    }

    @Test
    public void canUndo_singleFoodInventory_returnsFalse() {
        VersionedFoodInventory versionedFoodInventory = prepareFoodInventoryList(emptyFoodInventory);

        assertFalse(versionedFoodInventory.canUndo());
    }

    @Test
    public void canUndo_multipleFoodInventoryPointerAtStartOfStateList_returnsFalse() {
        VersionedFoodInventory versionedFoodInventory = prepareFoodInventoryList(
                emptyFoodInventory, foodInventoryWithApplePie, foodInventoryWithBread);
        shiftCurrentStatePointerLeftwards(versionedFoodInventory, 2);

        assertFalse(versionedFoodInventory.canUndo());
    }

    @Test
    public void canRedo_multipleFoodInventoryPointerNotAtEndOfStateList_returnsTrue() {
        VersionedFoodInventory versionedFoodInventory = prepareFoodInventoryList(
                emptyFoodInventory, foodInventoryWithApplePie, foodInventoryWithBread);
        shiftCurrentStatePointerLeftwards(versionedFoodInventory, 1);

        assertTrue(versionedFoodInventory.canRedo());
    }

    @Test
    public void canRedo_multipleFoodInventoryPointerAtStartOfStateList_returnsTrue() {
        VersionedFoodInventory versionedFoodInventory = prepareFoodInventoryList(
                emptyFoodInventory, foodInventoryWithApplePie, foodInventoryWithBread);
        shiftCurrentStatePointerLeftwards(versionedFoodInventory, 2);

        assertTrue(versionedFoodInventory.canRedo());
    }

    @Test
    public void canRedo_singleFoodInventory_returnsFalse() {
        VersionedFoodInventory versionedFoodInventory = prepareFoodInventoryList(emptyFoodInventory);

        assertFalse(versionedFoodInventory.canRedo());
    }

    @Test
    public void canRedo_multipleFoodInventoryPointerAtEndOfStateList_returnsFalse() {
        VersionedFoodInventory versionedFoodInventory = prepareFoodInventoryList(
                emptyFoodInventory, foodInventoryWithApplePie, foodInventoryWithBread);

        assertFalse(versionedFoodInventory.canRedo());
    }

    @Test
    public void undo_multipleFoodInventoryPointerAtEndOfStateList_success() {
        VersionedFoodInventory versionedFoodInventory = prepareFoodInventoryList(
                emptyFoodInventory, foodInventoryWithApplePie, foodInventoryWithBread);

        versionedFoodInventory.undo();
        assertFoodInventoryListStatus(versionedFoodInventory,
                Collections.singletonList(emptyFoodInventory),
                foodInventoryWithApplePie,
                Collections.singletonList(foodInventoryWithBread));
    }

    @Test
    public void undo_multipleFoodInventoryPointerNotAtStartOfStateList_success() {
        VersionedFoodInventory versionedFoodInventory = prepareFoodInventoryList(
                emptyFoodInventory, foodInventoryWithApplePie, foodInventoryWithBread);
        shiftCurrentStatePointerLeftwards(versionedFoodInventory, 1);

        versionedFoodInventory.undo();
        assertFoodInventoryListStatus(versionedFoodInventory,
                Collections.emptyList(),
                emptyFoodInventory,
                Arrays.asList(foodInventoryWithApplePie, foodInventoryWithBread));
    }

    @Test
    public void undo_singleFoodInventory_throwsNoUndoableStateException() {
        VersionedFoodInventory versionedFoodInventory = prepareFoodInventoryList(emptyFoodInventory);

        assertThrows(VersionedFoodInventory.NoUndoableStateException.class, versionedFoodInventory::undo);
    }

    @Test
    public void undo_multipleFoodInventoryPointerAtStartOfStateList_throwsNoUndoableStateException() {
        VersionedFoodInventory versionedFoodInventory = prepareFoodInventoryList(
                emptyFoodInventory, foodInventoryWithApplePie, foodInventoryWithBread);
        shiftCurrentStatePointerLeftwards(versionedFoodInventory, 2);

        assertThrows(VersionedFoodInventory.NoUndoableStateException.class, versionedFoodInventory::undo);
    }

    @Test
    public void redo_multipleFoodInventoryPointerNotAtEndOfStateList_success() {
        VersionedFoodInventory versionedFoodInventory = prepareFoodInventoryList(
                emptyFoodInventory, foodInventoryWithApplePie, foodInventoryWithBread);
        shiftCurrentStatePointerLeftwards(versionedFoodInventory, 1);

        versionedFoodInventory.redo();
        assertFoodInventoryListStatus(versionedFoodInventory,
                Arrays.asList(emptyFoodInventory, foodInventoryWithApplePie),
                foodInventoryWithBread,
                Collections.emptyList());
    }

    @Test
    public void redo_multipleFoodInventoryPointerAtStartOfStateList_success() {
        VersionedFoodInventory versionedFoodInventory = prepareFoodInventoryList(
                emptyFoodInventory, foodInventoryWithApplePie, foodInventoryWithBread);
        shiftCurrentStatePointerLeftwards(versionedFoodInventory, 2);

        versionedFoodInventory.redo();
        assertFoodInventoryListStatus(versionedFoodInventory,
                Collections.singletonList(emptyFoodInventory),
                foodInventoryWithApplePie,
                Collections.singletonList(foodInventoryWithBread));
    }

    @Test
    public void redo_singleFoodInventory_throwsNoRedoableStateException() {
        VersionedFoodInventory versionedFoodInventory = prepareFoodInventoryList(emptyFoodInventory);

        assertThrows(VersionedFoodInventory.NoRedoableStateException.class, versionedFoodInventory::redo);
    }

    @Test
    public void redo_multipleFoodInventoryPointerAtEndOfStateList_throwsNoRedoableStateException() {
        VersionedFoodInventory versionedFoodInventory = prepareFoodInventoryList(
                emptyFoodInventory, foodInventoryWithApplePie, foodInventoryWithBread);

        assertThrows(VersionedFoodInventory.NoRedoableStateException.class, versionedFoodInventory::redo);
    }

    @Test
    public void equals() {
        VersionedFoodInventory versionedFoodInventory =
                prepareFoodInventoryList(foodInventoryWithApplePie, foodInventoryWithBread);

        // same values -> returns true
        VersionedFoodInventory copy = prepareFoodInventoryList(foodInventoryWithApplePie, foodInventoryWithBread);
        assertTrue(versionedFoodInventory.equals(copy));

        // same object -> returns true
        assertTrue(versionedFoodInventory.equals(versionedFoodInventory));

        // null -> returns false
        assertFalse(versionedFoodInventory.equals(null));

        // different types -> returns false
        assertFalse(versionedFoodInventory.equals(1));

        // different state list -> returns false
        VersionedFoodInventory differentFoodInventoryList =
                prepareFoodInventoryList(foodInventoryWithBread, foodInventoryWithCarrotCake);
        assertFalse(versionedFoodInventory.equals(differentFoodInventoryList));

        // different current pointer index -> returns false
        VersionedFoodInventory differentCurrentStatePointer = prepareFoodInventoryList(
                foodInventoryWithApplePie, foodInventoryWithBread);
        shiftCurrentStatePointerLeftwards(versionedFoodInventory, 1);
        assertFalse(versionedFoodInventory.equals(differentCurrentStatePointer));
    }

    /**
     * Asserts that {@code versionedFoodInventory} is currently pointing at {@code expectedCurrentState},
     * states before {@code versionedFoodInventory#currentStatePointer} is equal to {@code expectedStatesBeforePointer},
     * and states after {@code versionedFoodInventory#currentStatePointer} is equal to
     * {@code expectedStatesAfterPointer}.
     */
    private void assertFoodInventoryListStatus(VersionedFoodInventory versionedFoodInventory,
                                             List<ReadOnlyFoodInventory> expectedStatesBeforePointer,
                                             ReadOnlyFoodInventory expectedCurrentState,
                                             List<ReadOnlyFoodInventory> expectedStatesAfterPointer) {
        // check state currently pointing at is correct
        assertEquals(new FoodInventory(versionedFoodInventory), expectedCurrentState);

        // shift pointer to start of state list
        while (versionedFoodInventory.canUndo()) {
            versionedFoodInventory.undo();
        }

        // check states before pointer are correct
        for (ReadOnlyFoodInventory expectedFoodInventory : expectedStatesBeforePointer) {
            assertEquals(expectedFoodInventory, new FoodInventory(versionedFoodInventory));
            versionedFoodInventory.redo();
        }

        // check states after pointer are correct
        for (ReadOnlyFoodInventory expectedFoodInventory : expectedStatesAfterPointer) {
            versionedFoodInventory.redo();
            assertEquals(expectedFoodInventory, new FoodInventory(versionedFoodInventory));
        }

        // check that there are no more states after pointer
        assertFalse(versionedFoodInventory.canRedo());

        // revert pointer to original position
        expectedStatesAfterPointer.forEach(unused -> versionedFoodInventory.undo());
    }

    /**
     * Creates and returns a {@code VersionedFoodInventory} with the {@code foodInventoryStates} added into it, and the
     * {@code VersionedFoodInventory#currentStatePointer} at the end of list.
     */
    private VersionedFoodInventory prepareFoodInventoryList(ReadOnlyFoodInventory... foodInventoryStates) {
        assertFalse(foodInventoryStates.length == 0);

        VersionedFoodInventory versionedFoodInventory = new VersionedFoodInventory(foodInventoryStates[0]);
        for (int i = 1; i < foodInventoryStates.length; i++) {
            versionedFoodInventory.resetData(foodInventoryStates[i]);
            versionedFoodInventory.commit();
        }

        return versionedFoodInventory;
    }

    /**
     * Shifts the {@code versionedFoodInventory#currentStatePointer} by {@code count} to the left of its list.
     */
    private void shiftCurrentStatePointerLeftwards(VersionedFoodInventory versionedFoodInventory, int count) {
        for (int i = 0; i < count; i++) {
            versionedFoodInventory.undo();
        }
    }
}
