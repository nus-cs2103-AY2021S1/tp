package seedu.address.ui;

import java.util.Comparator;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import seedu.address.model.delivery.Delivery;
import seedu.address.model.item.Item;

/**
 * An UI component that displays information of a {@code Item}.
 */
public class DeliveryCard extends UiPart<Region> {

    private static final String FXML = "ItemListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Delivery delivery;

    /**
     * Creates a {@code ItemCode} with the given {@code Item} and index to display.
     */
    public DeliveryCard(Delivery delivery, int displayedIndex) {
        super(FXML);
        this.delivery = delivery;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeliveryCard)) {
            return false;
        }

        // state check
        //TODO: Update this in the future
        DeliveryCard card = (DeliveryCard) other;

        return card == other;
    }
}
