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
public class ExpiredFoodListPanel extends UiPart<Region> {
    private static final String NO_EXPIRED_ITEMS = "No expired food item in the inventory";
    private static final String FXML = "ExpiredFoodListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ExpiredFoodListPanel.class);

    @javafx.fxml.FXML
    private ListView<Food> expiredListView;

    /**
     * Creates a {@code FoodListPanel} with the given {@code ObservableList}.
     */
    public ExpiredFoodListPanel(ObservableList<Food> foodList) {
        super(FXML);
        Label placeHolderText = new Label(NO_EXPIRED_ITEMS);
        placeHolderText.setWrapText(true);
        placeHolderText.setStyle("-fx-text-fill: #FFFFFF");
        placeHolderText.setTextAlignment(TextAlignment.CENTER);
        expiredListView.setPlaceholder(placeHolderText);
        expiredListView.setItems(foodList);
        expiredListView.setCellFactory(listView -> new ExpiredListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Food} using a {@code FoodCard}.
     */
    class ExpiredListViewCell extends ListCell<Food> {
        @Override
        protected void updateItem(Food food, boolean empty) {
            super.updateItem(food, empty);

            if (empty || food == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ExpiredFoodListCard(food).getRoot());
            }
        }
    }

}
