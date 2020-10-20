package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SavedFilePathTest {

    @Test
    public void equals() {
        SavedFilePath firstSavedFilePath = new SavedFilePath(System.getProperty("user.dir"));
        SavedFilePath secondSavedFilePath = new SavedFilePath(System.getProperty("java.home"));

        assertTrue(firstSavedFilePath.equals(firstSavedFilePath));

        SavedFilePath firstSavedFilePathCopy = new SavedFilePath(System.getProperty("user.dir"));
        assertTrue(firstSavedFilePath.equals(firstSavedFilePathCopy));

        assertFalse(firstSavedFilePath.equals(0));

        assertFalse(firstSavedFilePath.equals(null));

        assertFalse(firstSavedFilePath.equals(secondSavedFilePath));
    }
}
