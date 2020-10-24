package seedu.fma.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private TextArea resultDisplay;

    private HashMap<String, String> commandMessageUsage;

    public ResultDisplay() {
        super(FXML);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);
    }

    public void setAutoCompleteList(HashMap<String, String> commandMessageUsage) {
        this.commandMessageUsage = commandMessageUsage;
    }

    public void getAutoCompleteResult(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        String feedback = "";
        for (String regex : commandMessageUsage.keySet()) {
            if (Pattern.matches(regex, feedbackToUser.toLowerCase())) {
                feedback += commandMessageUsage.get(regex) + "\n";
            }
        }
        resultDisplay.setText(feedback);
    }
}
