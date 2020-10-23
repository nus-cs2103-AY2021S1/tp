package seedu.flashcard.ui;

import javafx.event.EventHandler;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import seedu.flashcard.logic.StudyManager;
import seedu.flashcard.logic.commands.exceptions.CommandException;
import seedu.flashcard.model.flashcard.Flashcard;

public class QuizPanel extends StudyPanel {

    public static final String EXIT_MESSAGE = "Exited Quiz mode";
    public static final String HELP_MESSAGE = "You are in quiz mode \n\u2193 show answer   q quit review mode";

    /**
     * Creates a {@code QuizPanel} that handles review mode.
     */
    public QuizPanel(StudyManager studyManager, MainWindow parent) {
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
                try {
                    switch (event.getCode().getCode()) {
                    case 40: // down arrow key down
                        FlashcardAnswerCard flashcardAnswerCard = new FlashcardAnswerCard(
                                studyManager.getCurrentFlashcard());
                        showAnswer(flashcardAnswerCard);
                        studyManager.markCurrentFlashcardAsReviewed();
                        parent.setResultDisplayMessage("Did you get the answer correct? Press y/n");
                        break;
                    case 81: // 'q' key down
                        exitStudyMode(EXIT_MESSAGE);
                        break;
                    case 89: // 'y' key down
                        handleUserAnswerInput(true);
                        break;
                    case 78:// 'n' key down
                        handleUserAnswerInput(false);
                        break;
                    default:
                        break;
                    }
                } catch (CommandException e) {
                    parent.setResultDisplayMessage(e.getMessage());
                }
                event.consume();
            }
        };
        parent.getRoot().addEventFilter(KeyEvent.KEY_PRESSED, keyDownEventHandler);
    }

    private void handleUserAnswerInput(boolean isCorrect) throws CommandException {
        if (studyManager.isCurrentFlashcardReviewed()) {
            studyManager.incrementCurrentFlashcardStatistics(isCorrect);
            if (!studyManager.hasNextFlashcard()) {
                exitStudyMode("End of quiz" + "\n" + EXIT_MESSAGE);
            } else {
                showFlashcard(studyManager.getNextFlashcard());

            }
        }
    }

    @Override
    protected void showFlashcard(Flashcard flashcard) {
        super.showFlashcard(flashcard);
        parent.setResultDisplayMessage(HELP_MESSAGE);
    }

}
