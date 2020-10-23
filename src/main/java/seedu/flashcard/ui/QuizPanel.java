package seedu.flashcard.ui;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import seedu.flashcard.logic.ReviewManager;
import seedu.flashcard.model.flashcard.Flashcard;

public class QuizPanel extends TestPanel {

    /**
     * Creates a {@code FlashcardListCard} with the given {@code Flashcard} and index to display.
     */
    public QuizPanel(ObservableList<Flashcard> flashcardList, MainWindow parent) {
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
