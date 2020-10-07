package seedu.address.model.student.admin;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.student.admin.AdditionalDetail;

public class AdditionalDetailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AdditionalDetail(null));
    }

    @Test
    public void constructor_invalidAdditionalDetail_throwsIllegalArgumentException() {
        String invalidAdditionalDetail = "";
        assertThrows(IllegalArgumentException.class, () -> new AdditionalDetail(invalidAdditionalDetail));
    }

    @Test
    public void isValidAdditionalDetail() {
        // null additional detail
        assertThrows(NullPointerException.class, () -> AdditionalDetail.isValidAdditionalDetail(null));
    }

}
