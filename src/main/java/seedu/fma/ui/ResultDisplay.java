package seedu.fma.ui;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;


/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private TextArea resultDisplay;

    private List<String> commandSuggestionList;

    public ResultDisplay() {
        super(FXML);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);
    }

    public void setSuggestionList(List<String> commandMessageUsage) {
        this.commandSuggestionList = commandMessageUsage;
    }

    /**
     * Show autocomplete result to user.
     * @param input input of the user
     */
    public void showAutoCompleteResult(String input) {
        requireNonNull(input);

        if (input.length() == 0) {
            return;
        }

        String feedback = "";
        input = input.length() > 3 ? input.substring(0, 3) : input;
        for (String suggestion : commandSuggestionList) {
            if (suggestion.startsWith(input.toLowerCase())) {
                feedback += suggestion + "\n";
            }
        }
        resultDisplay.setText(feedback.trim());
    }
}
