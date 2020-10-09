package tp.cap5buddy.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import tp.cap5buddy.logic.commands.CommandResult;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

public class MainWindowResult extends HBox {
    private static final Logger logger = LogsCenter.getLogger(MainWindowResult.class);
    private static final URL url = MainWindowResult.class.getResource("view/tp/MainWindowResult.fxml");
    @FXML
    private Label resultWindow;

//    public MainWindowResult(CommandResult resultContainer) {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(MainWindowResult.class.getResource("view/tp/MainWindowResult.fxml"));
//            fxmlLoader.setController(this);
//            fxmlLoader.setRoot(this);
//            fxmlLoader.load();
//        } catch (IOException e) {
//            logger.info(e.getMessage());
//        }
//        text.setText(resultContainer.getMessage());
//    }

    public MainWindowResult(String random) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindowResult.class.getResource("/view/tp/MainWindowResult.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        resultWindow.setText(random);
    }
}
