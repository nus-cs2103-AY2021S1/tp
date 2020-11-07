package seedu.flashcard.ui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.flashcard.logic.Logic;
import seedu.flashcard.logic.StudyManager;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * An UI component that handles an abstract study mode.
 */
public abstract class StudyPanel extends UiPart<Region> {

    private static final String FXML = "StudyPanel.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     */

    protected EventHandler<KeyEvent> keyDownEventHandler;
    protected final MainWindow parent;
    protected final StudyManager studyManager;
    private int numberOfCards;

    @FXML
    private StackPane questionPlaceholder;

    @FXML
    private StackPane answerPlaceholder;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label progressLabel;

    /**
     * Creates a {@code StudyPanel} with the given parent {@code MainWindow}
     */
    public StudyPanel(Logic logic, MainWindow parent) {
        super(FXML);
        this.studyManager = new StudyManager(logic);
        this.parent = parent;
        this.numberOfCards = studyManager.getNumberOfCards();
        // Progress bar must start at 1st flashcard
        setProgress(1);
    }

    /**
     * Shows the current flashcard being studied.
     *
     * @param flashcard the FlashCard question to show.
     */
    protected void showFlashcard(Flashcard flashcard) {

        questionPlaceholder.getChildren().clear();
        FlashcardQuestionCard flashcardQuestionCard = new FlashcardQuestionCard(flashcard);
        questionPlaceholder.getChildren().add(flashcardQuestionCard.getRoot());
        answerPlaceholder.getChildren().clear();
        setProgress(studyManager.getCurrentIndex() + 1);
    }

    /**
     * Clears up current window to exit study mode and calls parent to exit study mode.
     *
     * @param exitReason Message to show user on exit.
     */
    protected void exitStudyMode(String exitReason) {
        parent.getRoot().removeEventFilter(KeyEvent.KEY_PRESSED, keyDownEventHandler);
        questionPlaceholder.getChildren().clear();
        answerPlaceholder.getChildren().clear();
        parent.exitStudyMode(exitReason);
    }

    /**
     * Shows the answer to the flashcard.
     *
     * @param flashcardAnswerCard the flashcard answer to show.
     */
    protected void showAnswer(FlashcardAnswerCard flashcardAnswerCard) {
        answerPlaceholder.getChildren().add(flashcardAnswerCard.getRoot());
    }

    /**
     * Hides the answer to the flashcard.
     */
    protected void hideAnswer() {
        answerPlaceholder.getChildren().clear();
    }

    /**
     * Executes study function by setting up event handler.
     */
    protected void handleStudy(EventHandler<KeyEvent> keyDownEventHandler) {
        this.keyDownEventHandler = keyDownEventHandler;
        parent.getRoot().addEventFilter(KeyEvent.KEY_PRESSED, keyDownEventHandler);
    };

    private void setProgress(int currentCardNumber) {
        progressBar.setProgress((double) currentCardNumber / (double) numberOfCards);
        progressLabel.setText(currentCardNumber + "/" + numberOfCards);
    }


}
