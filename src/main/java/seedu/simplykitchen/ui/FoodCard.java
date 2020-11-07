package seedu.simplykitchen.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.simplykitchen.model.food.ExpiryDate;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.model.food.Priority;

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
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">
     * The issue on FoodInventory level 4</a>
     */

    public final Food food;

    @FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label id;
    @FXML
    private Label expiryDate;
    @FXML
    private Label expiryLabel;
    @FXML
    private Label priority;
    @FXML
    private Label quantity;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code FoodCard} with the given {@code Food} and index to display.
     */
    public FoodCard(Food food, int displayedIndex) {
        super(FXML);
        this.food = food;
        id.setText(displayedIndex + ". ");
        description.setText(food.getDescription().fullDescription);
        priority.setText(food.getPriority().toString());
        setPriorityColor(food.getPriority().value);
        expiryDate.setText(food.getExpiryDate().value);
        setExpiryDateLabel();
        quantity.setText(food.getQuantity().toString());
        food.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private void setPriorityColor(Priority.Level level) {
        String priorityColor;
        switch(level) {
        case LOW:
            priorityColor = "#00802b";
            break;
        case MEDIUM:
            priorityColor = "#cc7a00";
            break;
        case HIGH:
            priorityColor = "#cc0000";
            break;
        default:
            throw new IllegalArgumentException("Invalid priority level");
        }
        priority.setStyle("-fx-background-color: " + priorityColor + ";");
    }

    private void setExpiryDateLabel() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d-M-yyyy");
        ExpiryDate dateToday = new ExpiryDate(LocalDate.now().format(dateFormat));
        ExpiryDate dateNextWeek = new ExpiryDate(LocalDate.now().plusDays(8).format(dateFormat));

        if (dateToday.isAfter(food.getExpiryDate())) {
            expiryLabel.setText("EXPIRED");
            expiryLabel.setStyle("-fx-background-color: #922B21; -fx-background-radius: 5;");
        } else if (dateNextWeek.isAfter(food.getExpiryDate())) {
            expiryLabel.setText("EXPIRING SOON");
            expiryLabel.setStyle("-fx-background-color: #9A7D0A; -fx-background-radius: 5;");
        } else {
            expiryLabel.setStyle("visibility: false");
        }

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
                && food.equals(card.food);
    }
}
