package seedu.resireg.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


/**
 * A ui item in the {@link ResultDisplay} which displays an executed command and its result.
 */
class ResultDisplayItem extends UiPart<VBox> {

    private static final String FXML = "ResultDisplayItem.fxml";

    @FXML
    private Label command;
    @FXML
    private Label feedback;

    /**
     *
     * @param commandText Command executed.
     * @param feedbackText Feedback to show to user.
     * @param isError Whether the command execution led to an error.
     */
    ResultDisplayItem(String commandText, String feedbackText, boolean isError) {
        super(FXML);
        command.setText(commandText);
        feedback.setText(feedbackText);

        if (isError) {
            command.getStyleClass().add("result-display-invalid-command");
        } else {
            command.getStyleClass().add("result-display-valid-command");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ResultDisplayItem)) {
            return false;
        }

        ResultDisplayItem item = (ResultDisplayItem) other;
        return command.getText().equals(item.command.getText())
                && feedback.getText().equals(item.feedback.getText());
    }
}
