package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_BOB;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class RoomMatchesNumberPredicateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RoomMatchesNumberPredicate(null));
    }

    @Test
    public void equals() {
        RoomMatchesNumberPredicate firstPredicate = new RoomMatchesNumberPredicate(VALID_ROOM_NUMBER_AMY);
        RoomMatchesNumberPredicate secondPredicate = new RoomMatchesNumberPredicate(VALID_ROOM_NUMBER_BOB);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RoomMatchesNumberPredicate firstPredicateCopy =
                new RoomMatchesNumberPredicate(VALID_ROOM_NUMBER_AMY);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different floor -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void isValidRoomNumber() {
        // double digit within room range -> returns true
        assertTrue(RoomMatchesNumberPredicate.isValidRoomNumber("10"));
        assertTrue(RoomMatchesNumberPredicate.isValidRoomNumber("20"));
        assertTrue(RoomMatchesNumberPredicate.isValidRoomNumber("01"));

        // double digit out of range -> returns false
        assertFalse(RoomMatchesNumberPredicate.isValidRoomNumber("00"));
        assertFalse(RoomMatchesNumberPredicate.isValidRoomNumber("21"));

        // non-double digits -> returns false
        assertFalse(RoomMatchesNumberPredicate.isValidRoomNumber("100"));
        assertFalse(RoomMatchesNumberPredicate.isValidRoomNumber("1"));
    }

    @Test
    public void test() {
        // same floor -> returns true
        RoomMatchesNumberPredicate predicate = new RoomMatchesNumberPredicate(VALID_ROOM_NUMBER_AMY);
        assertTrue(predicate
                .test(new PersonBuilder().withRoom(VALID_ROOM_AMY).build()));

        // different floor -> returns false
        predicate = new RoomMatchesNumberPredicate(VALID_ROOM_NUMBER_BOB);
        assertFalse(predicate
                .test(new PersonBuilder().withRoom(VALID_ROOM_AMY).build()));
    }
}
