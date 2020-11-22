package seedu.address.model.studentgroup;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentGroupTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentGroup(null));
    }

    @Test
    public void constructor_invalidStudentGroupName_throwsIllegalArgumentException() {
        String invalidStudentGroupName = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentGroup(invalidStudentGroupName));
    }

    @Test
    public void isValidStudentGroupName() {
        // null student group name
        assertThrows(NullPointerException.class, () -> StudentGroup.isValidStudentGroupName(null));
    }

}
