package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.order.OrderItem;

/**
 * An UI component that displays information of a {@code Food}.
 */
public class OrderItemCard extends UiPart<Region> {

    private static final String FXML = "OrderItemListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on VendorManager level 4</a>
     */

    public final OrderItem orderItem;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label price;
    @FXML
    private Label quantity;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code FoodCode} with the given {@code Food} and index to display.
     */
    public OrderItemCard(OrderItem orderItem, int displayedIndex) {
        super(FXML);
        this.orderItem = orderItem;
        id.setText(displayedIndex + ". ");
        name.setText(orderItem.getName());
        price.setText(orderItem.getPriceString());
        quantity.setText("x " + Integer.toString(orderItem.getQuantity()));
        orderItem.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label label = new Label(tag.tagName);
                    label.setEllipsisString("...");
                    label.setMaxWidth(150);
                    tags.getChildren().add(label);
                });
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrderItemCard)) {
            return false;
        }

        // state check
        OrderItemCard card = (OrderItemCard) other;
        return id.getText().equals(card.id.getText())
                && orderItem.equals(card.orderItem);
    }
}
