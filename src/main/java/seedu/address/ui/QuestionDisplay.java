package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * A ui for the status bar that is displayed at the header of the application.
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

    public QuestionDisplay() {
        super(FXML);

        ObservableList<Node> tmp = FXCollections.observableArrayList(displayBox.getChildren());
        tmp.remove(this.answerDisplay);
        tmp.remove(this.questionDisplay);
        displayBox.getChildren().setAll(tmp);
    }

    public void setQuestion(String question) {
        requireNonNull(question);
        questionDisplay.setText("Question: " + question);
    }

    public void showOutcome(String givenAnswer, String expectedAnswer, boolean isAnswerCorrect) {
        ObservableList<Node> tmp = FXCollections.observableArrayList(displayBox.getChildren());
        tmp.addAll(this.answerDisplay, this.questionDisplay);
        displayBox.getChildren().setAll(tmp);

        String answerToDisplay = String.format("Your Answer: %s\nExpected Answer: %s",
                givenAnswer, expectedAnswer);
        answerDisplay.setText(answerToDisplay);

        if (isAnswerCorrect) {
            outcomeDisplay.setText("You are correct!");
            outcomeDisplay.setStyle("-fx-text-fill: #608a5a;");
        } else {
            outcomeDisplay.setText("You are incorrect!");
            outcomeDisplay.setStyle("-fx-text-fill: #b30000;");
        }
    }

}
