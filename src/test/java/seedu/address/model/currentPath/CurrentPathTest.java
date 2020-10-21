package seedu.address.model.currentPath;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.currentpath.CurrentPath;


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
