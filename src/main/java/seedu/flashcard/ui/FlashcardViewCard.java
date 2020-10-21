package seedu.flashcard.ui;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
     * Creates a {@code FlashcardListCard} with the given {@code Flashcard} and index to display.
     */
    public FlashcardViewCard(ObservableList<Flashcard> flashcardList, Flashcard flashcard, int displayedIndex) {
        super(FXML);
        this.flashcard = flashcard;
        FlashcardQuestionCard questionCard = new FlashcardQuestionCard(flashcard, displayedIndex);
        questionViewPlaceholder.getChildren().add(questionCard.getRoot());
        FlashcardAnswerCard answerCard = new FlashcardAnswerCard(flashcard);
        answerViewPlaceholder.getChildren().add(answerCard.getRoot());
        flashcardList.addListener(new ListChangeListener<Flashcard>() {
            @Override
            public void onChanged(Change<? extends Flashcard> c) {
                c.next();
                if (c.wasReplaced()) {
                    if (flashcard.equals((Flashcard) c.getRemoved().get(0))) {
                        FlashcardQuestionCard questionCard = new FlashcardQuestionCard((Flashcard) c.getAddedSubList().get(0), displayedIndex);
                        questionViewPlaceholder.getChildren().clear();
                        answerViewPlaceholder.getChildren().clear();
                        questionViewPlaceholder.getChildren().add(questionCard.getRoot());
                        FlashcardAnswerCard answerCard = new FlashcardAnswerCard(c.getAddedSubList().get(0));
                        answerViewPlaceholder.getChildren().add(answerCard.getRoot());
                    }
                } else if (c.wasRemoved()) {
                    for (Flashcard card : c.getRemoved()) {
                        if (flashcard.equals((Flashcard) card)) {
                            questionViewPlaceholder.getChildren().clear();
                            answerViewPlaceholder.getChildren().clear();
                        }
                    }
                } else {
                    return;
                }
            }
        });
    }
}
