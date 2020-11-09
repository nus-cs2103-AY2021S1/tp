package seedu.flashcard.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * An UI component that displays information of a {@code Flashcard}.
 */
public class FlashcardViewCard extends UiPart<Region> {

    private static final String FXML = "FlashcardViewCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     */

    public final Flashcard flashcard;

    @FXML
    private StackPane questionViewPlaceholder;

    @FXML
    private StackPane answerViewPlaceholder;

    /**
     * Creates a {@code FlashcardListCard} with the given {@code Flashcard} to display.
     */
    public FlashcardViewCard(Flashcard flashcard, boolean showAnswer) {
        super(FXML);
        this.flashcard = flashcard;
        FlashcardQuestionCard questionCard = new FlashcardQuestionCard(flashcard);
        questionViewPlaceholder.getChildren().add(questionCard.getRoot());
        if (!showAnswer) {
            answerViewPlaceholder.setVisible(false);
        }
        FlashcardAnswerCard answerCard = new FlashcardAnswerCard(flashcard);
        answerViewPlaceholder.getChildren().add(answerCard.getRoot());

    }
}
