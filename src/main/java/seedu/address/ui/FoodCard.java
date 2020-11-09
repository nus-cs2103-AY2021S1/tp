package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.food.MenuItem;


/**
 * An UI component that displays information of a {@code Food}.
 */
public class FoodCard extends UiPart<Region> {

    private static final String FXML = "FoodListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on VendorManager level 4</a>
     */

    public final MenuItem item;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label price;
    @FXML
    private FlowPane tags;

    @FXML
    private ImageView imageView;

    /**
     * Creates a {@code FoodCode} with the given {@code Food} and index to display.
     */
    public FoodCard(MenuItem item, int displayedIndex) {
        super(FXML);
        this.item = item;
        id.setText(displayedIndex + ". ");
        name.setText(item.getName());
        price.setText(item.getPriceString());
        item.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        try {
            Image image = new Image(this.getClass().getResourceAsStream("/images/food/" + item.getFilePath()));
            imageView.setImage(image);
        } catch (NullPointerException e) {
            Image defaultImage = new Image(this.getClass()
                    .getResourceAsStream("/images/food/default-menu-item.jpg"));
            imageView.setImage(defaultImage);
        }

        imageView.setFitHeight(120);
        imageView.setFitWidth(120);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FoodCard)) {
            return false;
        }

        // state check
        FoodCard card = (FoodCard) other;
        return id.getText().equals(card.id.getText())
                && item.equals(card.item);
    }
}
