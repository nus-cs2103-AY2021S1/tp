package quickcache.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import quickcache.model.flashcard.Difficulties;
import quickcache.model.flashcard.Flashcard;

/**
 * An UI component that displays information of a {@code Flashcard}.
 */
public class FlashcardDisplay extends UiPart<Region> {

    private static final String FXML = "FlashcardDisplay.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on QuickCache level 4</a>
     */

    public final Flashcard flashcard;

    @FXML
    private HBox cardPane;
    @FXML
    private Label question;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code FLashCardDisplay} with the given {@code Flashcard} and index to display.
     */
    public FlashcardDisplay(Flashcard flashcard, int displayedIndex) {
        super(FXML);
        this.flashcard = flashcard;
        id.setText(displayedIndex + ". ");
        question.setText(flashcard.getQuestion().toString());
        flashcard.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        if (!(flashcard.getDifficulty().getValue().equals(Difficulties.UNSPECIFIED.name()))) {
            Label difficultyLabel = new Label("Difficulty: " + flashcard.getDifficulty().value);
            if (flashcard.getDifficulty().getValue().equals(Difficulties.LOW.name())) {
                difficultyLabel.setStyle("-fx-background-color: #4BA314;");
            } else if (flashcard.getDifficulty().getValue().equals(Difficulties.MEDIUM.name())) {
                difficultyLabel.setStyle("-fx-background-color: #E5B915;");
            } else if (flashcard.getDifficulty().getValue().equals(Difficulties.HIGH.name())) {
                difficultyLabel.setStyle("-fx-background-color: #BE4C34;");
            } else {
                difficultyLabel.setStyle("-fx-background-color: #262626;");
            }
            tags.getChildren().add(difficultyLabel);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FlashcardDisplay)) {
            return false;
        }

        // state check
        FlashcardDisplay card = (FlashcardDisplay) other;
        return id.getText().equals(card.id.getText())
                && flashcard.equals(card.flashcard);
    }
}
