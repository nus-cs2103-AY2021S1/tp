package seedu.address.ui;

import java.util.Comparator;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import seedu.address.model.recipe.Recipe;

/**
 * An UI component that displays information of a {@code Recipe}.
 */
public class SingleRecipeCard extends UiPart<HBox> {

    private static final String FXML = "SingleRecipe.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on WishfulShrinking level 4</a>
     */

    public final Recipe recipe;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label ingredients;
    @FXML
    private Label calories;
    @FXML
    private Label instruction;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView recipeImage;


    /**
     * Creates a {@code RecipeCode} with the given {@code Recipe} and index to display.
     */
    public SingleRecipeCard(Recipe recipe, int displayedIndex) {
        super(FXML);
        this.recipe = recipe;
        double maxWidth = 100;
        id.setText(displayedIndex + ". ");
        name.setText(recipe.getName().fullName);
        name.setMaxWidth(maxWidth);
        instruction.setText(recipe.getInstruction());
        instruction.setMaxWidth(maxWidth);

        Image rawImage = new Image(recipe.getRecipeImage(), 340, 0, true, true);
        recipeImage.setImage(rawImage);
        recipeImage.setPreserveRatio(true);

        //Responsive resizing
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            final double newMaxWidth = getRoot().getWidth();
            recipeImage.setFitWidth(newMaxWidth - 10);
            name.setMaxWidth(newMaxWidth);
            instruction.setMaxWidth(newMaxWidth);
            ingredients.setMaxWidth(newMaxWidth);
        };
        getRoot().widthProperty().addListener(stageSizeListener);

        ingredients.setText(recipe.getIngredient().stream()
                .map(item -> item.getQuantity() + " " + item.getValue())
                .reduce("", (a, b) -> b.equals("") ? a : b + ", " + a));
        ingredients.setMaxWidth(maxWidth);
        calories.setText(recipe.getCalories().value.toString() + " cal");

        this.recipe.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
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
        SingleRecipeCard card = (SingleRecipeCard) other;
        return id.getText().equals(card.id.getText())
                && recipe.equals(card.recipe);
    }
}
