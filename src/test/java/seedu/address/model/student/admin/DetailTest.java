package seedu.address.model.student.admin;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DetailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Detail(null));
    }

    @Test
    public void constructor_invalidDetail_throwsIllegalArgumentException() {
        String invalidDetail = "";
        assertThrows(IllegalArgumentException.class, () -> new Detail(invalidDetail));
    }

    @Test
    public void isValidDetail() {
        // null detail
        assertThrows(NullPointerException.class, () -> Detail.isValidDetail(null));

        //
    }

}
