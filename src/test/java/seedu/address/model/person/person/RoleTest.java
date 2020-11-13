package seedu.address.model.person.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class RoleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Role.getRole(null));
    }

    @Test
    public void constructor_invalidRole_throwsIllegalArgumentException() {
        String invalidRole = "";
        assertThrows(IllegalArgumentException.class, () -> Role.getRole(invalidRole));
    }

    @Test
    public void isValidRole() {
        // null Role
        assertThrows(NullPointerException.class, () -> Role.isValidRole(null));

        // invalid Roles
        assertFalse(Role.isValidRole("")); // empty string
        assertFalse(Role.isValidRole(" ")); // spaces only
        assertFalse(Role.isValidRole("91")); // less than 3 numbers
        assertFalse(Role.isValidRole("Role")); // non-numeric
        assertFalse(Role.isValidRole("9011p041")); // alphabets within digits
        assertFalse(Role.isValidRole("9312 1534")); // spaces within digits

        // valid Roles
        assertTrue(Role.isValidRole("stk"));
        assertTrue(Role.isValidRole("dev"));
    }
}
