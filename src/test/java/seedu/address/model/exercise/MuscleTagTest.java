package seedu.address.model.exercise;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MuscleTagTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MuscleTag(null));
    }

    @Test
    public void constructor_invalidMuscleTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new MuscleTag(invalidTagName));
    }

    @Test
    public void isValidMuscleTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> MuscleTag.isValidMuscleTagName(null));
    }
}
