package seedu.flashcard.ui;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import seedu.flashcard.logic.Logic;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * An UI component that handles review mode.
 */
public class ReviewPanel extends StudyPanel {

    public static final String EXIT_MESSAGE = "Exited Review mode";
    public static final String HELP_MESSAGE = "You are in review mode \n\u2191 hide answer   \u2193 show answer   "
            + "\u2190 previous flashcard" + "   \u2192 next flashcard" + "   q quit review mode";
    public static final int RIGHT_ARROW = 39;
    public static final int LEFT_ARROW = 37;
    public static final int DOWN_ARROW = 40;
    public static final int UP_ARROW = 38;
    public static final int Q_KEY = 81;

    /**
     * Creates a {@code ReviewPanel} that handles review mode.
     */
    public ReviewPanel(Logic logic, MainWindow parent) {
        super(logic, parent);
        showFlashcard(studyManager.getCurrentFlashcard());
        EventHandler<KeyEvent> keyDownEventHandler = event -> {
            switch (event.getCode().getCode()) {
            case RIGHT_ARROW: // right arrow key down
                handleUserNextFlashcardInput();
                break;
            case LEFT_ARROW: // left arrow key down
                handleUserPreviousFlashcardInput();
                break;
            case DOWN_ARROW: // down arrow key down
                FlashcardAnswerCard flashcardAnswerCard = new FlashcardAnswerCard(
                        studyManager.getCurrentFlashcard());
                showAnswer(flashcardAnswerCard);
                break;
            case UP_ARROW: // up arrow key down
                hideAnswer();
                break;
            case Q_KEY: // 'q' key down
                exitStudyMode(EXIT_MESSAGE);
                break;
            default:
                break;
            }
            event.consume();
        };
        handleStudy(keyDownEventHandler);
    }

    private void handleUserNextFlashcardInput() {
        if (!studyManager.hasNextFlashcard()) {
            parent.setResultDisplayMessage("There are no more flashcards to be reviewed\n" + HELP_MESSAGE);
        } else {
            showFlashcard(studyManager.getNextFlashcard());
        }
    }

    private void handleUserPreviousFlashcardInput() {
        if (!studyManager.hasPreviousFlashcard()) {
            parent.setResultDisplayMessage("There are no more previous flashcards to be reviewed\n"
                    + HELP_MESSAGE);
        } else {
            showFlashcard(studyManager.getPrevFlashcard());
        }
    }

    @Override
    protected void showFlashcard(Flashcard flashcard) {
        super.showFlashcard(flashcard);
        parent.setResultDisplayMessage(HELP_MESSAGE);
    }

}
