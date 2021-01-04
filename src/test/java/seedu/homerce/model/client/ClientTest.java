package seedu.homerce.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.client.TypicalClients.ALICE;
import static seedu.homerce.testutil.client.TypicalClients.BOB;

import org.junit.jupiter.api.Test;

import seedu.homerce.testutil.client.ClientBuilder;

public class ClientTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Client client = new ClientBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> client.getTags().remove(0));
    }

    @Test
    public void isSame() {
        // same object -> returns true
        assertTrue(ALICE.isSame(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSame(null));

        // different phone and email -> returns false
        Client editedAlice = new ClientBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSame(editedAlice));

        // different name -> returns true as uniqueness is determined by phone number.
        editedAlice = new ClientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSame(editedAlice));

        // same name, same phone, different tags -> returns true
        editedAlice = new ClientBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSame(editedAlice));

        // same name, same email, different tags -> returns false as uniqueness is determined by phone number.
        editedAlice = new ClientBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.isSame(editedAlice));

        // same name, same phone, same email, different tags -> returns true
        editedAlice = new ClientBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSame(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Client aliceCopy = new ClientBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different client -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Client editedAlice = new ClientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ClientBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ClientBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ClientBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void getName() {
        assertEquals(ALICE.getName(), new Name("Alice Pauline"));

        assertNotEquals(ALICE.getName(), new Name("Alice"));
        assertNotEquals(ALICE.getName(), new Name("Alice Paul"));

    }

    @Test
    public void getPhone() {
        Client editedAlice = new ClientBuilder(ALICE).withPhone("91234567").build();

        assertEquals(ALICE.getPhone(), new Phone("94351253"));
        assertEquals(new Phone("91234567"), editedAlice.getPhone());

        assertNotEquals(ALICE.getPhone(), new Phone("9123123"));

    }

    @Test
    public void getEmail() {
        assertEquals(ALICE.getEmail(), new Email("alice@example.com"));

        assertNotEquals(ALICE.getEmail(), new Email("alice@gmail.com"));
    }

    @Test
    public void testHashCode() {
        Client editedAlice = new ClientBuilder(ALICE).withPhone("91234567").build();

        assertEquals(ALICE.hashCode(), ALICE.hashCode());
        assertNotEquals(ALICE.hashCode(), BOB.hashCode());

        assertNotEquals(ALICE.hashCode(), editedAlice.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals(ALICE.toString(), "Alice Pauline Phone: 94351253 Email: alice@example.com Tags: [friends]");

        assertNotEquals(ALICE.toString(), "Alice Pauline");
        assertNotEquals(ALICE.toString(), BOB.toString());
    }

}
