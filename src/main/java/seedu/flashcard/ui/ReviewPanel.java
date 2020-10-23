package seedu.flashcard.ui;

import javafx.event.EventHandler;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import seedu.flashcard.logic.StudyManager;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * An UI component that displays review function.
 */
public class ReviewPanel extends StudyPanel {

    public static final String EXIT_MESSAGE = "Exited review mode";
    public static final String HELP_MESSAGE = "You are in review mode \n\u2191 hide answer   \u2193 show answer   "
            + "\u2190 previous flashcard" + "   \u2192 next flashcard" + "   q quit review mode";

    /**
     * Creates a {@code ReviewPanel} that handles review mode.
     */
    public ReviewPanel(StudyManager studyManager, MainWindow parent) {
        super(studyManager, parent);
        showFlashcard(studyManager.getCurrentFlashcard());
        handleStudy();
    }

    /**
     * Executes review function.
     */
    @Override
    protected void handleStudy() {
        keyDownEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!(event.getTarget() instanceof TextInputControl)) {
                    return;
                }
                switch (event.getCode().getCode()) {
                case 39: // right arrow key down
                    if (!studyManager.hasNextFlashcard()) {
                        parent.setResultDisplayMessage("This are no more flashcards to be reviewed\n" + HELP_MESSAGE);
                    } else {
                        showFlashcard(studyManager.getNextFlashcard());
                    }
                    break;
                case 37: // left arrow key down
                    if (!studyManager.hasPreviousFlashcard()) {
                        parent.setResultDisplayMessage("This are no more previous flashcards to be reviewed\n"
                                + HELP_MESSAGE);
                    } else {
                        showFlashcard(studyManager.getPrevFlashcard());
                    }
                    break;
                case 40: // down arrow key down
                    FlashcardAnswerCard flashcardAnswerCard = new FlashcardAnswerCard(
                            studyManager.getCurrentFlashcard());
                    showAnswer(flashcardAnswerCard);
                    break;
                case 38: // up arrow key down
                    hideAnswer();
                    break;
                case 81: // 'q' key down
                    exitStudyMode(EXIT_MESSAGE);
                    break;
                default:
                    break;
                }
                event.consume();
            }
        };
        parent.getRoot().addEventFilter(KeyEvent.KEY_PRESSED, keyDownEventHandler);
    }

    @Override
    protected void showFlashcard(Flashcard flashcard) {
        super.showFlashcard(flashcard);
        parent.setResultDisplayMessage(HELP_MESSAGE);
    }

}
