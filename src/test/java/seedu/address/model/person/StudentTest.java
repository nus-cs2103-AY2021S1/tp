package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BENG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BENG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BENG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BENG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALEX;
import static seedu.address.testutil.TypicalStudents.BENG;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationsException() {
        Student student = new StudentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> student.getTags().remove(0));
    }

    @Test
    public void isSameStudent() {
        // same object -> returns true
        assertTrue(ALEX.isSame(ALEX));

        // null -> returns false
        assertFalse(ALEX.isSame(null));

        // students with same id are considered the same student
        // different phone and email -> returns true
        Student editedAlex = new StudentBuilder(ALEX).withPhone(VALID_PHONE_BENG).withEmail(VALID_EMAIL_BENG).build();
        assertTrue(ALEX.isSame(editedAlex));

        // different name -> returns true
        editedAlex = new StudentBuilder(ALEX).withName(VALID_NAME_BENG).build();
        assertTrue(ALEX.isSame(editedAlex));

        // different student id -> returns false
        editedAlex = new StudentBuilder(ALEX).withStudentId(VALID_STUDENT_ID_BENG).build();
        assertFalse(ALEX.isSame(editedAlex));

        // same name, same phone, same id, different attributes -> returns true
        editedAlex = new StudentBuilder(ALEX).withEmail(VALID_EMAIL_BENG)
                .withTags(VALID_TAG_FRIEND).build();
        assertTrue(ALEX.isSame(editedAlex));

        // same name, same email, same id, different attributes -> returns true
        editedAlex = new StudentBuilder(ALEX).withPhone(VALID_PHONE_BENG)
                .withTags(VALID_TAG_FRIEND).build();
        assertTrue(ALEX.isSame(editedAlex));

        // same name, same phone, same email, same id, different attributes -> returns true
        editedAlex = new StudentBuilder(ALEX).withTags(VALID_TAG_FRIEND).build();
        assertTrue(ALEX.isSame(editedAlex));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student alexCopy = new StudentBuilder(ALEX).build();
        assertTrue(ALEX.equals(alexCopy));

        // same object -> returns true
        assertTrue(ALEX.equals(ALEX));

        // null -> returns false
        assertFalse(ALEX.equals(null));

        // different type -> returns false
        assertFalse(ALEX.equals(5));

        // different student -> returns false
        assertFalse(ALEX.equals(BENG));

        // different name -> returns false
        Student editedAlex = new StudentBuilder(ALEX).withName(VALID_NAME_BENG).build();
        assertFalse(ALEX.equals(editedAlex));

        // different phone -> returns false
        editedAlex = new StudentBuilder(ALEX).withPhone(VALID_PHONE_BENG).build();
        assertFalse(ALEX.equals(editedAlex));

        // different email -> returns false
        editedAlex = new StudentBuilder(ALEX).withEmail(VALID_EMAIL_BENG).build();
        assertFalse(ALEX.equals(editedAlex));

        // different tags -> returns false
        editedAlex = new StudentBuilder(ALEX).withTags(VALID_TAG_FRIEND).build();
        assertFalse(ALEX.equals(editedAlex));
    }
}
