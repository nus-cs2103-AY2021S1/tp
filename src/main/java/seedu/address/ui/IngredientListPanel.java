package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ingredient.Ingredient;

public class IngredientListPanel extends UiPart<Region> {
    private static final String FXML = "IngredientListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(IngredientListPanel.class);

    @FXML
    private Label header;

    @FXML
    private ListView<Ingredient> ingredientListView;

    /**
     * Creates an {@code IngredientListPanel} with the given {@code ObservableList}.
     */
    public IngredientListPanel(ObservableList<Ingredient> ingredientList) {
        super(FXML);
        header.setText("Ingredient Tracker");
        ingredientListView.setItems(ingredientList);
        ingredientListView.setCellFactory(listView -> new IngredientListViewCell());
    }

    class IngredientListViewCell extends ListCell<Ingredient> {

        @Override
        protected void updateItem(Ingredient ingredient, boolean empty) {
            super.updateItem(ingredient, empty);

            if (empty || ingredient == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new IngredientCard(ingredient).getRoot());
            }
        }
    }

}
