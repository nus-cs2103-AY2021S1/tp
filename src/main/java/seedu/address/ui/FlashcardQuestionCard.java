package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.flashcard.Flashcard;

public class FlashcardQuestionCard extends UiPart<Region> {

    private static final String FXML = "FlashcardQuestionCard.fxml";

    public final Flashcard flashcard;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label question;
    @FXML
    private Label category;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code FlashcardQuestionCard} with the given {@code Flashcard} and index to display.
     */
    public FlashcardQuestionCard(Flashcard flashcard, int displayedIndex) {
        super(FXML);
        this.flashcard = flashcard;
        id.setText(displayedIndex + ". ");
        question.setText(flashcard.getQuestion().toString());
        category.setText(flashcard.getCategory().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FlashcardQuestionCard)) {
            return false;
        }

        // state check
        FlashcardQuestionCard card = (FlashcardQuestionCard) other;
        return id.getText().equals(card.id.getText())
                && flashcard.equals(card.flashcard);
    }
}
