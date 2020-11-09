package seedu.address.ui.cards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.activity.Activity;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays information of an {@code Activity}.
 */
public class ActivityCard extends UiPart<Region> {

    private static final String FXML = "cards/ActivityListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     */

    public final Activity activity;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label address;
    @FXML
    private Label cost;
    @FXML
    private Label levelOfImportance;
    @FXML
    private Label activityDateTime;

    /**
     * Creates an {@code ActivityCard} with the given {@code Activity} and index to display.
     */
    public ActivityCard(Activity activity, int displayedIndex) {
        super(FXML);
        this.activity = activity;
        id.setText(displayedIndex + ". ");
        name.setText(activity.getName().toString());
        address.setText("Location: " + activity.getLocation().toString());
        cost.setText("Cost: $" + activity.getCost().toString());
        levelOfImportance.setText("Importance: " + activity.getLevelOfImportance().toString());
        activityDateTime.setText("Date & Time: " + activity.getActivityDateTime().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FriendCard)) {
            return false;
        }

        // state check
        ActivityCard card = (ActivityCard) other;
        return id.getText().equals(card.id.getText())
                && activity.equals(card.activity);
    }
}
