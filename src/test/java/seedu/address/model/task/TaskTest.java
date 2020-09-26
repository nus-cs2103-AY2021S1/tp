package seedu.address.model.task;

import org.junit.jupiter.api.Test;
import static seedu.address.testutil.Assert.assertThrows;

public class TaskTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task(null, null, null, 0, false));
    }

    // TODO: invalid test

}
