package chopchop.ui;

import chopchop.logic.Logic;
import chopchop.model.recipe.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class DisplayController extends UiPart<Region> {

    private static final String FXML = "DisplayPanel.fxml";
    private Logic logic;

    @FXML
    private StackPane displayAreaPlaceholder;

    public DisplayController(Logic logic) {
        super(FXML);
        this.logic = logic;
    }

    protected void displayRecipeList() {
        /*
        RecipeViewPanel recipeViewPanel = new RecipeViewPanel(logic.getFilteredRecipeList());
        displayListPlaceholder.getChildren().setAll(recipeViewPanel.getRoot());
         */
    }

    protected void displayRecipe(Recipe recipe) {
        RecipeDisplay recipeDisplay = new RecipeDisplay(recipe);
        displayAreaPlaceholder.getChildren().setAll(recipeDisplay.getRoot());
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
    public void handleIngredients(ActionEvent event) {
        // To add more code.
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
