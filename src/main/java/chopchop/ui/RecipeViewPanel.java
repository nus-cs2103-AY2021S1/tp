package chopchop.ui;

import java.util.Optional;

import chopchop.model.recipe.Recipe;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class RecipeViewPanel extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";

    private static final String FXML = "RecipeViewPanel.fxml";
    private static final String EMPTY_PROMPT = "You do not have any recipes yet.\nAdd one today (:";
    private static final String FILTER_NO_MATCH = "No matching recipes found";

    private static final int ROWS = 3;
    private static final int START_COL = -1;

    private ObservableList<Recipe> recipeObservableList;
    // Only 3 rows of recipes will be displayed.

    @FXML
    private ScrollPane recipePanel;

    @FXML
    private GridPane recipeGridView;

    /**
     * Creates a {@code RecipeView} with the given {@code ObservableList}.
     */
    public RecipeViewPanel(ObservableList<Recipe> filteredList) {
        super(FXML);

        recipeObservableList = filteredList;
        recipeObservableList.addListener((ListChangeListener<Recipe>) c -> fillDisplay());
        fillDisplay();
    }

    /**
     * Checks if the display contains any recipes, and fills the recipe grid view.
     */
    private void fillDisplay() {
        recipeGridView.getChildren().clear();

        this.getPlaceholderText().ifPresentOrElse(
            t -> this.recipeGridView.add(new TextDisplay(t).getRoot(), 0, 0),
            () -> this.populate()
        );
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
        for (int i = 0; i < recipeObservableList.size(); i++) {
            Recipe recipe = recipeObservableList.get(i);

            // Change from 0 based index to 1 based index
            RecipeCard recipeCard = new RecipeCard(recipe, i + 1);
            row = calculate_row(i);
            if (row == 0) {
                col++;
            }
            recipeGridView.add(recipeCard.getRoot(), col, row);
        }
    }

    /**
     * Gets the appropriate text to show in the pane if there are no recipes to show.
     * If there are recipes to show, returns an empty optional.
     */
    private Optional<String> getPlaceholderText() {
        if (this.recipeObservableList instanceof FilteredList<?>) {
            var src = ((FilteredList<?>) this.recipeObservableList).getSource();

            // if the source was not empty but the filter view is empty,
            // then we have no results.
            if (!src.isEmpty() && this.recipeObservableList.isEmpty()) {
                return Optional.of(FILTER_NO_MATCH);
            }
        }

        return this.recipeObservableList.isEmpty()
            ? Optional.of(EMPTY_PROMPT)
            : Optional.empty();
    }
}
