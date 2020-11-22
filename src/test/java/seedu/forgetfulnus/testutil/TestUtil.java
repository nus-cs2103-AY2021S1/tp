package seedu.forgetfulnus.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import seedu.forgetfulnus.commons.core.index.Index;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.FlashCard;

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
     * Returns the middle index of the flashcard in the {@code model}'s flashcard list.
     */
    public static Index getMidIndex(Model model) {
        return Index.fromOneBased(model.getFilteredFlashCardList().size() / 2);
    }

    /**
     * Returns the last index of the flashcard in the {@code model}'s flashcard list.
     */
    public static Index getLastIndex(Model model) {
        return Index.fromOneBased(model.getFilteredFlashCardList().size());
    }

    /**
     * Returns the flashcard in the {@code model}'s flashcard list at {@code index}.
     */
    public static FlashCard getFlashCard(Model model, Index index) {
        return model.getFilteredFlashCardList().get(index.getZeroBased());
    }

    /**
     * Checks whether the order in two {$code FlashCard} lists are exactly the same.
     * @param list1 first list to compare.
     * @param list2 second list to compare
     * @return true if the orders of the lists are the same, false otherwise.
     */
    public static boolean checkSortedOrder(List<FlashCard> list1, List<FlashCard> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }
}
