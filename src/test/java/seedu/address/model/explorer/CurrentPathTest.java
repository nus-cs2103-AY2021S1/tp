package seedu.address.model.explorer;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class CurrentPathTest {

    @Test
    public void constructor_null_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CurrentPath(null, null));
    }
}
