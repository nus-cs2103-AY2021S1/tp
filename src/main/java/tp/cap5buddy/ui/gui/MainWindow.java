package tp.cap5buddy.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Logger;

public class MainWindow extends AnchorPane {
    private static final Logger logger = LogsCenter.getLogger(MainWindow.class);
    private Stage primaryStage;


    public MainWindow(Stage primaryStage) {
        this.primaryStage = primaryStage;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/tp/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene sc = new Scene(ap);
            this.primaryStage.setScene(sc);
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }

    public void show() {
        this.primaryStage.show();
    }

    @FXML
    public void initialize() {
        // link all the vbox and textbox to instance fields.
    }
}
