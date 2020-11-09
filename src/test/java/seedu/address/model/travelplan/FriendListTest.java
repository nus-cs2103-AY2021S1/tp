package seedu.address.model.travelplan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.ConstructorUtils.VALID_NAME_B;
import static seedu.address.testutil.typicals.TypicalFriends.ALICE;
import static seedu.address.testutil.typicals.TypicalFriends.getTypicalFriendList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.exceptions.DuplicateFriendException;
import seedu.address.testutil.builders.FriendBuilder;

public class FriendListTest {

    private final FriendList friendList = new FriendList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), friendList.getObservableFriendList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> friendList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFriendList_replacesData() {
        FriendList newData = getTypicalFriendList(1);
        friendList.resetData(newData);
        assertEquals(newData, friendList);
    }

    @Test
    public void resetData_withDuplicateFriends_throwsDuplicateFriendException() {
        // Two friends with the same identity fields
        Friend editedAlice = new FriendBuilder(ALICE).withName(VALID_NAME_B).build();
        List<Friend> newFriends = Arrays.asList(ALICE, editedAlice);
        FriendListStub newData = new FriendListStub(newFriends);

        assertThrows(DuplicateFriendException.class, () -> friendList.resetData(newData));
    }

    @Test
    public void hasFriend_nullFriend_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> friendList.hasFriend(null));
    }

    @Test
    public void hasFriend_friendNotInFriendList_returnsFalse() {
        assertFalse(friendList.hasFriend(ALICE));
    }

    @Test
    public void hasFriend_friendInFriendList_returnsTrue() {
        friendList.addFriend(ALICE);
        assertTrue(friendList.hasFriend(ALICE));
    }

    @Test
    public void hasFriend_friendWithSameIdentityFieldsInFriendList_returnsTrue() {
        friendList.addFriend(ALICE);
        Friend editedAlice = new FriendBuilder(ALICE).withName(VALID_NAME_B).build();
        assertTrue(friendList.hasFriend(editedAlice));
    }

    @Test
    public void getFriendList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> friendList.getObservableFriendList().remove(0));
    }

    /**
     * TODO
     * A stub ReadOnlyFriendList whose friends list can violate interface constraints.
     */
    private static class FriendListStub extends FriendList {
        private final ObservableList<Friend> friends = FXCollections.observableArrayList();

        FriendListStub(Collection<Friend> friends) {
            this.friends.setAll(friends);
        }

        public ObservableList<Friend> getObservableFriendList() {
            return friends;
        }
    }

}
