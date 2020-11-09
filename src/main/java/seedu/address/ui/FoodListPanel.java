package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.food.MenuItem;

/**
 * Panel containing the list of foods.
 */
public class FoodListPanel extends UiPart<Region> {
    private static final String FXML = "FoodListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FoodListPanel.class);

    @FXML
    private ListView<MenuItem> foodListView;

    /**
     * Creates a {@code FoodListPanel} with the given {@code ObservableList}.
     */
    public FoodListPanel(ObservableList<MenuItem> menuItemList) {
        super(FXML);
        foodListView.setItems(menuItemList);
        foodListView.setCellFactory(listView -> new MenuItemListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Food} using a {@code FoodCard}.
     */
    class MenuItemListViewCell extends ListCell<MenuItem> {
        @Override
        protected void updateItem(MenuItem item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FoodCard(item, getIndex() + 1).getRoot());
            }
        }
    }

}
