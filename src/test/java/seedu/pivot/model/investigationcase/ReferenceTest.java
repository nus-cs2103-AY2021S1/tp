package seedu.pivot.model.investigationcase;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.pivot.commons.util.FileUtil;


public class ReferenceTest {

    @TempDir
    public static Path testFolder;

    private static Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    /**
     * Represents a Reference with separate default paths for testing.
     */
    private static class ReferenceStub extends Reference {
        private static final String DEFAULT_TESTPATH = "./testDirectory/";

        public ReferenceStub(String fileName) {
            super(fileName);
        }
        @Override
        public String getFilePath() {
            return DEFAULT_TESTPATH;
        }

        //override isExists to check the correct test file location
        @Override
        public boolean isExists() {
            return FileUtil.isFileExists(getTempFilePath(path.toString()));
        }

    }


    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Reference(null));
    }

    @Test
    public void constructor_invalidReference_throwsIllegalArgumentException()
            throws NoSuchFieldException, IllegalAccessException {
        String invalidReference = "";
        assertThrows(IllegalArgumentException.class, () -> new Reference(invalidReference));
    }

    @Test
    public void isValidReference() {
        assertFalse(Reference.isValidReference(""));
        assertFalse(Reference.isValidReference("invalid :across ?/\0 OS"));
        assertTrue(Reference.isValidReference("fileWithExtension.txt"));
    }

    @Test
    public void equals() {
        Reference reference = new Reference("validRef");
        Reference referenceDuplicate = new Reference("validRef");

        //same object -> returns true
        assertTrue(reference.equals(reference));

        //null -> returns false
        assertFalse(reference.equals(null));

        //same path -> true
        assertTrue(reference.equals(referenceDuplicate));
    }


    @Test
    public void isExists() throws IOException {

        Reference doesNotExist = new ReferenceStub("dummy.txt");
        Reference exists = new ReferenceStub("existReference.txt");

        FileUtil.createFile(getTempFilePath("./testDirectory/existReference.txt"));
        assertTrue(exists.isExists());
        assertFalse(doesNotExist.isExists());

    }
}
