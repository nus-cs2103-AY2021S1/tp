package seedu.address.model.profile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Phone;

public class ProfileTest {

    @Test
    public void equals() {
        Phone phone1 = new Phone("66666666");
        Address address1 = new Address("123 Blk street");
        Profile profile1 = new Profile(phone1, address1);
        Profile sameProfile1 = new Profile(new Phone("66666666"), new Address("123 Blk street"));
        Profile differentProfile = new Profile(new Phone("99999999"), new Address("125 Block Streetz"));
        Profile differentProfilePhone = new Profile(new Phone("66666667"), address1);
        Profile differentProfileAddress = new Profile(phone1, new Address("123 Blk streets"));

        // same values -> returns true
        assertTrue(profile1.equals(sameProfile1));

        // same object -> returns true
        assertTrue(profile1.equals(profile1));

        // null -> returns false
        assertFalse(profile1.equals(null));

        // different type -> returns false
        assertFalse(profile1.equals(5));

        // different profile -> returns false
        assertFalse(profile1.equals(differentProfile));
        // different phone -> returns false
        assertFalse(profile1.equals(differentProfilePhone));

        // different address -> returns false
        assertFalse(profile1.equals(differentProfileAddress));
    }
}
