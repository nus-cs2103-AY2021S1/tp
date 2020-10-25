package seedu.address.model.vendor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalVendors.ALICE;
import static seedu.address.testutil.TypicalVendors.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.VendorBuilder;

public class VendorTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Vendor vendor = new VendorBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> vendor.getTags().remove(0));
    }

    @Test
    public void isSameVendor() {
        // same object -> returns true
        assertTrue(ALICE.isSameVendor(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameVendor(null));

        // different phone and email -> returns false
        Vendor editedAlice = new VendorBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSameVendor(editedAlice));

        // different name -> returns false
        editedAlice = new VendorBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameVendor(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new VendorBuilder(ALICE).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameVendor(editedAlice));

        // same name, same email, different attributes -> returns true
        editedAlice = new VendorBuilder(ALICE).withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameVendor(editedAlice));

        // same name, same phone, same email, different attributes -> returns true
        editedAlice = new VendorBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameVendor(editedAlice));
    }

    //TODO: pass
    @Test
    public void equals() {
        // same values -> returns true
        Vendor aliceCopy = new VendorBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different vendor -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Vendor editedAlice = new VendorBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new VendorBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new VendorBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new VendorBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new VendorBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
