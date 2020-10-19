package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_ADDRESS_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_EMAIL_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_GIT_USERNAME_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_NAME_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_PHONE_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_PHONE_B;
import static seedu.address.testutil.TypicalPersons.DESC_A;
import static seedu.address.testutil.TypicalPersons.DESC_B;

import java.util.Objects;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {




    @Test
    public void execute_isValidFields() {
        String expectedPersonName = VALID_TEAMMATE_NAME_A;
        String expectedGitUserName = VALID_TEAMMATE_GIT_USERNAME_A;
        String expectedPhone = VALID_TEAMMATE_PHONE_A;
        String expectedEmail = VALID_TEAMMATE_EMAIL_A;
        String expectedAddress = VALID_TEAMMATE_ADDRESS_A;

        assertTrue(DESC_A.getPersonName().toString().equals(expectedPersonName));
        assertTrue(DESC_A.getGitUserName().toString().equals(expectedGitUserName));
        assertTrue(DESC_A.getPhone().toString().equals(expectedPhone));
        assertTrue(DESC_A.getEmail().toString().equals(expectedEmail));
        assertTrue(DESC_A.getAddress().toString().equals(expectedAddress));

    }

    @Test
    public void isSameTeamamate() {
        // same object -> return true
        assertTrue(DESC_A.isSameTeammate(DESC_A));

        // null -> return false
        assertFalse(DESC_A.isSameTeammate(null));

        // different personName -> return true
        Person editedDescAPersonName = new PersonBuilder(DESC_A).withPersonName("luanqiba").build();
        assertTrue(DESC_A.isSameTeammate(editedDescAPersonName));

        // different gitUserName -> return false
        Person editedDescAGitUserName = new PersonBuilder(DESC_A).withGitUserName("different").build();
        assertFalse(DESC_A.isSameTeammate(editedDescAGitUserName));

        // different phone -> return false
        Person editedDescAPhone = new PersonBuilder(DESC_A).withPhone("38283828311").build();
        assertFalse(DESC_A.isSameTeammate(editedDescAPhone));

        // different email -> return false
        Person editedDescAEmail = new PersonBuilder(DESC_A).withEmail("jdjf@hmail.com").build();
        assertFalse(DESC_A.isSameTeammate(editedDescAEmail));

        // different address -> return true
        Person editedDescAAddress = new PersonBuilder(DESC_A).withAddress("timbuktu").build();
        assertTrue(DESC_A.isSameTeammate(editedDescAAddress));

    }

    @Test
    public void execute_validHashCode() {
        int expectedHash = Objects.hash(VALID_TEAMMATE_NAME_A, VALID_TEAMMATE_GIT_USERNAME_A, VALID_TEAMMATE_PHONE_A,
            VALID_TEAMMATE_EMAIL_A, VALID_TEAMMATE_ADDRESS_A);

        int resultHash = DESC_A.hashCode();

        assertTrue(resultHash == expectedHash);
    }

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

    @Test
    public void execute_validToString() {
        String expectedString = " Person name: Jack Nicholson"
            + " Git Username: Sparrow32"
            + " Phone: 92883923"
            + " Email: jack@gmail.com"
            + " Address: 32 Lake Road";

        String resultString = DESC_A.toString();

        assertTrue(expectedString.equals(resultString));
    }

}
