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
public class OrderListPanel extends UiPart<Region> {
    private static final String FXML = "OrderListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(OrderListPanel.class);

    @FXML
    private ListView<OrderItem> orderListView;

    /**
     * Creates a {@code OrderListPanel} with the given {@code ObservableList}.
     */
    public OrderListPanel(ObservableList<OrderItem> orderList) {
        super(FXML);
        orderListView.setItems(orderList);
        orderListView.setCellFactory(listView -> new OrderListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code OrderItem} using a {@code OrderCard}.
     */
    class OrderListViewCell extends ListCell<OrderItem> {
        @Override
        protected void updateItem(OrderItem orderItem, boolean empty) {
            super.updateItem(orderItem, empty);

            if (empty || orderItem == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new OrderCard(orderItem, getIndex() + 1).getRoot());
            }
        }
    }

}
