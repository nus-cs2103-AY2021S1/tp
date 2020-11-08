package seedu.pivot.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.pivot.storage.testutil.ReferenceStorageStub;


public class ReferenceStorageTest {


    @TempDir
    public Path testFolder;

    private ReferenceStorage referenceStorage;


    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void constructor_success() throws IOException {
        ReferenceStorage referenceStorage = new ReferenceStorageStub(getTempFilePath("./tests"));
        assertTrue(Files.exists(getTempFilePath("tests")));
    }

    @Test
    public void addTestFile_success() throws IOException {
        ReferenceStorage referenceStorage = new ReferenceStorageStub(getTempFilePath("./testDir"));
        assertFalse(Files.exists(getTempFilePath("./testDir/testFile.txt")));
        referenceStorage.addTestFile();
        assertTrue(Files.exists(getTempFilePath("./testDir/testFile.txt")));
    }


}
