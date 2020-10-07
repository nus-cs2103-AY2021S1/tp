package chopchop.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Recipe}.
 */
public class RecipeCard extends UiPart<Region> {
    public final Recipe recipe;

    @FXML
    private Button cardPane
    @FXML
    private Label name;
    /**
     * Creates a {@code RecipeCard} with the given {@code Recipe}.
     */
    public RecipeCard(Recipe recipe) {
        this.recipe = recipe;
        name.setText(recipe.getName());
    }
}
