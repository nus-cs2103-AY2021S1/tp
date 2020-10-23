package seedu.flashcard.ui;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import seedu.flashcard.logic.StudyManager;
import seedu.flashcard.model.flashcard.Flashcard;

public class QuizPanel extends StudyPanel {

    public static final String EXIT_MESSAGE = "Exited Quiz mode";
    private final StudyManager studyManager;

    /**
     * Creates a {@code QuizPanel} that handles review mode.
     */
    public QuizPanel(ObservableList<Flashcard> flashcardList, MainWindow parent) {
        super(parent);
        studyManager = new StudyManager(flashcardList);
        showFlashcard(flashcardList.get(0));
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
                case 40: // up arrow key down
                    FlashcardAnswerCard flashcardAnswerCard = new FlashcardAnswerCard(
                            studyManager.getCurrentFlashcard());
                    showAnswer(flashcardAnswerCard);
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

}
