package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * A handle to the {@code RecipeCard} in the GUI.
 */
public class RecipeCardHandle extends NodeHandle<Node> {

    private static final String NAME_FIELD_ID = "#recipeName";

    private final Label nameLabel;

    /**
     * Constructs a {@code RecipeCardHandle} with the given {@code recipeCardNode}.
     */
    public RecipeCardHandle(Node recipeCardNode) {
        super(recipeCardNode);

        nameLabel = getChildNode(NAME_FIELD_ID);
    }

    /**
     * Retrieves the name of the recipe in the recipe card.
     */
    public String getName() {
        return nameLabel.getText();
    }
}
