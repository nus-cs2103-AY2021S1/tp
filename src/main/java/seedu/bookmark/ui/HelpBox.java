package seedu.bookmark.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class HelpBox extends VBox {
    @FXML
    private Label exampleIntro;

    @FXML
    private Label exampleMessage;

    @FXML
    private Label exampleCommand;

    private HelpBox(String intro, String message, String example) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/HelpBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        exampleIntro.setText(intro);
        exampleMessage.setText(message);
        exampleCommand.setText(example);
    }

    /**
     * Creates a HelpBox object
     * @param intro Text that is to be included in the Help Box
     * @param message A picture as an example
     * @param example
     * @return a HelpBox object
     */
    public static HelpBox getHelpBox(String intro, String message, String example) {
        return new HelpBox(intro, message, example);
    }
}
