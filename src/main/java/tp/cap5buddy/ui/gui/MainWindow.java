package tp.cap5buddy.ui.gui;

import java.net.URL;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tp.cap5buddy.commons.LogsCenter;


public class MainWindow extends AnchorPane {
    private static final Logger logger = LogsCenter.getLogger(MainWindow.class);
    private static final URL DIR = MainWindow.class.getResource("/view/tp/MainWindow.fxml");
    private Stage primaryStage;

    @FXML
    private TextField userInputTextBox;
    @FXML
    private VBox mainDisplayWindowVbox;

    /**
     * Runs the initialising conditions for the GUI.
     */
    @FXML
    public void initialize() {
        // link all the vbox and textbox to instance fields.
        loadSavedJson();
    }

    /**
     * Runs to react to the user input.
     */
    @FXML
    private void handleUserInput() {
        String input = userInputTextBox.getText();
        mainDisplayWindowVbox.getChildren().addAll(new MainWindowResult(input));
        logger.info(input);
    }

    /**
     * Loads the local saved file.
     * @return
     */
    private boolean loadSavedJson() {
        return true;
    }
}
