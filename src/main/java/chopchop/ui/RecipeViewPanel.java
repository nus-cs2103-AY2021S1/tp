//@@author fall9x

package chopchop.ui;

import java.util.List;
import java.util.Optional;

import chopchop.model.recipe.Recipe;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;

public class RecipeViewPanel extends UiPart<Region> {
    private static final String FXML = "RecipeViewPanel.fxml";
    private static final String EMPTY_PROMPT = "You do not have any recipes yet.\nAdd one today!";
    private static final String FILTER_NO_MATCH = "No matching recipes found";

    private List<Recipe> recipeList;

    @FXML
    private ScrollPane recipePanel;

    @FXML
    private FlowPane recipeGridView;

    /**
     * Creates a {@code RecipeView} with the given {@code List}.
     */
    public RecipeViewPanel(List<Recipe> filteredList) {
        super(FXML);

        this.recipeList = filteredList;
        this.fillDisplay();
    }

    /**
     * Checks if the display contains any recipes, and fills the recipe grid view.
     */
    private void fillDisplay() {
        this.recipeGridView.getChildren().clear();

        this.getPlaceholderText().ifPresentOrElse(
            t -> this.recipePanel.setContent(new TextDisplay(t).getRoot()), this::populate
        );
    }

    /**
     * Populates the gridPane with recipes stored.
     */
    private void populate() {
        for (int i = 0; i < this.recipeList.size(); i++) {
            var recipe = this.recipeList.get(i);
            var recipeCard = new RecipeCard(recipe, i + 1);
            this.recipeGridView.getChildren().add(recipeCard.getRoot());
        }
    }

    /**
     * Gets the appropriate text to show in the pane if there are no recipes to show.
     * If there are recipes to show, returns an empty optional.
     */
    private Optional<String> getPlaceholderText() {
        if (this.recipeList instanceof FilteredList<?>) {
            var src = ((FilteredList<?>) this.recipeList).getSource();

            // if the source was not empty but the filter view is empty,
            // then we have no results.
            if (!src.isEmpty() && this.recipeList.isEmpty()) {
                return Optional.of(FILTER_NO_MATCH);
            }
        }

        return this.recipeList.isEmpty()
            ? Optional.of(EMPTY_PROMPT)
            : Optional.empty();
    }
}
