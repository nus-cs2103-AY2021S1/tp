package seedu.address.model.currentPath;

import org.junit.jupiter.api.Test;
import seedu.address.model.currentpath.CurrentPath;

import static seedu.address.testutil.Assert.assertThrows;

public class CurrentPathTest {

    @Test
    public void constructor_null_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CurrentPath(null));
    }

    @Test
    public void setAddress_null_throwNullPointerException() {
        CurrentPath currentPath = new CurrentPath(new FileListStub());
        assertThrows(NullPointerException.class, () -> currentPath.setAddress(null));
    }
}
