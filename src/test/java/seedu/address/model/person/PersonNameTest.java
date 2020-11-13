package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PersonNameTest {

    @Test
    public void construtor_null_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonName(null));
    }

    @Test
    public void constructor_invalidPersonName_throwsIllegalArgumentException() {
        String invalidName = " James";
        assertThrows(IllegalArgumentException.class, () -> new PersonName(invalidName));
    }

    @Test
    public void isValidPersonName() {
        // null
        assertThrows(NullPointerException.class, () -> PersonName.isValidPersonName(null));

        // invalid person name
        assertFalse(PersonName.isValidPersonName("")); // empty string
        assertFalse(PersonName.isValidPersonName(" ")); // spaces only
        assertFalse(PersonName.isValidPersonName("&")); // only non-alphanumeric character
        assertFalse(PersonName.isValidPersonName("Te$la")); // contains non-alphanumeric characters
        assertFalse(PersonName.isValidPersonName("12345")); // numbers only
        assertFalse(PersonName.isValidPersonName("Post Malone the 2nd")); // alphanumeric characters
        assertFalse(PersonName.isValidPersonName("jack  sparrow")); // double spaces
        assertFalse(PersonName.isValidPersonName(" jack sparrow")); // space before the person name

        // valid person name
        assertTrue(PersonName.isValidPersonName("jack")); // single word, no space
        assertTrue(PersonName.isValidPersonName("jack sparrow")); // alphebets only
        assertTrue(PersonName.isValidPersonName("Saul Goodman")); // with capital letters
        assertTrue(PersonName.isValidPersonName("AARON TAN ZF")); // fully capital letters


    }



}
