package tp.cap5buddy.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

public class MainWindow extends AnchorPane {
    private static final Logger logger = LogsCenter.getLogger(MainWindow.class);
    private static final URL DIR = MainWindow.class.getResource("/view/tp/MainWindow.fxml");
    private Stage primaryStage;

    @FXML
    private TextField userInputTextBox;
    @FXML
    private VBox mainDisplayWindowVbox;


    @FXML
    public void initialize() {
        // link all the vbox and textbox to instance fields.
        loadSavedJson();
    }

    @FXML
    private void handleUserInput() {
        String input = userInputTextBox.getText();
        mainDisplayWindowVbox.getChildren().addAll(new MainWindowResult(input));
        logger.info(input);
    }

    private boolean loadSavedJson() {
        return true;
    }
}
