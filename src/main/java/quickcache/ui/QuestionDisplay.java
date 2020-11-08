package quickcache.ui;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * A ui for the question window that is displayed at the header of the application.
 */
public class QuestionDisplay extends UiPart<Region> {

    private static final String FXML = "QuestionDisplay.fxml";

    @FXML
    private VBox displayBox;

    @FXML
    private TextArea questionDisplay;

    @FXML
    private TextArea answerDisplay;

    @FXML
    private TextArea outcomeDisplay;

    /**
     * Creates a {@code QuestionDisplay}.
     */
    public QuestionDisplay() {
        super(FXML);

        ObservableList<Node> tmp = FXCollections.observableArrayList(displayBox.getChildren());
        tmp.remove(this.answerDisplay);
        tmp.remove(this.outcomeDisplay);
        displayBox.getChildren().setAll(tmp);
    }

    public void setQuestion(String question) {
        requireNonNull(question);
        questionDisplay.setText("Question:\n" + question);
    }

    /**
     * Displays the outcome of a test command to the user.
     *
     * @param feedbackToUser the feedback to be displayed to the user.
     * @param isAnswerCorrect displays the answer to be correct if true and vice versa.
     */
    public void showOutcome(String feedbackToUser, boolean isAnswerCorrect) {
        ObservableList<Node> tmp = FXCollections.observableArrayList(displayBox.getChildren());
        tmp.addAll(this.answerDisplay, this.outcomeDisplay);
        displayBox.getChildren().setAll(tmp);

        answerDisplay.setText(feedbackToUser);

        if (isAnswerCorrect) {
            outcomeDisplay.setText("You are correct!");
            outcomeDisplay.setStyle("-fx-text-fill: #608a5a;");
        } else {
            outcomeDisplay.setText("You are incorrect!");
            outcomeDisplay.setStyle("-fx-text-fill: #b30000;");
        }
    }

}
