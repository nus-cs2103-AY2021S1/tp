package chopchop.ui;

import java.util.Comparator;

import chopchop.model.ingredient.Ingredient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;


public class IngredientCard extends UiPart<Region> {

    private static final String FXML = "IngredientCard.fxml";

    public final Ingredient ingredient;

    @FXML
    private Button ingredientCard;

    @FXML
    private Label ingredientName;

    @FXML
    private Label ingredientQty;

    @FXML
    private Label expiryDate;

    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code RecipeCard} with the given {@code Recipe}.
     */
    public IngredientCard(Ingredient ingredient) {
        super(FXML);
        this.ingredient = ingredient;
        ingredientName.setText(ingredient.getName());
        ingredientQty.setText(ingredient.getQuantity().toString());
        expiryDate.setText(ingredient.getExpiryDate().map(d -> String.format("Exp: %s", d))
                .orElse(""));
        ingredient.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.toString()))
                .forEach(tag -> tags.getChildren().add(new Label(tag.toString())));
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
        return ingredient.equals(card.ingredient);
    }
}
