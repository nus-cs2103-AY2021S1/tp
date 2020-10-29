//@@author fall9x

package chopchop.ui;

import java.util.List;
import java.util.Optional;

import chopchop.model.ingredient.Ingredient;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;

public class IngredientViewPanel extends UiPart<Region> {
    private static final String FXML = "IngredientViewPanel.fxml";

    private static final String EMPTY_PROMPT = "You do not have any ingredients yet.\nAdd one today!";
    private static final String FILTER_NO_MATCH = "No matching ingredients found";

    private List<Ingredient> ingredientList;

    @FXML
    private ScrollPane ingredientPanel;

    @FXML
    private FlowPane ingredientGridView;

    /**
     * Creates a {@code RecipeView} with the given {@code List}.
     */
    public IngredientViewPanel(List<Ingredient> ingredientList) {
        super(FXML);

        this.ingredientList = ingredientList;
        this.fillDisplay();
    }

    /**
     * Checks if the display contains any recipes, and fills the recipe grid view.
     */
    private void fillDisplay() {
        ingredientGridView.getChildren().clear();

        this.getPlaceholderText().ifPresentOrElse(
            t -> this.ingredientPanel.setContent(new TextDisplay(t).getRoot()), this::populate
        );
    }

    /**
     * Populates the gridPane with recipes stored.
     */
    private void populate() {
        for (int i = 0; i < this.ingredientList.size(); i++) {
            var ingredient = this.ingredientList.get(i);
            var ingredientCard = new IngredientCard(ingredient, i + 1);
            this.ingredientGridView.getChildren().add(ingredientCard.getRoot());
        }
    }

    // TODO: copy-paste from RecipeViewPanel
    /**
     * Gets the appropriate text to show in the pane if there are no recipes to show.
     * If there are recipes to show, returns an empty optional.
     */
    private Optional<String> getPlaceholderText() {
        if (this.ingredientList instanceof FilteredList<?>) {
            var src = ((FilteredList<?>) this.ingredientList).getSource();

            // if the source was not empty but the filter view is empty,
            // then we have no results.
            if (!src.isEmpty() && this.ingredientList.isEmpty()) {
                return Optional.of(FILTER_NO_MATCH);
            }
        }

        return this.ingredientList.isEmpty()
            ? Optional.of(EMPTY_PROMPT)
            : Optional.empty();
    }
}
