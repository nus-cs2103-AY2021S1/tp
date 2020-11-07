package seedu.simplykitchen.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.simplykitchen.model.food.Food;

/**
 * An UI component that displays information of a {@code Food} which is expiring soon.
 */
public class ExpiringFoodCard extends UiPart<Region> {

    private static final String FXML = "ExpiringFoodCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">
     * The issue on FoodInventory level 4</a>
     */

    public final Food food;

    @FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label expiryDate;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code FoodCard} with the given {@code Food} and index to display.
     */
    public ExpiringFoodCard(Food food) {
        super(FXML);
        this.food = food;
        description.setText(food.getDescription().fullDescription);
        expiryDate.setText(food.getExpiryDate().value);
        food.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label newTag = new Label(tag.tagName);
                    newTag.setWrapText(true);
                    cardPane.widthProperty().addListener((obs, oldVal, newVal) -> {
                        newTag.setMaxWidth((double) newVal * 0.9);
                    });
                    tags.getChildren().add(newTag);
                });
        // Default min width of FlowPane is largest tag label, which may cause the tags to not wrap, so set to 0
        tags.setMinWidth(0);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ExpiringFoodCard)) {
            return false;
        }

        // state check
        ExpiringFoodCard card = (ExpiringFoodCard) other;
        return food.equals(card.food);
    }
}
