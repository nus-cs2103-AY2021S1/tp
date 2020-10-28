package chopchop.ui;

import java.util.logging.Filter;

import chopchop.model.recipe.Recipe;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class RecommendationViewPanel extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "RecommendationViewPanel.fxml";
    private static final String EMPTY_PROMPT = "You do not have any recipes yet.\nAdd one today:)";

    private final TextDisplay textDisplay;

    private FilteredList<Recipe> recommendedRecipeObservableList;
    private FilteredList<Recipe> expiringRecipeObservableList;
    // Only 3 rows of recipes will be displayed.

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
        textDisplay = new TextDisplay(EMPTY_PROMPT);
        recommendedRecipeObservableList = recommendationList;
        expiringRecipeObservableList = expiringList;
        recommendedRecipeObservableList.addListener((ListChangeListener<Recipe>) c -> fillDisplay());
        fillDisplay();
    }

    /**
     * Checks if the display contains any recipes, and fills the recipe grid view.
     */
    private void fillDisplay() {
        recommendationGridView.getChildren().clear();
        if (isEmpty()) {
            displayPrompt();
        } else {
            populate();
        }
    }

    /**
     * Populates the gridPane with recipes stored.
     */
    private void populate() {
//        if (!expiringRecipeObservableList.isEmpty()) {
//            Recipe expiringRecipe = expiringRecipeObservableList.get(0);
//            RecommendationCard expiringCard = new RecommendationCard(expiringRecipe,
//                    expiringRecipeObservableList.getSourceIndex(expiringRecipeObservableList.indexOf(expiringRecipe)) + 1);
//            recommendationGridView.add(expiringCard.getRoot(), 0, 0, 1, 3);
//            col++;
//        }

        for (int i = 0; i < recommendedRecipeObservableList.size(); i++) {
            Recipe recipe = recommendedRecipeObservableList.get(i);
            // Change from 0 based index to 1 based index
            RecipeCard recipeCard = new RecipeCard(recipe, recommendedRecipeObservableList.getSourceIndex(i) + 1);
            recommendationGridView.getChildren().add(recipeCard.getRoot());
        }
    }

    private void displayPrompt() {
        recommendationGridView.getChildren().add(textDisplay.getRoot());
    }

    private boolean isEmpty() {
        return recommendationGridView.getChildren().contains(textDisplay) || recommendedRecipeObservableList.isEmpty();
    }
}
