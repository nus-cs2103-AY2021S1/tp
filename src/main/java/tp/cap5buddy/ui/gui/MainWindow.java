package tp.cap5buddy.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
    private TextField userInput;

    public MainWindow(Stage primaryStage) {
        AnchorPane ap = new AnchorPane();
        FXMLLoader fxmlLoader = new FXMLLoader(DIR);
        fxmlLoader.setLocation(DIR);
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(ap);
        Scene sc = new Scene(ap);
        primaryStage.setScene(sc);
        this.primaryStage = primaryStage;
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        this.primaryStage = primaryStage;
    }

    public void show() {
        this.primaryStage.show();
    }

    @FXML
    public void initialize() {
        // link all the vbox and textbox to instance fields.
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        logger.info(input);
    }
}
