package seedu.flashcard.ui;

import java.io.File;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.flashcard.model.flashcard.Diagram;
import seedu.flashcard.model.flashcard.Flashcard;

public class FlashcardQuestionCard extends UiPart<Region> {

    private static final String FXML = "FlashcardQuestionCard.fxml";

    public final Flashcard flashcard;

    @javafx.fxml.FXML
    private HBox cardPane;
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
    @FXML
    private ImageView diagram;
    @FXML
    private Label diagramErrorMessage;

    /**
     * Creates a {@code FlashcardQuestionCard} with the given {@code Flashcard} to display.
     */
    public FlashcardQuestionCard(Flashcard flashcard) {
        super(FXML);
        this.flashcard = flashcard;
        question.setText(flashcard.getQuestion().toString());
        category.setText(flashcard.getCategory().toString());
        String diagramFilePath = flashcard.getDiagram().toString();
        diagram.managedProperty().bind(diagram.visibleProperty());
        diagramErrorMessage.managedProperty().bind(diagramErrorMessage.visibleProperty());
        if (diagramFilePath.isEmpty()) {
            diagram.setVisible(false);
        } else {
            Image image = loadImage(diagramFilePath);
            if (image == null) {
                diagram.setVisible(false);
                diagramErrorMessage.setText("Diagram at " + diagramFilePath + " could not be loaded");
                diagramErrorMessage.setVisible(true);
            }
            diagram.setImage(image);
        }

        if (flashcard.getTags().size() > 0) {
            flashcard.getTags().stream()
                    .sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        } else {
            tags.setVisible(false);
        }

        if (flashcard.getRating().toString().length() > 0) {
            rating.setText(flashcard.getRating().toString());
            ratingIcon.setText(" " + TextIcon.STAR);
        } else {
            ratingPane.setVisible(false);
            ratingPane.managedProperty().bind(ratingPane.visibleProperty());
        }
        if (flashcard.isFavourite()) {
            favouriteIcon.setText(TextIcon.HEART);
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
        if (!(other instanceof FlashcardQuestionCard)) {
            return false;
        }

        // state check
        FlashcardQuestionCard card = (FlashcardQuestionCard) other;
        return flashcard.equals(card.flashcard);
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
