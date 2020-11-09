package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.command.CommandTestUtil.VALID_MOBILE_AMY;
import static seedu.address.logic.command.CommandTestUtil.VALID_MOBILE_BOB;
import static seedu.address.logic.command.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.command.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.command.CommandTestUtil.VALID_PASSPORT_AMY;
import static seedu.address.logic.command.CommandTestUtil.VALID_PASSPORT_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.commons.Name;
import seedu.address.testutil.builders.FriendBuilder;
import seedu.address.testutil.typicals.TypicalFriends;

class FriendTest {

    private final Friend testFriend = TypicalFriends.AMY; //test toString and public getter methods with valid Friend

    // should return true if identity field (passport) is the same
    @Test
    public void isSameFriend() {
        // same object -> returns true
        assertTrue(TypicalFriends.ALICE.isSameFriend(TypicalFriends.ALICE));

        // null -> returns false
        assertFalse(TypicalFriends.ALICE.isSameFriend(null));
        Friend editedAlice;

        // different passport -> returns false
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).withPassport(VALID_PASSPORT_BOB).build();
        assertFalse(TypicalFriends.ALICE.isSameFriend(editedAlice));

        // same name with different mobile and passport -> returns false
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).withMobile(VALID_MOBILE_BOB)
                .withPassport(VALID_PASSPORT_BOB).build();

        assertFalse(TypicalFriends.ALICE.isSameFriend(editedAlice));

        // same name, same passport with different mobile -> returns true
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).withMobile(VALID_MOBILE_BOB).build();

        assertTrue(TypicalFriends.ALICE.isSameFriend(editedAlice));

        // same name, same mobile, different passport -> returns false
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).withPassport(VALID_PASSPORT_BOB).build();

        assertFalse(TypicalFriends.ALICE.isSameFriend(editedAlice));

        // different name, different mobile, same passport -> returns true
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).withName(VALID_NAME_BOB).withMobile(VALID_MOBILE_BOB)
                .build();

        assertTrue(TypicalFriends.ALICE.isSameFriend(editedAlice));

        // same name, same mobile, same passport -> returns true
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).build();
        assertTrue(TypicalFriends.ALICE.isSameFriend(editedAlice));
    }

    //test if two friends have the same identity and data fields
    @Test
    public void equals() {

        // same object -> returns true
        assertEquals(TypicalFriends.ALICE, TypicalFriends.ALICE);

        // null -> returns false
        assertNotEquals(null, TypicalFriends.ALICE);
        Friend editedAlice;

        // different name -> returns false
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).withName(VALID_NAME_BOB).build();
        assertNotEquals(TypicalFriends.ALICE, editedAlice);

        // same name with different mobile and passport -> returns false
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).withMobile(VALID_MOBILE_BOB)
                .withPassport(VALID_PASSPORT_BOB).build();

        assertNotEquals(TypicalFriends.ALICE, editedAlice);

        // same name, same passport with different mobile -> returns false
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).withMobile(VALID_MOBILE_BOB).build();

        assertNotEquals(TypicalFriends.ALICE, editedAlice);

        // same name, same mobile, different passport -> returns false
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).withPassport(VALID_PASSPORT_BOB).build();

        assertNotEquals(TypicalFriends.ALICE, editedAlice);

        // same name, same mobile, same passport -> returns true
        editedAlice = new FriendBuilder(TypicalFriends.ALICE).build();
        assertEquals(TypicalFriends.ALICE, editedAlice);

    }

    @Test
    void testToString() {
        String expected = "Amy Choo\n"
                + "Passport number: E1234567T\n"
                + "Mobile phone: 81234567\n";

        assertEquals(testFriend.toString(), expected);
    }

    @Test
    void getNameAsString() {
        assertEquals("Amy Choo", testFriend.getNameAsString());
    }

    @Test
    void getName() {
        Name compare = new Name(VALID_NAME_AMY);
        assertEquals(compare, testFriend.getName());
    }

    @Test
    void getPassport() {
        Passport compare = new Passport(VALID_PASSPORT_AMY);
        assertEquals(compare, testFriend.getPassport());
    }

    @Test
    void getMobile() {
        Mobile compare = new Mobile(VALID_MOBILE_AMY);
        assertEquals(compare, testFriend.getMobile());
    }
}
