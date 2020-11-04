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

    public static String getAutoCompleteResult(String input, List<String> commandSuggestionList) {
        String feedback = "";
        String[] inputArr = input.split(" ");
        String prefix = inputArr[0];

        if (inputArr.length == 1) {
            for (String suggestion : commandSuggestionList) {
                if (suggestion.startsWith(prefix.toLowerCase())) {
                    feedback += suggestion + "\n";
                }
            }
        } else {
            feedback = commandSuggestionList.stream()
                    .filter(suggestion -> prefix.equals(suggestion.split(" ")[0])).findFirst().orElse("");
        }

        return feedback.trim();
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

        resultDisplay.setText(getAutoCompleteResult(input, commandSuggestionList));
    }
}
