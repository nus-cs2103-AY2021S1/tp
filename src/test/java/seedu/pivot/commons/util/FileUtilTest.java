package seedu.pivot.commons.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.testutil.Assert.assertThrows;

import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.pivot.commons.core.UserMessages;
import seedu.pivot.logic.commands.exceptions.CommandException;

public class FileUtilTest {

    @TempDir
    public static Path testFolder;

    private static final String SINGLE_FILE = "test.txt";
    private static Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void isValidPath() {
        // valid path
        assertTrue(FileUtil.isValidPath("valid/file/path"));

        // invalid path
        assertFalse(FileUtil.isValidPath("a\0"));

        // null path -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> FileUtil.isValidPath(null));
    }

    @Test
    public void openDocument() throws IOException {
        Files.createFile(getTempFilePath(SINGLE_FILE));

        boolean supported = Desktop.isDesktopSupported();
        if (supported) {
            assertDoesNotThrow(() -> FileUtil.openFile(getTempFilePath(SINGLE_FILE)));
        } else {
            assertThrows(CommandException.class, UserMessages.MESSAGE_DESKTOP_API_NOT_AVAILABLE, (
            ) -> FileUtil.openFile(getTempFilePath(SINGLE_FILE)));
        }
    }

}
