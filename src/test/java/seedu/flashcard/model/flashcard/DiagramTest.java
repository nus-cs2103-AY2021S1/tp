package seedu.flashcard.model.flashcard;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class DiagramTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "ImageTest");
    private static final Path INVALID_FILE_TYPE = TEST_DATA_FOLDER.resolve("invalidFileType.txt");
    private static final Path NON_EXISTENT_FILE = TEST_DATA_FOLDER.resolve("nonExistentFile.jpg");
    private static final Path VALID_FILE_TYPE = TEST_DATA_FOLDER.resolve("valid_image.jpg");


    @Test
    public void read_invalidFileType() throws Exception {
        assertFalse(Diagram.isValidImageFileType(INVALID_FILE_TYPE.toString()));
    }

    @Test
    public void read_missingFile() throws Exception {
        assertFalse(Diagram.isValidImageFileType(NON_EXISTENT_FILE.toString()));
    }

    @Test
    public void read_validFile() throws Exception {
        assertTrue(Diagram.isValidImageFileType(VALID_FILE_TYPE.toString()));
    }

}

