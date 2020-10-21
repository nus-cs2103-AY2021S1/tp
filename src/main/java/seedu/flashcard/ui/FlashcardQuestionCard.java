package seedu.flashcard.ui;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.flashcard.model.flashcard.Diagram;
import seedu.flashcard.model.flashcard.Flashcard;

public class FlashcardQuestionCard extends UiPart<Region> {

    private static final String FXML = "FlashcardQuestionCard.fxml";

    public final Flashcard flashcard;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label question;
    @FXML
    private Label category;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView diagram;

    /**
     * Creates a {@code FlashcardQuestionCard} with the given {@code Flashcard} and index to display.
     */
    public FlashcardQuestionCard(Flashcard flashcard, int displayedIndex) {
        super(FXML);
        this.flashcard = flashcard;
        id.setText(displayedIndex + ". ");
        question.setText(flashcard.getQuestion().toString());
        category.setText(flashcard.getCategory().toString());
        String diagramFilePath = flashcard.getDiagram().toString();
        if (diagramFilePath.isEmpty()) {
            diagram.setVisible(false);
        } else {
            Image image = loadImage(diagramFilePath);
            if (image == null) {
                diagram.setVisible(false);
            }
            diagram.setImage(image);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FlashcardQuestionCard)) {
            return false;
        }

        // state check
        FlashcardQuestionCard card = (FlashcardQuestionCard) other;
        return id.getText().equals(card.id.getText())
                && flashcard.equals(card.flashcard);
    }

    private Image loadImage(String diagramFilePath) {
        if (Diagram.isValidFile(diagramFilePath) && Diagram.isValidImageFileType(diagramFilePath)) {
            File imageFile = new File(diagramFilePath);
            Image image = new Image(imageFile.toURI().toString());
            return image;
        } else {
            return null;
        }
    }

}
