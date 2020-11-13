package seedu.bagel.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.bagel.commons.core.LogsCenter;
import seedu.bagel.model.flashcard.Flashcard;

/**
 * An UI component that displays information of a {@code Flashcard}.
 */
public class FlashcardCard extends UiPart<Region> {

    private static final String FXML = "FlashcardList.fxml";
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Flashcard flashcard;
    private final Logger logger = LogsCenter.getLogger(FlashcardCard.class);
    private HostServices hostServices;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Hyperlink link;
    @FXML
    private FlowPane sets;
    @FXML
    private FlowPane tags;
    @FXML
    private VBox vBox;

    /**
     * Creates a {@code FlashcardCard} with the given {@code Flashcard} and index to display.
     */
    public FlashcardCard(Flashcard flashcard, int displayedIndex, HostServices hostServices, boolean isSingle) {
        super(FXML);
        this.flashcard = flashcard;
        this.hostServices = hostServices;
        id.setText(displayedIndex + ". ");
        title.setText(flashcard.getTitle().fullTitle);
        description.setText(flashcard.getDescription().value);
        setupLink(flashcard.getLink().value);

        if (isSingle) {
            id.setVisible(false);
            id.setManaged(false);
        } else {
            description.setVisible(false);
            description.setManaged(false);
            link.setVisible(false);
            link.setManaged(false);
        }

        sets.getChildren().add(new Label(flashcard.getFlashcardSet().value));

        flashcard.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Adds a {@code Hyperlink} with value {@code link} to display.
     */
    private void setupLink(String linkText) {
        link.setText(linkText);
        link.setOnMouseClicked(t -> {
            assert !linkText.isEmpty();
            logger.info("Link clicked: " + linkText);
            hostServices.showDocument(linkText);
        });

        if (linkText.isEmpty()) {
            link.setVisible(false);
            link.setManaged(false);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FlashcardCard)) {
            return false;
        }

        // state check
        FlashcardCard card = (FlashcardCard) other;
        return id.getText().equals(card.id.getText())
                && flashcard.equals(card.flashcard);
    }
}
