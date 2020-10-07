package tp.cap5buddy.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import seedu.address.commons.core.LogsCenter;
import tp.cap5buddy.logic.commands.CommandResult;

import java.io.IOException;
import java.util.logging.Logger;

public class MainWindowResult extends HBox {
    private static final Logger logger = LogsCenter.getLogger(MainWindowResult.class);
    @FXML
    private Label text;

    public MainWindowResult(CommandResult resultContainer) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindowResult.class.getResource("view/tp/MainWindowResult.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        text.setText(resultContainer.getMessage());
    }
}
