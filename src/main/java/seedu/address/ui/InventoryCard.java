package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.item.Item;


/**
 * An UI component that displays information of a {@code Item}.
 */
public class InventoryCard extends UiPart<Region> {

    private static final String FXML = "InventoryListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Item item;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label quantity;
    @FXML
    private Label description;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ItemCode} with the given {@code Item} and index to display.
     */
    public InventoryCard(Item item, int displayedIndex) {
        super(FXML);
        this.item = item;
        name.setText(item.getName());
        quantity.setText("Quantity: " + item.getQuantity());
        description.setText("Description: " + item.getDescription());
        item.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InventoryCard)) {
            return false;
        }

        // state check
        InventoryCard card = (InventoryCard) other;
        return item.equals(card.item);
    }
}
