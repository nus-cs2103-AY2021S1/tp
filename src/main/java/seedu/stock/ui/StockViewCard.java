package seedu.stock.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a field of a {@code Stock}.
 * A field is either: Name, Serial Number, Source, Quantity, Location stored
 * in warehouse, or Notes represented as String.
 */
public class StockViewCard extends UiPart<Region> {

    private static final String FXML = "StockViewCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     */

    public final String field;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label fieldText;

    /**
     * Creates a {@code StockViewCard} with the given {@code String} and header to display.
     */
    public StockViewCard(String field, String header) {
        super(FXML);
        this.field = field;
        id.setText(header + ": ");
        fieldText.setText(field);
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
        return field.equals(card.field);
    }

}
