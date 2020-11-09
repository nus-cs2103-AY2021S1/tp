package seedu.address.testutil.typicals;

import static seedu.address.logic.command.CommandTestUtil.VALID_MOBILE_AMY;
import static seedu.address.logic.command.CommandTestUtil.VALID_MOBILE_BOB;
import static seedu.address.logic.command.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.command.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.command.CommandTestUtil.VALID_PASSPORT_AMY;
import static seedu.address.logic.command.CommandTestUtil.VALID_PASSPORT_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.friend.Friend;
import seedu.address.model.travelplan.FriendList;
import seedu.address.testutil.builders.FriendBuilder;

/**
 * A utility class containing a list of {@code Friend} objects to be used in tests.
 */
public class TypicalFriends {

    //Friend
    public static final Friend ALICE = new FriendBuilder().withName("Alice Pauline")
            .withPassport("E1234567A")
            .withMobile("94351253").build();
    public static final Friend BENSON = new FriendBuilder().withName("Benson Meier")
            .withPassport("E1234567B")
            .withMobile("98765432").build();
    public static final Friend CARL = new FriendBuilder().withName("Carl Kurz")
            .withPassport("E1234567C")
            .withMobile("95352563").build();
    public static final Friend DANIEL = new FriendBuilder().withName("Daniel Meier")
            .withPassport("E1234567D")
            .withMobile("87652533").build();

    public static final Friend ELLE = new FriendBuilder().withName("Elle Meyer")
            .withPassport("E1234567E")
            .withMobile("94822241").build();
    public static final Friend FIONA = new FriendBuilder().withName("Fiona Kunz")
            .withPassport("E7654321F")
            .withMobile("94822427").build();
    public static final Friend GEORGE = new FriendBuilder().withName("George Best")
            .withPassport("E7654321G")
            .withMobile("94824142").build();
    public static final Friend HOON = new FriendBuilder().withName("Hoon Meier")
            .withPassport("E7654321H")
            .withMobile("84824224").build();
    public static final Friend IDA = new FriendBuilder().withName("Ida Mueller")
            .withPassport("E7654321I")
            .withMobile("84821631").build();

    // Manually added - Friends' details found in {@code CommandTestUtil}
    public static final Friend AMY = new FriendBuilder().withName(VALID_NAME_AMY)
            .withMobile(VALID_MOBILE_AMY).withPassport(VALID_PASSPORT_AMY).build();

    public static final Friend BOB = new FriendBuilder().withName(VALID_NAME_BOB)
            .withMobile(VALID_MOBILE_BOB).withPassport(VALID_PASSPORT_BOB).build();


    private TypicalFriends() {} // prevents instantiation

    /**
     * Returns a {@code FriendList} with a set of typical friends (either set 1 or 2).
     */
    public static FriendList getTypicalFriendList(int set) {
        if (set != 1 && set != 2) {
            throw new IllegalArgumentException("getTypicalFriendList only takes in set 1 or 2 as argument.");
        }
        List<Friend> friends = set == 1 ? getTypicalFriends1() : getTypicalFriends2();
        FriendList fl = new FriendList();
        for (Friend friend : friends) {
            fl.addFriend(new FriendBuilder(friend).build());
        }
        return fl;
    }

    public static List<Friend> getTypicalFriends1() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL));
    }

    public static List<Friend> getTypicalFriends2() {
        return new ArrayList<>(Arrays.asList(ELLE, FIONA, GEORGE, HOON, IDA));
    }

}
