package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.order.OrderItem;

/**
 * Panel containing the list of OrderItem.
 */
public class OrderItemListPanel extends UiPart<Region> {
    private static final String FXML = "OrderItemListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(OrderItemListPanel.class);

    @FXML
    private ListView<OrderItem> orderItemListView;

    /**
     * Creates a {@code OrderListPanel} with the given {@code ObservableList}.
     */
    public OrderItemListPanel(ObservableList<OrderItem> orderList) {
        super(FXML);
        orderItemListView.setItems(orderList);
        orderItemListView.setCellFactory(listView -> new OrderItemListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code OrderItem} using a {@code OrderCard}.
     */
    class OrderItemListViewCell extends ListCell<OrderItem> {
        @Override
        protected void updateItem(OrderItem orderItem, boolean empty) {
            super.updateItem(orderItem, empty);

            if (empty || orderItem == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new OrderItemCard(orderItem, getIndex() + 1).getRoot());
            }
        }
    }

}
