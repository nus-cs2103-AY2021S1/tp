package chopchop.ui;

import chopchop.model.recipe.Recipe;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

public class RecipeDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    private final Recipe recipe;

    @FXML
    private TextArea recipeName;

    @FXML
    private TextArea instructionDisplay;

    @FXML
    private TextArea ingredientDisplay;

    public RecipeDisplay(Recipe recipe) {
        super(FXML);
        this.recipe = recipe;
        display();
    }

    /**
     * Displays the recipe on the recipeDisplay.
     */
    private void display() {
        // Splits the recipe string representation into ingredients,
        String[] recipeComponents = recipe.toString().split("/");

        recipeName.clear();
        recipeName.setText(recipe.getName().toString());

        ingredientDisplay.clear();
        ingredientDisplay.setText(/* Ingredients. */ recipeComponents[0]);

        instructionDisplay.clear();
        instructionDisplay.setText(/* Steps. */ recipeComponents[1]);
    }
}
