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
public abstract class TestPanel extends UiPart<Region> {

    private static final String FXML = "TestPanel.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     */

    protected final ObservableList<Flashcard> flashcardList;
    protected final ReviewManager reviewManager;
    protected EventHandler<KeyEvent> keyDownEventHandler;
    protected final MainWindow parent;

    @FXML
    protected StackPane questionPlaceholder;

    @FXML
    protected StackPane answerPlaceholder;

    /**
     * Creates a {@code FlashcardListCard} with the given {@code Flashcard} and index to display.
     */
    public TestPanel(ObservableList<Flashcard> flashcardList, MainWindow parent) {
        super(FXML);
        this.flashcardList = flashcardList;
        this.parent = parent;
        reviewManager = new ReviewManager(flashcardList);
        FlashcardQuestionCard questionCard = new FlashcardQuestionCard(reviewManager.getCurrentFlashcard());
        questionPlaceholder.getChildren().add(questionCard.getRoot());
        handleReview();
    }

    /**
     * Makes window show the current flashcard being reviewed.
     *
     * @param flashcard      the FlashCard being reviewed.
     * @param displayedIndex the displayed index of the Flashcard being reviewed.
     */
    protected void showReviewFlashcard(Flashcard flashcard, int displayedIndex) {
        questionPlaceholder.getChildren().clear();
        FlashcardQuestionCard flashcardQuestionCard = new FlashcardQuestionCard(flashcard);
        questionPlaceholder.getChildren().add(flashcardQuestionCard.getRoot());
        answerPlaceholder.getChildren().clear();
    }

    protected void exitReviewMode(String exitReason) {
        parent.getRoot().removeEventFilter(KeyEvent.KEY_PRESSED, keyDownEventHandler);
        questionPlaceholder.getChildren().clear();
        answerPlaceholder.getChildren().clear();
        parent.exitReviewMode(exitReason);
    }

    /**
     * Executes review function.
     */
    protected abstract void handleReview();

}