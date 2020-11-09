package seedu.address.ui;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.quiz.Mcq;
import seedu.address.model.quiz.Question;
import seedu.address.model.quiz.Response;
import seedu.address.model.quiz.TrueFalse;

/**
 * An UI component that displays information of a {@code Flashcard}.
 */
public class ResponseCard extends UiPart<Region> {

    private static final String FXML = "ResponseListCard.fxml";
    private static final String LABEL_BACKGROUND_RED = "-fx-background-color: #cc3361;";
    private static final String LABEL_BACKGROUND_GREEN = "-fx-background-color: #018f6e;";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Question question;

    @FXML
    private HBox cardPane;
    @FXML
    private Label prompt;
    @FXML
    private Label id;
    @FXML
    private FlowPane markedOptions;

    /**
     * Creates a {@code PersonCode} with the given {@code Flashcard} and index to display.
     */
    public ResponseCard(Response response, int displayedIndex) {
        super(FXML);
        Question question = response.getQuestion();
        this.question = question;
        id.setText(displayedIndex + ". ");
        prompt.setText(question.getPrompt());
        if (question instanceof Mcq) {
            List<String> opt = ((Mcq) question).getOptions();
            int userResponseIndex = Integer.parseInt(response.getResponse());
            addOptions(opt, opt.get(((Mcq) question).getAnswer() - 1), opt.get(userResponseIndex - 1));
        } else {
            String correctAns = ((TrueFalse) question).getAnswer() ? "true" : "false";
            addOptions(TrueFalse.OPTIONS, correctAns, response.getResponse());
        }
    }

    /**
     * add option labels, changing the label to be red or green accordingly
     */
    private void addOptions(List<String> options, String correctAnswer, String userAnswer) {
        for (String op : options) {
            Label label = new Label(op);
            if (op.toLowerCase().equals(userAnswer.toLowerCase())) {
                label.setStyle(LABEL_BACKGROUND_RED);
            }
            if (op.toLowerCase().equals(correctAnswer.toLowerCase())) {
                label.setStyle(LABEL_BACKGROUND_GREEN);
            }
            markedOptions.getChildren().add(label);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ResponseCard)) {
            return false;
        }

        // state check
        ResponseCard card = (ResponseCard) other;
        return id.getText().equals(card.id.getText())
                && question.equals(card.question);
    }
}
