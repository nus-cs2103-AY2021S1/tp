package nustorage.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import nustorage.model.record.FinanceRecord;

public class FinanceRecordCard extends UiPart<Region> {
    private static final String FXML = "FinanceListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final FinanceRecord record;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label transactionName;
    @FXML
    private Label amount;
    @FXML
    private Label dateAndTime;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public FinanceRecordCard(FinanceRecord record, int displayedIndex) {
        super(FXML);
        this.record = record;
        id.setText(displayedIndex + ". ");
        name.setText(Integer.toString(record.getID()));
        transactionName.setText(String.format("Transaction no.: #%d", record.getID()));
        amount.setText(String.format("Amount: $%.2f", record.getAmount()));
        dateAndTime.setText(String.format("Date and Time: %s", record.getDatetime().toString()));
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
        FinanceRecordCard card = (FinanceRecordCard) other;
        return id.getText().equals(card.id.getText())
                && record.equals(card.record);
    }
}
