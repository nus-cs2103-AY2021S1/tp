package seedu.address.model.module.gradetracker;

import org.junit.jupiter.api.Test;
import seedu.address.model.contact.Contact;
import seedu.address.model.module.grade.Assignment;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.testutil.ContactBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.ALICE;
import static seedu.address.testutil.TypicalContacts.BOB;

public class AssignmentTest {

    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        AssignmentBuilder assignmentBuilder = new AssignmentBuilder();

        // null AssignmentName -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> assignmentBuilder.withAssignmentName(null).build());
    }

    @Test
    public void isSameAssignment() {
        // same object -> returns true
        assertTrue(.isSameContact(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameContact(null));

        // same name, different telegram and email -> returns false
        Contact editedAlice = new ContactBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .withTelegram(VALID_TELEGRAM_AMY).build();
        assertFalse(ALICE.isSameContact(editedAlice));

        // different name -> returns false
        editedAlice = new ContactBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameContact(editedAlice));

        // same name, same email, different attributes -> returns true
        editedAlice = new ContactBuilder(ALICE).withTelegram(VALID_TELEGRAM_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        assertTrue(ALICE.isSameContact(editedAlice));

        // same name, same telegram, different email -> returns false
        editedAlice = new ContactBuilder(ALICE).withEmail(VALID_EMAIL_AMY).build();
        assertFalse(ALICE.isSameContact(editedAlice));

        // same name, same email, same telegram, different tags -> returns true
        editedAlice = new ContactBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameContact(editedAlice));
    }

    @Test
    public void markAsImportant_validContact_success() {
        Contact contact = new ContactBuilder().build();
        assertTrue(contact.markAsImportant().isImportant());
    }

    @Test
    public void markAsNotImportant_validContact_success() {
        Contact contact = new ContactBuilder().build();
        assertFalse(contact.markAsNotImportant().isImportant());
    }

    @Test
    public void equals() {
        // same values -> returns true
        Contact aliceCopy = new ContactBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(10));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Contact editedAlice = new ContactBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ContactBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different telegram -> returns false
        editedAlice = new ContactBuilder(ALICE).withTelegram(VALID_TELEGRAM_AMY).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ContactBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
