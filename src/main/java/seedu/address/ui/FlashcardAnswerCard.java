package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.model.flashcard.Flashcard;

public class FlashcardAnswerCard extends UiPart<Region> {

    private static final String FXML = "FlashcardAnswerCard.fxml";
    private static final String PREFIX_ANSWER = "Answer: ";

    public final Flashcard flashcard;

    @FXML
    private TextArea answer;

    /**
     * Creates a {@code FlashcardAnswerCard} with the given {@code Flashcard} and index to display.
     */
    public FlashcardAnswerCard(Flashcard flashcard) {
        super(FXML);
        this.flashcard = flashcard;
        answer.setText(PREFIX_ANSWER + flashcard.getAnswer().toString());
    }

}
