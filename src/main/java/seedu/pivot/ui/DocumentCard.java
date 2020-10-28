package seedu.pivot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.pivot.model.investigationcase.Document;


public class DocumentCard extends UiPart<Region> {

    private static final String FXML = "DocumentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Document document;

    @FXML
    private HBox cardPane;
    @FXML
    private Label filename;
    @FXML
    private Label id;
    @FXML
    private Label reference;

    /**
     * Creates a {@code DocumentCard} with the given {@code Case} and index to display.
     */
    public DocumentCard(Document document, int displayedIndex) {
        super(FXML);
        this.document = document;
        id.setText(displayedIndex + ". ");
        filename.setText(document.getName().toString());
        reference.setText(document.getReference().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DocumentCard)) {
            return false;
        }

        // state check
        DocumentCard card = (DocumentCard) other;
        return id.getText().equals(card.id.getText())
                && document.equals(card.document);
    }
}
