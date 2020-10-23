package seedu.flashcard.ui;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.flashcard.logic.ReviewManager;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * An UI component that displays review function.
 */
public class ReviewPanel extends TestPanel {

    /**
     * Creates a {@code FlashcardListCard} with the given {@code Flashcard} and index to display.
     */
    public ReviewPanel(ObservableList<Flashcard> flashcardList, MainWindow parent) {
        super(flashcardList, parent);
    }

    /**
     * Executes review function.
     */
    @Override
    protected void handleReview() {
        keyDownEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!(event.getTarget() instanceof TextInputControl)) {
                    return;
                }
                switch (event.getCode().getCode()) {
                case 39: // right arrow key down
                    if (!reviewManager.hasNextFlashcard()) {
                        exitReviewMode(ReviewManager.NO_NEXT_FLASHCARD_MESSAGE + "\n");
                    } else {
                        showReviewFlashcard(reviewManager.getNextFlashcard(),
                                reviewManager.getCurrentIndex() + 1);
                    }
                    break;
                case 37: // left arrow key down
                    if (!reviewManager.hasPreviousFlashcard()) {
                        exitReviewMode(ReviewManager.NO_PREVIOUS_FLASHCARD_MESSAGE + "\n");
                    } else {
                        showReviewFlashcard(reviewManager.getPrevFlashcard(),
                                reviewManager.getCurrentIndex() + 1);
                    }
                    break;
                case 40: // up arrow key down
                    FlashcardAnswerCard flashcardAnswerCard = new FlashcardAnswerCard(reviewManager.getCurrentFlashcard());
                    answerPlaceholder.getChildren().add(flashcardAnswerCard.getRoot());
                    break;
                case 38: // down arrow key down
                    answerPlaceholder.getChildren().clear();
                    break;
                case 81: // 'q' key down
                    exitReviewMode("");
                    break;
                default:
                    break;
                }
                event.consume();
            }
        };
        parent.getRoot().addEventFilter(KeyEvent.KEY_PRESSED, keyDownEventHandler);
    }

}