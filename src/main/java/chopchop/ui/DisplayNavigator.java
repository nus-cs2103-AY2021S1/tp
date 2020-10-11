package chopchop.ui;

import chopchop.model.recipe.Recipe;

/**
 * Utility class for controlling navigation between displays.
 *
 * All methods on the navigator are static to facilitate
 * simple access from the different displays.
 */
public class DisplayNavigator {

    private static DisplayController displayController;

    protected static void setDisplayController(DisplayController displayController) {
        DisplayNavigator.displayController = displayController;
    }

    protected static void loadRecipePanel() {
        displayController.displayRecipeList();
    }

    protected static void loadRecipeDisplay(Recipe recipe) {
        displayController.displayRecipe(recipe);
    }
}
