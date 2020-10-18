package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.consumption.Consumption;

/**
 * An UI component that displays information of a {@code Consumption}.
 */
public class ConsumptionCard extends UiPart<Region> {

    private static final String FXML = "ConsumptionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on WishfulShrinking level 4</a>
     */

    public final Consumption consumption;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label calories;
    @FXML
    private ImageView recipeImageView;


    /**
     * Creates a {@code ConsumptionCode} with the given {@code Consumption} and index to display.
     */
    public ConsumptionCard(Consumption consumption, int displayedIndex) {
        super(FXML);
        this.consumption = consumption;
        id.setText(displayedIndex + ". ");
        name.setText(consumption.getRecipe().getName().fullName);
        Image rawImage = new Image(consumption.getRecipe().getRecipeImage(), 340, 0, true, true);
        PixelReader reader = rawImage.getPixelReader();
        WritableImage newImage = new WritableImage(reader, 0, 0, 310, 150);
        recipeImageView.setImage(newImage);
        calories.setText(consumption.getRecipe().getCalories().value.toString() + " cal");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ConsumptionCard)) {
            return false;
        }

        // state check
        ConsumptionCard card = (ConsumptionCard) other;
        return id.getText().equals(card.id.getText())
                && consumption.equals(card.consumption);
    }
}

