package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.BidderAddressBook;
import seedu.address.model.person.bidder.Bidder;



/**
 * A utility class containing a list of {@code Bidder} objects to be used in tests.
 */
public class TypicalBidder {

    public static final Bidder ALICE = new BidderBuilder().withName("Alice Pauline")
            .withPhone("94351253").withId("B", 1)
            .withTags("friends").build();
    public static final Bidder BENSON = new BidderBuilder().withName("Benson Meier")
            .withTags("owesMoney", "friends").withId("B", 2).build();
    public static final Bidder CARL = new BidderBuilder().withName("Carl Kurz")
            .withPhone("95352563").withPhone("9482442").withId("B", 3).build();
    public static final Bidder DANIEL = new BidderBuilder().withName("Daniel Meier")
            .withPhone("87652533").withTags("friends").withId("B", 4).build();
    public static final Bidder ELLE = new BidderBuilder().withName("Elle Meyer")
            .withPhone("9482224").withId("B", 5)
            .build();
    public static final Bidder FIONA = new BidderBuilder().withName("Fiona Kunz")
            .withPhone("9482427").withId("B", 6)
            .build();
    public static final Bidder GEORGE = new BidderBuilder()
            .withName("George Best").withPhone("9482442")
            .withId("B", 7)
            .build();

    // Manually added - Bidder's details found in {@code CommandTestUtil}
    public static final Bidder AMY = new BidderBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withTags(VALID_TAG_FRIEND).withId("B", 12)
            .build();
    public static final Bidder BOB = new BidderBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withId("B", 13)
            .build();

    /**
     * Returns an {@code BidderAddressBook} with all the typical persons.
     */
    public static BidderAddressBook getTypicalAddressBook() {
        BidderAddressBook ab = new BidderAddressBook();
        for (Bidder bidder : getTypicalBidders()) {
            ab.addBidder(bidder);
        }
        return ab;
    }

    public static List<Bidder> getTypicalBidders() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
