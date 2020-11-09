package seedu.address.ui;

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
    private List<String> suggestions;

    public ResultDisplay() {
        super(FXML);
    }

    public void setSuggestionList(List<String> suggestions) {
        this.suggestions = suggestions;
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);
    }

    /**
     * Gives smart suggestions on commands user attempts to input.
     *
     * @author AY2021S1-CS2103-F10-3
     * @param input input
     * @param commandSuggestionList command suggestion list
     *
     * @return feedback string
     */
    //@@author Caiyi34777-reused
    //Reused from https://github.com/AY2021S1-CS2103-F10-3/tp to implement auto-suggestion
    public static String getAutoCompleteResult(String input, List<String> commandSuggestionList) {
        String feedback = "";
        String[] inputArr = input.split(" ");
        String prefix = inputArr[0];

        if (inputArr.length == 1) {
            for (String suggestion : commandSuggestionList) {
                if (suggestion.startsWith(prefix)) {
                    feedback += suggestion + "\n";
                }
            }
        } else {
            feedback = commandSuggestionList.stream()
                    .filter(suggestion -> prefix.equals(suggestion.split(" ")[0])).findFirst().orElse("");
        }

        return feedback;
    }
    //@@author

    /**
     * Shows autocomplete result to user.
     *
     * @author AY2021S1-CS2103-F10-3
     * @param input input of the user
     *
     */
    //@@author Caiyi34777-reused
    //Reused from https://github.com/AY2021S1-CS2103-F10-3/tp to implement auto-suggestion
    public void showAutoCompleteResult(String input) {
        requireNonNull(input);

        if (input.length() == 0) {
            return;
        }

        resultDisplay.setText(getAutoCompleteResult(input, suggestions));
    }
    //@@author

}
