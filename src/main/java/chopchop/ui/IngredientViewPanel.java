package chopchop.ui;

import java.util.Optional;

import chopchop.model.ingredient.Ingredient;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class IngredientViewPanel extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "IngredientViewPanel.fxml";

    private static final String EMPTY_PROMPT = "You do not have any ingredients yet.\nAdd one today (:";
    private static final String FILTER_NO_MATCH = "No matching ingredients found";

    private static final int ROWS = 3;
    private static final int START_COL = -1;

    // Only 3 rows of recipes will be displayed.
    private ObservableList<Ingredient> ingredientObservableList;


    @FXML
    private ScrollPane ingredientPanel;

    @FXML
    private GridPane ingredientGridView;

    /**
     * Creates a {@code RecipeView} with the given {@code ObservableList}.
     */
    public IngredientViewPanel(ObservableList<Ingredient> ingredientList) {
        super(FXML);
        ingredientObservableList = ingredientList;
        ingredientObservableList.addListener((ListChangeListener<Ingredient>) c -> fillDisplay());
        fillDisplay();
    }

    private int calculate_row(int index) {
        return index % ROWS;
    }

    /**
     * Populates the gridPane with recipes stored.
     */
    private void populate() {
        int row;
        int col = START_COL;
        for (int i = 0; i < ingredientObservableList.size(); i++) {
            var ingredient = ingredientObservableList.get(i);
            var ingredientCard = new IngredientCard(ingredient, i + 1);

            row = calculate_row(i);
            if (row == 0) {
                col++;
            }
            ingredientGridView.add(ingredientCard.getRoot(), col, row);
        }
    }

    /**
     * Checks if the display contains any recipes, and fills the recipe grid view.
     */
    private void fillDisplay() {
        ingredientGridView.getChildren().clear();

        this.getPlaceholderText().ifPresentOrElse(
            t -> this.ingredientGridView.add(new TextDisplay(t).getRoot(), 0, 0), () -> this.populate()
        );
    }

    // TODO: copy-paste from RecipeViewPanel
    /**
     * Gets the appropriate text to show in the pane if there are no recipes to show.
     * If there are recipes to show, returns an empty optional.
     */
    private Optional<String> getPlaceholderText() {
        if (this.ingredientObservableList instanceof FilteredList<?>) {
            var src = ((FilteredList<?>) this.ingredientObservableList).getSource();

            // if the source was not empty but the filter view is empty,
            // then we have no results.
            if (!src.isEmpty() && this.ingredientObservableList.isEmpty()) {
                return Optional.of(FILTER_NO_MATCH);
            }
        }

        return this.ingredientObservableList.isEmpty()
            ? Optional.of(EMPTY_PROMPT)
            : Optional.empty();
    }
}
