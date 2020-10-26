package seedu.pivot.storage.testutil;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.pivot.commons.util.FileUtil;
import seedu.pivot.storage.ReferenceStorage;

/**
 * Simulates creation of a { @code ReferenceStorage } at a separate test location.
 */
public class ReferenceStorageStub extends ReferenceStorage {

    private final Path testDirectory;

    /**
     * Creates a directory at specified { @code Path } , path is assumed to be valid.
     */
    public ReferenceStorageStub(Path testDirectory) throws IOException {
        FileUtil.createDirectories(testDirectory);
        this.testDirectory = testDirectory;
    }

    @Override
    public void addTestFile() throws IOException {
        Path filePath = Paths.get(testDirectory.toString() + "/testFile.txt");
        FileUtil.createIfMissing(filePath);
    }
}
