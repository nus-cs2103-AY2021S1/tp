package seedu.resireg.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * A ui for the status bar that is displayed at the header of the application.
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

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        TextArea response = new TextArea(feedbackToUser);
        response.setEditable(false);
        response.getStyleClass().add("result-display");
        responseContainer
                .getChildren()
                .add(response);
    }

}
