package seedu.jarvis.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Test;

import javafx.stage.Stage;
import seedu.jarvis.commons.core.GuiSettings;
import seedu.jarvis.logic.Logic;
import seedu.jarvis.logic.LogicManager;
import seedu.jarvis.model.Model;
import seedu.jarvis.storage.Storage;
import seedu.jarvis.testutil.TypicalManagers;

public class MainWindowTest {

    // Integration and unit tests combined

    // This model is used to execute commands
    private static Storage storage;
    private static Model model;
    private static Logic logic;
    private static Stage stage;
    private static MainWindow mainWindowToBeTested;
    private static GuiSettings guiSettings;

    // Every possible command has to be tested here
    // I just need to copy the code here to the new branch later on.
    // create a stub.

    @Test
    public void constructor_setWindowSizeSuccess() {
        final AtomicBoolean running = new AtomicBoolean(false);
        final double[] expectedHeight = new double[1];
        final double[] expectedWidth = new double[1];
        final double[] actualHeight = new double[1];
        final double[] actualWidth = new double[1];

        Thread thread = new Thread(() -> {
            running.set(true);
            storage = TypicalManagers.getStorage();
            model = TypicalManagers.getModel();
            logic = new LogicManager(model, storage);
            stage = new Stage();
            mainWindowToBeTested = new MainWindow(stage, logic);
            guiSettings = logic.getGuiSettings();
            expectedHeight[0] = guiSettings.getWindowHeight();
            expectedWidth[0] = guiSettings.getWindowWidth();
            actualHeight[0] = stage.getHeight();
            actualWidth[0] = stage.getWidth();
            running.set(false);
        });
        assertEquals(expectedHeight[0], actualHeight[0]);
        assertEquals(expectedWidth[0], actualWidth[0]);
        if (!running.get()) {
            thread.interrupt();
        }
    }

}
