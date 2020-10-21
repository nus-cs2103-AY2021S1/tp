package seedu.address.model.currentPath;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.currentpath.CurrentPath;


public class CurrentPathTest {

    @Test
    public void constructor_null_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CurrentPath(null, null));
    }

    @Test
    public void setAddress_null_throwNullPointerException() {
        String testPath = "C:\\Users";
        CurrentPath currentPath = new CurrentPath(testPath, new FileListStub());
        assertThrows(NullPointerException.class, () -> currentPath.setAddress(null));
    }
}
