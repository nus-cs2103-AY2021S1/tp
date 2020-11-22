package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FLOOR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FLOOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_AMY;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;


public class RoomInFloorPredicateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RoomInFloorPredicate(null));
    }

    @Test
    public void equals() {
        RoomInFloorPredicate firstPredicate = new RoomInFloorPredicate(VALID_FLOOR_AMY);
        RoomInFloorPredicate secondPredicate = new RoomInFloorPredicate(VALID_FLOOR_BOB);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RoomInFloorPredicate firstPredicateCopy =
                new RoomInFloorPredicate(VALID_FLOOR_AMY);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different floor -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void isValidFloorNumber() {
        // single digit within range -> returns true
        assertTrue(RoomInFloorPredicate.isValidFloorNumber("1"));
        assertTrue(RoomInFloorPredicate.isValidFloorNumber("2"));
        assertTrue(RoomInFloorPredicate.isValidFloorNumber("4"));

        // single digit out of range -> returns false
        assertFalse(RoomInFloorPredicate.isValidFloorNumber("9"));
        assertFalse(RoomInFloorPredicate.isValidFloorNumber("5"));

        // multiple digits -> returns false
        assertFalse(RoomInFloorPredicate.isValidFloorNumber("100"));
        assertFalse(RoomInFloorPredicate.isValidFloorNumber("10"));
    }

    @Test
    public void test() {
        // same floor -> returns true
        RoomInFloorPredicate predicate = new RoomInFloorPredicate(VALID_FLOOR_AMY);
        assertTrue(predicate
                .test(new PersonBuilder().withRoom(VALID_ROOM_AMY).build()));

        // different floor -> returns false
        predicate = new RoomInFloorPredicate(VALID_FLOOR_BOB);
        assertFalse(predicate
                .test(new PersonBuilder().withRoom(VALID_ROOM_AMY).build()));
    }
}
