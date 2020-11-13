package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    public void optionalConstructor_invalidContacts_throwsIllegalArgumentException() {
        String invalidContacts = "123";
        assertThrows(IllegalArgumentException.class, () -> new Contacts(Optional.of(invalidContacts)));
    }

    @Test
    public void stringConstructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidContacts = "123";
        assertThrows(IllegalArgumentException.class, () -> new Contacts(invalidContacts));
    }

    @Test
    public void isValidContacts() {
        // Empty -> true
        assertTrue(Contacts.isValidContacts(""));

        // One name -> true
        assertTrue(Contacts.isValidContacts("James"));

        // Comma-separated names -> true
        assertTrue(Contacts.isValidContacts("James, Eric, John"));

        // Number -> false
        assertFalse(Contacts.isValidContacts("123"));

        // Special characters -> false
        assertFalse(Contacts.isValidContacts("!@#$,.?"));

        // names separated by random character -> false
        assertFalse(Contacts.isValidContacts("123"));

    }

    @Test
    public void contactsToString() {
        // Empty contacts -> "-"
        Contacts emptyContacts = new Contacts("");
        assertEquals(emptyContacts.toString(), "-");

        // Non-empty contacts -> comma-separated names
        Contacts contacts = new Contacts("James, Eric, Ben");
        assertEquals(contacts.toString(), "James, Eric, Ben");

    }

}
