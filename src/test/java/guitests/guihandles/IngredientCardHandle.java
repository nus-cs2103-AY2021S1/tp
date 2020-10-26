package guitests.guihandles;


import chopchop.model.ingredient.Ingredient;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * A handle to the {@code IngredientCard} in the GUI.
 */
public class IngredientCardHandle extends NodeHandle<Node> {
    private static final String NAME_FIELD_ID = "#name";

    private final Label nameLabel;

    /**
     * Constructs a {@code IngredientCard} with the given {@code ingredientCardNode}.
     */
    public IngredientCardHandle(Node ingredientCardNode) {
        super(ingredientCardNode);
        nameLabel = getChildNode(NAME_FIELD_ID);
    }

    /**
     * Retrieves the name of the ingredient in the ingredient card.
     */
    public String getName() {
        return nameLabel.getText();
    }

    /**
     * Compares an ingredient card to verify if it is equal to an ingredient.
     */
    public boolean equals(Ingredient ingredient) {
        // Well we gotta change this depending on how we make our ingredient card more presentable uwu
        return getName().equals(ingredient.getName());
    }
}
