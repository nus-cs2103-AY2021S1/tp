package seedu.address.ui;

import java.util.logging.Logger;

import com.jfoenix.controls.JFXListView;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.recipe.Ingredient;

/**
 * Panel containing the list of recipes.
 */
public class IngredientListPanel extends UiPart<Region> {
    private static final String FXML = "IngredientListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RecipeListPanel.class);

    @FXML
    private JFXListView<Ingredient> ingredientListView;

    /**
     * Creates a {@code RecipeListPanel} with the given {@code ObservableList}.
     */
    public IngredientListPanel(ObservableList<Ingredient> ingredientList) {
        super(FXML);
        ingredientListView.setItems(ingredientList);
        ingredientListView.setCellFactory(listView -> new IngredientListViewCell());
        ingredientListView.depthProperty().set(1);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Recipe} using a {@code RecipeCard}.
     */
    class IngredientListViewCell extends ListCell<Ingredient> {
        @Override
        protected void updateItem(Ingredient ingredient, boolean empty) {
            super.updateItem(ingredient, empty);

            if (empty || ingredient == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new IngredientCard(ingredient, getIndex() + 1).getRoot());
            }
        }
    }

}
