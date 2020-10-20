package quickcache.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static quickcache.logic.commands.CommandTestUtil.assertCommandFailure;
import static quickcache.logic.commands.CommandTestUtil.assertCommandSuccess;
import static quickcache.testutil.TypicalFlashcards.getTypicalQuickCache;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import quickcache.model.Model;
import quickcache.model.ModelManager;
import quickcache.model.ReadOnlyQuickCache;
import quickcache.model.UserPrefs;
import quickcache.model.flashcard.Flashcard;
import quickcache.storage.JsonQuickCacheStorage;
import quickcache.testutil.TestUtil;
import quickcache.testutil.TypicalFlashcards;
import quickcache.testutil.TypicalIndexes;

class ExportCommandTest {

    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");
    private static final String TEST_FILE_NAME = "test_export.json";
    @TempDir
    public Path temporaryFolder;
    private final Model model = new ModelManager(getTypicalQuickCache(), new UserPrefs());

    @Test
    public void execute_unfilteredList_success() throws Exception {
        // Checks that command executed properly
        Model expectedModel = new ModelManager(getTypicalQuickCache(), new UserPrefs());
        Path testFilePath = TestUtil.getFilePathInSandboxFolder(TEST_FILE_NAME);
        ExportCommand exportCommand = new ExportCommand(testFilePath);
        String expectedMessage = String.format(ExportCommand.MESSAGE_EXPORT_FLASHCARDS_SUCCESS, testFilePath);
        assertCommandSuccess(exportCommand, model, expectedMessage, expectedModel);

        // Checks that json exported is the same as that in current filtered flashcard list
        JsonQuickCacheStorage jsonQuickCacheStorage = new JsonQuickCacheStorage(testFilePath);
        List<Flashcard> readBack = jsonQuickCacheStorage.readQuickCache().get().getFlashcardList();
        List<Flashcard> original = expectedModel.getFilteredFlashcardList();
        assertEquals(original, readBack);
    }

    @Test
    public void execute_filteredList_success() throws Exception {
        // Checks that command executed properly
        CommandTestUtil.showFlashcardAtIndex(model, TypicalIndexes.INDEX_FIRST_FLASHCARD);
        Model expectedModel = new ModelManager(getTypicalQuickCache(), new UserPrefs());
        expectedModel.updateFilteredFlashcardList((flashcard -> flashcard.equals(TypicalFlashcards.RANDOM1)));
        Path testFilePath = TestUtil.getFilePathInSandboxFolder(TEST_FILE_NAME);
        ExportCommand exportCommand = new ExportCommand(testFilePath);
        String expectedMessage = String.format(ExportCommand.MESSAGE_EXPORT_FLASHCARDS_SUCCESS, testFilePath);
        assertCommandSuccess(exportCommand, model, expectedMessage, expectedModel);

        // Checks that json exported is the same as that in current filtered flashcard list
        JsonQuickCacheStorage jsonQuickCacheStorage = new JsonQuickCacheStorage(testFilePath);
        List<Flashcard> readBack = jsonQuickCacheStorage.readQuickCache().get().getFlashcardList();
        List<Flashcard> original = expectedModel.getFilteredFlashcardList();
        assertEquals(original, readBack);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup with JsonQuickCacheIoExceptionThrowingStub
        Path invalidTestFilePath = temporaryFolder.resolve("ioExceptionQuickCache.json");
        JsonQuickCacheStorage quickCacheStorage = new JsonQuickCacheIoExceptionThrowingStub(invalidTestFilePath);

        // Execute export command
        Model expectedModel = new ModelManager(getTypicalQuickCache(), new UserPrefs());
        ExportCommand exportCommand = new ExportCommand(invalidTestFilePath, quickCacheStorage);
        String expectedMessage = String.format(ExportCommand.MESSAGE_EXPORT_FLASHCARDS_FAILURE, invalidTestFilePath);
        assertCommandFailure(exportCommand, expectedModel, expectedMessage);
    }

    @Test
    public void equals() {
        String fileNameSecond = "non_test_export.json";

        Path testFilePathFirst = TestUtil.getFilePathInSandboxFolder(TEST_FILE_NAME);
        Path testFilePathSecond = TestUtil.getFilePathInSandboxFolder(fileNameSecond);
        ExportCommand exportFirstCommand = new ExportCommand(testFilePathFirst);
        ExportCommand exportSecondCommand = new ExportCommand(testFilePathSecond);

        // same object -> returns true
        assertTrue(exportFirstCommand.equals(exportFirstCommand));

        // same values -> returns true
        ExportCommand exportFirstCommandCopy = new ExportCommand(testFilePathFirst);
        assertTrue(exportFirstCommand.equals(exportFirstCommandCopy));

        // different types -> returns false
        assertFalse(exportFirstCommand.equals(1));

        // null -> returns false
        assertFalse(exportFirstCommand.equals(null));

        // different path -> returns false
        assertFalse(exportFirstCommand.equals(exportSecondCommand));
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonQuickCacheIoExceptionThrowingStub extends JsonQuickCacheStorage {
        private JsonQuickCacheIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveQuickCache(ReadOnlyQuickCache quickCache, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }

        @Override
        public void saveQuickCache(ReadOnlyQuickCache quickCache) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
