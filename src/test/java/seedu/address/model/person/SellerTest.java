package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.seller.TypicalSeller.ALICE;
import static seedu.address.testutil.seller.TypicalSeller.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.seller.Seller;
import seedu.address.testutil.seller.SellerBuilder;

public class SellerTest {


    @Test
    public void isSameSeller() {
        // same object -> returns true
        assertTrue(ALICE.isSameSeller(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameSeller(null));

        // different phone but same name -> returns true
        Seller editedAlice = new SellerBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertTrue(ALICE.isSameSeller(editedAlice));

        // different name but same phone -> returns true
        editedAlice = new SellerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSameSeller(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new SellerBuilder(ALICE).build();
        assertTrue(ALICE.isSameSeller(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new SellerBuilder(ALICE).build();
        assertTrue(ALICE.isSameSeller(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Seller aliceCopy = new SellerBuilder(ALICE).build();
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
        Seller editedAlice = new SellerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new SellerBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
