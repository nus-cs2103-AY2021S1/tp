package chopchop.ui;

import java.util.Optional;

import chopchop.model.recipe.Recipe;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;

public class RecommendationViewPanel extends UiPart<Region> {
    private static final String FXML = "RecommendationViewPanel.fxml";
    private static final String EMPTY_PROMPT = "You do not have any recipes yet.\nAdd one today!";

    private FilteredList<Recipe> recommendedRecipeObservableList;
    private FilteredList<Recipe> expiringRecipeObservableList;

    @FXML
    private ScrollPane recommendationPanel;

    @FXML
    private FlowPane recommendationGridView;

    /**
     * Creates a {@code RecipeView} with the given {@code ObservableList}.
     */
    public RecommendationViewPanel(FilteredList<Recipe> recommendationList,
                                   FilteredList<Recipe> expiringList) {
        super(FXML);

        this.recommendedRecipeObservableList = recommendationList;
        this.expiringRecipeObservableList = expiringList;
        this.recommendedRecipeObservableList.addListener((ListChangeListener<Recipe>) c -> this.fillDisplay());
        this.fillDisplay();
    }

    /**
     * Checks if the display contains any recipes, and fills the recipe grid view.
     */
    private void fillDisplay() {
        this.recommendationGridView.getChildren().clear();

        this.getPlaceholderText().ifPresentOrElse(
            t -> this.recommendationPanel.setContent(new TextDisplay(t).getRoot()), this::populate
        );
    }

    /**
     * TODO: Doesn't properly reset recipe filters when using GUI
     * Populates the gridPane with recipes stored.
     */
    private void populate() {
        if (!this.expiringRecipeObservableList.isEmpty()) {
            Recipe expiringRecipe = this.expiringRecipeObservableList.get(0);
            RecommendationCard expiringCard = new RecommendationCard(expiringRecipe,
                    this.expiringRecipeObservableList.getSourceIndex(this.expiringRecipeObservableList
                            .indexOf(expiringRecipe)) + 1);
            this.recommendationGridView.getChildren().add(expiringCard.getRoot());
        }

        for (int i = 0; i < this.recommendedRecipeObservableList.size(); i++) {
            var recipe = this.recommendedRecipeObservableList.get(i);
            var recipeCard = new RecipeCard(recipe, this.recommendedRecipeObservableList.getSourceIndex(i) + 1);
            this.recommendationGridView.getChildren().add(recipeCard.getRoot());
        }
    }

    /**
     * Gets the appropriate text to show in the pane if there are no recipes to show.
     * If there are recipes to show, returns an empty optional.
     */
    private Optional<String> getPlaceholderText() {
        return this.recommendedRecipeObservableList.isEmpty()
                ? Optional.of(EMPTY_PROMPT)
                : Optional.empty();
    }
}
