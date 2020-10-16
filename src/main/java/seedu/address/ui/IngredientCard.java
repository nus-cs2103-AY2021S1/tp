package seedu.address.ui;

//import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.recipe.Ingredient;

/**
 * An UI component that displays information of a {@code Recipe}.
 */
public class IngredientCard extends UiPart<Region> {

    private static final String FXML = "IngredientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on WishfulShrinking level 4</a>
     */

    public final Ingredient ingredient;
    private final int displayIndex;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label quantity;
    @FXML
    private Label ingredients;

    /**
     * Creates a {@code RecipeCode} with the given {@code Recipe} and index to display.
     */
    public IngredientCard(Ingredient ingredient, int displayedIndex) {
        super(FXML);
        this.ingredient = ingredient;
        this.displayIndex = displayedIndex;
        name.setText(ingredient.getValue());
        quantity.setText(ingredient.getQuantity());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecipeCard)) {
            return false;
        }

        // state check
        IngredientCard card = (IngredientCard) other;
        return this.displayIndex == card.displayIndex;
    }
}
