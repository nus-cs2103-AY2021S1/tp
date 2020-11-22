package seedu.address.testutil;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.client.Client;

/**
 * A utility class for test cases.
 */
public class TestUtil {

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    private static final Path SANDBOX_FOLDER = Paths.get("src", "test", "data", "sandbox");

    /**
     * Appends {@code fileName} to the sandbox folder path and returns the resulting path.
     * Creates the sandbox folder if it doesn't exist.
     */
    public static Path getFilePathInSandboxFolder(String fileName) {
        try {
            Files.createDirectories(SANDBOX_FOLDER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SANDBOX_FOLDER.resolve(fileName);
    }

    /**
     * Conducts all basic equals tests.
     */
    public static <T> void basicEqualsTests(T object) {
        // same object -> returns true
        assertTrue(object.equals(object));
        // null -> returns false
        assertFalse(object.equals(null));
        // different class -> returns false
        assertFalse(object.equals(2.0f));
        // random object -> returns false
        Object randomObject = new Object();
        assertFalse(object.equals(randomObject));
    }

    /**
     * Returns the middle index of the client in the {@code model}'s client list.
     */
    public static Index getMidIndex(Model model) {
        return Index.fromOneBased(model.getSortedFilteredClientList().size() / 2);
    }

    /**
     * Returns the last index of the client in the {@code model}'s client list.
     */
    public static Index getLastIndex(Model model) {
        return Index.fromOneBased(model.getSortedFilteredClientList().size());
    }

    /**
     * Returns the client in the {@code model}'s client list at {@code index}.
     */
    public static Client getClient(Model model, Index index) {
        return model.getSortedFilteredClientList().get(index.getZeroBased());
    }

}
