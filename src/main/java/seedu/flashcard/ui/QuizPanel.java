package seedu.flashcard.ui;

import javafx.event.EventHandler;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import seedu.flashcard.logic.Logic;
import seedu.flashcard.logic.commands.exceptions.CommandException;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * An UI component that handles the quiz mode.
 */
public class QuizPanel extends StudyPanel {

    public static final String EXIT_MESSAGE = "Exited Quiz mode";
    public static final String HELP_MESSAGE = "You are in quiz mode \n\u2193 show answer   q quit quiz mode";
    public static final int DOWN_ARROW = 40;
    public static final int Q_KEY = 81;
    public static final int Y_KEY = 89;
    public static final int N_KEY = 78;

    /**
     * Creates a {@code QuizPanel} that handles quiz mode.
     */
    public QuizPanel(Logic logic, MainWindow parent) {
        super(logic, parent);
        showFlashcard(studyManager.getCurrentFlashcard());
        EventHandler<KeyEvent> keyDownEventHandler = event -> {
            switch (event.getCode().getCode()) {
            case DOWN_ARROW: // down arrow key down
                FlashcardAnswerCard flashcardAnswerCard = new FlashcardAnswerCard(
                        studyManager.getCurrentFlashcard());
                showAnswer(flashcardAnswerCard);
                break;
            case Q_KEY: // 'q' key down
                exitStudyMode(EXIT_MESSAGE);
                break;
            case Y_KEY: // 'y' key down
                handleUserAnswerInput(true);
                break;
            case N_KEY:// 'n' key down
                handleUserAnswerInput(false);
                break;
            default:
                break;
            }
            event.consume();
        };
        handleStudy(keyDownEventHandler);
    }

    private void handleUserAnswerInput(boolean isCorrect) {
        if (!studyManager.isCurrentFlashcardReviewed()) {
            return;
        }
        try {
            studyManager.incrementCurrentFlashcardStatistics(isCorrect);
        } catch (CommandException e) {
            parent.setResultDisplayMessage(e.getMessage());
        }
        if (studyManager.hasNextFlashcard()) {
            showFlashcard(studyManager.getNextFlashcard());
        } else {
            exitStudyMode("End of quiz" + "\n" + EXIT_MESSAGE);
        }
    }

    @Override
    protected void showFlashcard(Flashcard flashcard) {
        super.showFlashcard(flashcard);
        parent.setResultDisplayMessage(HELP_MESSAGE);
    }

    @Override
    protected void showAnswer(FlashcardAnswerCard flashcardAnswerCard) {
        super.showAnswer(flashcardAnswerCard);
        studyManager.markCurrentFlashcardAsReviewed();
        parent.setResultDisplayMessage("Did you get the answer correct? Press y/n");
    }

}
