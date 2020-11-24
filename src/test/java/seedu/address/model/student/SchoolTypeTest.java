package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SchoolTypeTest {

    @Test
    public void toString_returnsCorrectString() {
        assertEquals("Primary", SchoolType.PRIMARY.toString());
        assertEquals("Secondary", SchoolType.SECONDARY.toString());
        assertEquals("JC", SchoolType.JC.toString());
    }

    @Test
    public void isValidSchoolType_validSchoolType_returnsTrue() {
        assertTrue(SchoolType.isValidSchoolType("pri"));
        assertTrue(SchoolType.isValidSchoolType("primary"));
        assertTrue(SchoolType.isValidSchoolType("Primary"));
        assertTrue(SchoolType.isValidSchoolType("p"));
        assertTrue(SchoolType.isValidSchoolType("sec"));
        assertTrue(SchoolType.isValidSchoolType("JC"));
    }

    @Test
    public void isValidSchoolType_invalidSchoolType_returnsFalse() {
        assertFalse(SchoolType.isValidSchoolType("ns"));
        assertFalse(SchoolType.isValidSchoolType("n"));
        assertFalse(SchoolType.isValidSchoolType("yramirp"));
    }
}
