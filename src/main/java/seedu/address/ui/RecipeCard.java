package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.recipe.PrintableRecipe;

/**
 * An UI component that displays information of a {@code Recipe}.
 */
public class RecipeCard extends UiPart<Region> {

    private static final String FXML = "RecipeCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final PrintableRecipe printableRecipe;

    @FXML
    private HBox cardPane;
    @FXML
    private Label productName;
    @FXML
    private Label ingredients;
    @FXML
    private Label productQuantity;
    @FXML
    private Label description;

    /**
     * Creates a {@code RecipeCode} with the given {@code Recipe} and index to display.
     */
    public RecipeCard(PrintableRecipe printableRecipe, int displayedIndex) {
        super(FXML);
        this.printableRecipe = printableRecipe;
        if (printableRecipe.getOffset()) {
            int index = displayedIndex - 1;
            productName.setText(index + ". Recipe for: " + printableRecipe.getProductName());
        } else {
            productName.setText("Recipe for: " + printableRecipe.getProductName());
        }
        ingredients.setText("Ingredients: " + printableRecipe.getPrintableIngredients());
        productQuantity.setText("Produces: " + printableRecipe.getProductQuantity().toString() + " "
                + printableRecipe.getProductName());
        description.setText(printableRecipe.getDescription());
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
        RecipeCard card = (RecipeCard) other;
        return printableRecipe.equals(card.printableRecipe);
    }
}
