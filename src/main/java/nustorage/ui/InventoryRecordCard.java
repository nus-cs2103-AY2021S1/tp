package nustorage.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import nustorage.model.record.InventoryRecord;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class InventoryRecordCard extends UiPart<Region> {

    private static final String FXML = "InventoryCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final InventoryRecord record;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label quantity;
    @FXML
    private Label date;
    @FXML
    private Label time;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public InventoryRecordCard(InventoryRecord record, int displayedIndex) {
        super(FXML);
        this.record = record;
        id.setText(displayedIndex + ". ");
        name.setText(record.getItemName());
        quantity.setText(Integer.toString(record.getQuantity()));
        date.setText(record.getDate().toString());
        time.setText(record.getTime().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InventoryRecordCard)) {
            return false;
        }

        // state check
        InventoryRecordCard card = (InventoryRecordCard) other;
        return id.getText().equals(card.id.getText())
                && record.equals(card.record);
    }
}
