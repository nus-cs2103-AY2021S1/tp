package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.bidder.TypicalBidder.ALICE;
import static seedu.address.testutil.bidder.TypicalBidder.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.bidder.Bidder;
import seedu.address.testutil.bidder.BidderBuilder;

public class BidderTest {

    @Test
    public void isSameBidder() {
        // same object -> returns true
        assertTrue(ALICE.isSameBidder(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameBidder(null));

        // different phone but same name -> returns true
        Bidder editedAlice = new BidderBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertTrue(ALICE.isSameBidder(editedAlice));

        // different name but same phone -> returns true
        editedAlice = new BidderBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSameBidder(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new BidderBuilder(ALICE).build();
        assertTrue(ALICE.isSameBidder(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new BidderBuilder(ALICE).build();
        assertTrue(ALICE.isSameBidder(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Bidder aliceCopy = new BidderBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Bidder editedAlice = new BidderBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new BidderBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

    }
}
