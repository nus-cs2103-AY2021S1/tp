package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.DESC_A;
import static seedu.address.testutil.TypicalPersons.DESC_B;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(DESC_A.equals(DESC_A));

        // null -> returns false
        assertFalse(DESC_A.equals(null));

        // same values, different objects -> return true
        Person descACopy = new PersonBuilder(DESC_A).build();
        assertTrue(DESC_A.equals(descACopy));

        // different type -> return false
        assertFalse(DESC_A.equals(5));

        // same type, different objects -> return false
        assertFalse(DESC_A.equals(DESC_B));

        // different PersonName
        Person editedDescAPersonName = new PersonBuilder(DESC_A).withPersonName("Niaaz").build();
        assertFalse(DESC_A.equals(editedDescAPersonName));

        // different gitUserName
        Person editedDescAGitUserName = new PersonBuilder(DESC_A).withGitUserName("Geniaaz982").build();
        assertFalse(DESC_A.equals(editedDescAGitUserName));

        // different phone
        Person editedDescAPhone = new PersonBuilder(DESC_A).withPhone("3728271").build();
        assertFalse(DESC_A.equals(editedDescAPhone));

        // different email
        Person editedDescAEmail = new PersonBuilder(DESC_A).withEmail("pb@gmail.com").build();
        assertFalse(DESC_A.equals(editedDescAEmail));

        // different address
        Person editedDescAAddress = new PersonBuilder(DESC_A).withAddress("247 pasir ris").build();
        assertFalse(DESC_A.equals(editedDescAAddress));
    }


}
