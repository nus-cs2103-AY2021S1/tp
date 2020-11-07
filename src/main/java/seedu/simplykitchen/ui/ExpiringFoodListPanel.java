package seedu.simplykitchen.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import seedu.simplykitchen.commons.core.LogsCenter;
import seedu.simplykitchen.model.food.Food;

/**
 * Panel containing the list of food items.
 */
public class ExpiringFoodListPanel extends UiPart<Region> {
    private static final String NO_EXPIRING_ITEMS = "No food item is expiring within the next 7 days";
    private static final String FXML = "ExpiringFoodListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ExpiringFoodListPanel.class);

    @javafx.fxml.FXML
    private ListView<Food> expiryListView;

    /**
     * Creates a {@code FoodListPanel} with the given {@code ObservableList}.
     */
    public ExpiringFoodListPanel(ObservableList<Food> foodList) {
        super(FXML);
        Label placeHolderText = new Label(NO_EXPIRING_ITEMS);
        placeHolderText.setWrapText(true);
        placeHolderText.setStyle("-fx-text-fill: #FFFFFF");
        placeHolderText.setTextAlignment(TextAlignment.CENTER);
        expiryListView.setPlaceholder(placeHolderText);
        expiryListView.setItems(foodList);
        expiryListView.setCellFactory(listView -> new ExpiryListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Food} using a {@code FoodCard}.
     */
    class ExpiryListViewCell extends ListCell<Food> {
        @Override
        protected void updateItem(Food food, boolean empty) {
            super.updateItem(food, empty);

            if (empty || food == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ExpiringFoodCard(food).getRoot());
            }
        }
    }

}
