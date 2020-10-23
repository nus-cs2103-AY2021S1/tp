package seedu.flashcard.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * An UI component that displays information of a {@code Flashcard}.
 */
public class FlashcardListCard extends UiPart<Region> {

    private static final String FXML = "FlashcardListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     */

    public final Flashcard flashcard;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label question;
    @FXML
    private Label category;
    @FXML
    private TextFlow ratingPane;
    @FXML
    private Text rating;
    @FXML
    private Text ratingIcon;
    @FXML
    private Text favouriteIcon;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code FlashcardListCard} with the given {@code Flashcard} and index to display.
     */
    public FlashcardListCard(Flashcard flashcard, int displayedIndex) {
        super(FXML);
        this.flashcard = flashcard;
        id.setText(displayedIndex + ". ");
        question.setText(flashcard.getQuestion().toString());
        category.setText(flashcard.getCategory().toString());
        if (flashcard.getRating().toString().length() > 0) {
            rating.setText(flashcard.getRating().toString());
            ratingIcon.setText(" \uD83D\uDFCA");
        } else {
            ratingPane.setVisible(false);
            ratingPane.managedProperty().bind(ratingPane.visibleProperty());
        }
        if (flashcard.getTags().size() > 0) {
            flashcard.getTags().stream()
                    .sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        } else {
            tags.setVisible(false);
        }
        if (flashcard.isFavourite()) {
            favouriteIcon.setText("\u2764");
        } else {
            favouriteIcon.setVisible(false);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FlashcardListCard)) {
            return false;
        }

        // state check
        FlashcardListCard card = (FlashcardListCard) other;
        return id.getText().equals(card.id.getText())
                && flashcard.equals(card.flashcard);
    }
}
