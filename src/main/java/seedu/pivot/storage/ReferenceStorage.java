package seedu.pivot.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.pivot.commons.util.FileUtil;

public class ReferenceStorage {

    private static final Path DEFAULT_DIRECTORY = Paths.get("./references/");
    private static final Path TEST_FILE_PATH = Paths.get("./references/test1.txt");

    public ReferenceStorage() throws IOException {
        FileUtil.createDirectories(DEFAULT_DIRECTORY);
    }


    protected void addTestFile() throws IOException {
        FileUtil.createIfMissing(TEST_FILE_PATH);
    }

}
