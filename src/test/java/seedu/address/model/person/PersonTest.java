package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getClientSources().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // different phone and email -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // different name -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withClientSources(VALID_CLIENTSOURCE_HUSBAND).withNote(VALID_NOTE_DOG).addToArchive().build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // same name, same email, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB)
                .withClientSources(VALID_CLIENTSOURCE_HUSBAND).withNote(VALID_NOTE_DOG).addToArchive().build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // same name, same phone, same email, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withClientSources(VALID_CLIENTSOURCE_HUSBAND)
                .withNote(VALID_NOTE_DOG).addToArchive().build();
        assertTrue(ALICE.isSamePerson(editedAlice));


        // null phone and email test cases start here
        Person aliceWithoutPhone = new PersonBuilder(ALICE).withoutPhone().build();
        Person aliceWithoutEmail = new PersonBuilder(ALICE).withoutEmail().build();
        Person aliceWithoutPhoneAndEmail = new PersonBuilder(ALICE).withoutPhone().withoutEmail().build();

        // same name, null phone, null email, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE).withoutPhone().withoutEmail().withAddress(VALID_ADDRESS_BOB)
                .withClientSources(VALID_CLIENTSOURCE_HUSBAND).withNote(VALID_NOTE_DOG).addToArchive().build();
        assertTrue(aliceWithoutPhoneAndEmail.isSamePerson(editedAlice));

        // same name, null phone, null and non-null email -> returns false
        editedAlice = new PersonBuilder(ALICE).withoutPhone().withoutEmail().build();
        assertFalse(aliceWithoutPhone.isSamePerson(editedAlice));

        // same name, null phone, same email, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE).withoutPhone().withAddress(VALID_ADDRESS_BOB)
                .withClientSources(VALID_CLIENTSOURCE_HUSBAND).withNote(VALID_NOTE_DOG).addToArchive().build();
        assertTrue(aliceWithoutPhone.isSamePerson(editedAlice));

        // same name, null phone, different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withoutPhone().withEmail(VALID_EMAIL_BOB).build();
        assertFalse(aliceWithoutPhone.isSamePerson(editedAlice));

        // same name, null and non-null phone, null email -> returns false
        editedAlice = new PersonBuilder(ALICE).withoutPhone().withoutEmail().build();
        assertFalse(aliceWithoutEmail.isSamePerson(editedAlice));

        // same name, null and non-null phone, null and non-null email -> returns false
        editedAlice = new PersonBuilder(ALICE).withoutPhone().withoutEmail().build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // same name, null and non-null phone, same email, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE).withoutPhone().withAddress(VALID_ADDRESS_BOB)
                .withClientSources(VALID_CLIENTSOURCE_HUSBAND).withNote(VALID_NOTE_DOG).addToArchive().build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // same name, null and non-null phone, different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withoutPhone().withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // same name, same phone, null email, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE).withoutEmail().withAddress(VALID_ADDRESS_BOB)
                .withClientSources(VALID_CLIENTSOURCE_HUSBAND).withNote(VALID_NOTE_DOG).addToArchive().build();
        assertTrue(aliceWithoutEmail.isSamePerson(editedAlice));

        // same name, same phone, null and non-null email, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE).withoutEmail().withAddress(VALID_ADDRESS_BOB)
                .withClientSources(VALID_CLIENTSOURCE_HUSBAND).withNote(VALID_NOTE_DOG).addToArchive().build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // same name, different phone, null email -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withoutEmail().build();
        assertFalse(aliceWithoutEmail.isSamePerson(editedAlice));

        // same name, different phone, null and non-null email -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withoutEmail().build();
        assertFalse(ALICE.isSamePerson(editedAlice));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
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
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different clientSources -> returns false
        editedAlice = new PersonBuilder(ALICE).withClientSources(VALID_CLIENTSOURCE_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        // different note -> returns false
        editedAlice = new PersonBuilder(ALICE).withNote(VALID_NOTE_DOG).build();
        assertFalse(ALICE.equals(editedAlice));

        // different policy -> returns false
        editedAlice = new PersonBuilder(ALICE).withPolicy(VALID_POLICY_NAME_BOB, VALID_POLICY_DESCRIPTION_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
