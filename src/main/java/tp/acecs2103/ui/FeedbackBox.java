package tp.acecs2103.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the feedback after axecuting a command.
 */
public class FeedbackBox extends UiPart<Region> {

    private static final String FXML = "FeedbackBox.fxml";

    @FXML
    private TextArea feedbackDisplay;

    /**
     * Creates a feedback box component.
     */
    public FeedbackBox() {
        super(FXML);
        feedbackDisplay.setText("Welcome!");
    }

    /**
     * Updates the content in the feedback box.
     * @param feedbackToUser is a string message which indicates the result after executing a command.
     */
    public void setFeedbackToUser(String feedbackToUser) {
        feedbackDisplay.setText(feedbackToUser);
    }

}
