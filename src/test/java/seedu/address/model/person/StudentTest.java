package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.StudentBuilder;

public class StudentTest {
    Student amy = new Student(new Name(VALID_NAME_AMY), new Phone(VALID_PHONE_AMY),
            new Email(VALID_EMAIL_AMY), SampleDataUtil.getTagSet(), new StudentId(VALID_STUDENT_ID_AMY));

    Student bob = new Student(new Name(VALID_NAME_BOB), new Phone(VALID_PHONE_BOB),
            new Email(VALID_EMAIL_BOB), SampleDataUtil.getTagSet(), new StudentId(VALID_STUDENT_ID_BOB));

    @Test
    public void equals() {
        // same values -> returns true
        Student amyCopy = new StudentBuilder(amy).build();
        assertTrue(amy.equals(amyCopy));

        // same object -> returns true
        assertTrue(amy.equals(amy));

        // null -> returns false
        assertFalse(amy.equals(null));

        // different type -> returns false
        assertFalse(amy.equals(5));

        // different person -> returns false
        assertFalse(amy.equals(bob));

        // different name -> returns false
        Student editedAmy = new StudentBuilder(amy).withName(VALID_NAME_BOB).build();
        assertFalse(amy.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new StudentBuilder(amy).withPhone(VALID_PHONE_BOB).build();
        assertFalse(amy.equals(editedAmy));

        // different email -> returns false
        editedAmy = new StudentBuilder(amy).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(amy.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new StudentBuilder(amy).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(amy.equals(editedAmy));
    }
}
