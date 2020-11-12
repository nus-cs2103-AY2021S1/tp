package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.ingredient.Ingredient;

public class IngredientCard extends UiPart<Region> {

    private static final String FXML = "IngredientListCard.fxml";

    public final Ingredient ingredient;

    @FXML
    private Label display;

    /**
     * Creates a {@code IngredientCard} with the given {@code Ingredient} to display.
     */
    public IngredientCard(Ingredient ingredient) {
        super(FXML);
        this.ingredient = ingredient;
        display.setText(ingredient.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IngredientCard)) {
            return false;
        }

        // state check
        IngredientCard card = (IngredientCard) other;
        return ingredient.equals(((IngredientCard) other).ingredient);
    }

}
