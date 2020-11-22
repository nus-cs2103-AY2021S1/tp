package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.testfx.api.FxToolkit;

import guitests.guihandles.HelpWindowHandle;
import guitests.guihandles.MainWindowHandle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import seedu.address.MainApp;
import seedu.address.logic.LogicManager;
import seedu.address.model.ModelManager;
import seedu.address.storage.JsonTbmManagerStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;

/**
 * Contains tests for closing of the {@code MainWindow}.
 */
public class MainWindowCloseTest extends GuiUnitTest {

    @TempDir
    public Path temporaryFolder;
    private MainWindow mainWindow;
    private MainWindowHandle mainWindowHandle;
    private Stage stage;

    @BeforeEach
    public void setUp() throws Exception {
        JsonTbmManagerStorage jsonTbmManagerStorage =
                new JsonTbmManagerStorage(temporaryFolder.resolve("tbmManager.json"));
        JsonUserPrefsStorage jsonUserPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storageManager = new StorageManager(jsonTbmManagerStorage, jsonUserPrefsStorage);
        FxToolkit.setupStage(stage -> {
            this.stage = stage;
            mainWindow = new MainWindow(stage, new LogicManager(new ModelManager(), storageManager), new MainApp());
            mainWindow.fillInnerParts();
            mainWindowHandle = new MainWindowHandle(stage);
            mainWindowHandle.focus();
        });
        FxToolkit.showStage();
    }

    @Test
    public void close_menuBarExitButton_allWindowsClosed() {
        guiRobot.pauseForHuman();
        assertTrue(mainWindowHandle.isShowing());
        guiRobot.clickOn("#file");
        guiRobot.clickOn("#exit");
        guiRobot.pauseForHuman();
        // The application will exit when all windows are closed.
        assertEquals(Collections.emptyList(), guiRobot.listWindows());
    }

    @Test
    public void close_externalRequest_exitAppRequestEventPosted() {
        guiRobot.clickOn("#help");
        guiRobot.clickOn("#helpMenuItem");
        assertTrue(HelpWindowHandle.isWindowPresent());
        // Close the main window
        guiRobot.interact(() -> stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST)));
        // The application will exit when all windows are closed.
        guiRobot.pauseForHuman();
        assertEquals(Collections.emptyList(), guiRobot.listWindows());
    }

}
