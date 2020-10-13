package chopchop.ui;

import chopchop.logic.Logic;
import chopchop.model.recipe.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * Controller class for the swappable display region.
 */
public class DisplayController extends UiPart<Region> {

    private static final String FXML = "DisplayPanel.fxml";
    private Logic logic;

    @FXML
    private StackPane displayAreaPlaceholder;

    /**
     * Creates a {@code DisplayController} with the given {@code Logic}.
     * @param logic
     */
    public DisplayController(Logic logic) {
        super(FXML);
        this.logic = logic;
    }

    /**
     * Displays the RecipeViewPanel on the swappable display region.
     */
    protected void displayRecipeList() {
        RecipeViewPanel recipeViewPanel = new RecipeViewPanel(logic.getFilteredRecipeList());
        displayAreaPlaceholder.getChildren().setAll(recipeViewPanel.getRoot());
    }

    /**
     * Displays the RecipeDisplay on the swappable display region.
     */
    protected void displayRecipe(Recipe recipe) {
        RecipeDisplay recipeDisplay = new RecipeDisplay(recipe);
        displayAreaPlaceholder.getChildren().setAll(recipeDisplay.getRoot());
    }

    /**
     * Displays the IngredientViewPanel on the swappable display region.
     */
    protected void displayIngredientList() {
        IngredientViewPanel ingredientViewPanel = new IngredientViewPanel(logic.getFilteredIngredientList());
        displayAreaPlaceholder.getChildren().setAll(ingredientViewPanel.getRoot());
    }


    /**
     * Displays the recipe panel.
     */
    @FXML
    public void handleRecipePanel(ActionEvent event) {
        displayRecipeList();
    }


    /**
     * Displays the recipe panel.
     */
    @FXML
    public void handleIngredientPanel(ActionEvent event) {
        displayIngredientList();
    }

    /**
     * Displays the recommendations panel.
     */
    @FXML
    public void handleRecommendations(ActionEvent event) {
        // To add more code.
    }

    /**
     * Displays the favourites panel.
     */
    @FXML
    public void handleFavourites(ActionEvent event) {
        // To add more code.
    }
}
