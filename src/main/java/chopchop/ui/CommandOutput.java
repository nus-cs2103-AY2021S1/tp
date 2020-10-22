package chopchop.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandOutput extends UiPart<Region> {

    private static final String FXML = "CommandOutput.fxml";


    @FXML
    private TextArea displayBox;

    /**
     * Constructs {@code CommandBox}
     */
    public CommandOutput() {
        super(FXML);
    }

    /**
     * Displays the commandResult to the user.
     *
     * @param feedbackToUser
     */
    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        displayBox.setText(feedbackToUser);
    }
}
