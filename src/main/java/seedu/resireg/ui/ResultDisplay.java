package seedu.resireg.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * A ui for the history of executed commands and results, which is displayed as a panel on the left of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private VBox responseContainer;
    @FXML
    private ScrollPane resultDisplayScroll;

    /**
     * Creates a ResultDisplay to display the result of a user's input.
     */
    public ResultDisplay() {
        super(FXML);
        resultDisplayScroll.vvalueProperty().bind(responseContainer.heightProperty());
    }

    public void setFeedbackToUser(String command, String feedbackToUser, boolean isError) {
        requireNonNull(feedbackToUser);
        ResultDisplayItem response = new ResultDisplayItem(command, feedbackToUser, isError);
        responseContainer
                .getChildren()
                .add(response.getRoot());
    }

    public void setFeedbackToUser(String command, String feedbackToUser) {
        setFeedbackToUser(command, feedbackToUser, false);
    }
}
