package seedu.flashcard.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.flashcard.model.flashcard.Flashcard;

public class FlashcardAnswerCard extends UiPart<Region> {

    private static final String FXML = "FlashcardAnswerCard.fxml";
    private static final String PREFIX_ANSWER = "Answer: ";

    public final Flashcard flashcard;

    @FXML
    private Label answer;
    @FXML
    private Label note;

    /**
     * Creates a {@code FlashcardAnswerCard} with the given {@code Flashcard} and index to display.
     */
    public FlashcardAnswerCard(Flashcard flashcard) {
        super(FXML);
        this.flashcard = flashcard;
        answer.setText(PREFIX_ANSWER + flashcard.getAnswer().toString());
        if (flashcard.getNote().toString().length() > 0) {
            note.setText("\u270e " + flashcard.getNote().toString());
        } else {
            note.setVisible(false);
        }
    }

}
