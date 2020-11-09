package tp.acecs2103.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static tp.acecs2103.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FileUtilTest {

    @Test
    public void isValidPath() {
        // valid path
        assertTrue(FileUtil.isValidPath("valid/file/path"));

        // invalid path
        assertFalse(FileUtil.isValidPath("a\0"));

        // null path -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> FileUtil.isValidPath(null));
    }

}
