package seedu.stock.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Stock}.
 */
public class StockViewCard extends UiPart<Region> {

    private static final String FXML = "StockViewCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final String note;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label noteText;

    /**
     * Creates a {@code NoteCard} with the given {@code Note} and index to display.
     */
    public StockViewCard(String note, String header) {
        super(FXML);
        this.note = note;
        id.setText(header + ":");
        noteText.setText(note);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StockViewCard)) {
            return false;
        }

        // state check
        StockViewCard card = (StockViewCard) other;
        return note.equals(card.note);
    }
}
