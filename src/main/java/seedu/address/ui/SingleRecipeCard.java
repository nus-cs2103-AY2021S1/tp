package seedu.address.ui;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import seedu.address.model.recipe.Instruction;
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
    public SingleRecipeCard(Recipe recipe) {
        super(FXML);
        this.recipe = recipe;
        name.setText(recipe.getName().fullName);
        ArrayList<Instruction> editedInstruction = new ArrayList<>();
        for (int i = 0; i < recipe.getInstruction().size(); i++) {
            Instruction instr = new Instruction((i + 1) + ") "
                    + recipe.getInstruction().get(i).toString() + ".\n");
            editedInstruction.add(instr);
        }
        instruction.setText(editedInstruction.stream()
                .map(item -> item.toString())
                .reduce("", (a, b) -> a + b).trim());
        Image rawImage = new Image(recipe.getRecipeImage().getValue(), 340, 0, true, true);
        PixelReader reader = rawImage.getPixelReader();
        WritableImage newImage = new WritableImage(reader, 0, 0, 310, 200);
        recipeImage.setImage(newImage);
        recipeImage.setPreserveRatio(true);

        //Responsive resizing
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            recipeImage.setFitWidth(getRoot().getWidth() - 10);
        };
        getRoot().widthProperty().addListener(stageSizeListener);

        ingredients.setText(recipe.getIngredient().stream()
                .map(item -> item.getQuantity() + " " + item.getValue())
                .reduce("", (a, b) -> b.equals("") ? a : a.trim().equals("") ? b : b + ", " + a));

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
        return recipe.equals(card.recipe);
    }
}
