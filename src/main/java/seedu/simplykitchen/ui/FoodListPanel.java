package seedu.simplykitchen.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
public class FoodListPanel extends UiPart<Region> {
    private static final String NO_FOOD_ITEMS = "No food item found";
    private static final String FXML = "FoodListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FoodListPanel.class);

    @FXML
    private ListView<Food> foodListView;

    /**
     * Creates a {@code FoodListPanel} with the given {@code ObservableList}.
     */
    public FoodListPanel(ObservableList<Food> foodList) {
        super(FXML);
        Label placeHolderText = new Label(NO_FOOD_ITEMS);
        placeHolderText.setWrapText(true);
        placeHolderText.setStyle("-fx-text-fill: #FFFFFF");
        placeHolderText.setTextAlignment(TextAlignment.CENTER);
        foodListView.setPlaceholder(placeHolderText);
        foodListView.setItems(foodList);
        foodListView.setCellFactory(listView -> new FoodListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Food} using a {@code FoodCard}.
     */
    class FoodListViewCell extends ListCell<Food> {
        @Override
        protected void updateItem(Food food, boolean empty) {
            super.updateItem(food, empty);

            if (empty || food == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FoodCard(food, getIndex() + 1).getRoot());
            }
        }
    }

}
