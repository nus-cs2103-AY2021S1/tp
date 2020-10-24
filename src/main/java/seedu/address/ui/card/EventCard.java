package seedu.address.ui.card;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
//import seedu.address.model.task.DateTime;
import seedu.address.model.task.Description;
//import seedu.address.model.task.State;
import seedu.address.model.task.Task;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class EventCard extends UiPart<Region> {

    private static final String FXML = "DeadlineCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private VBox card;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label from;
    @FXML
    private Label to;
    @FXML
    private HBox descriptionHolder;
    @FXML
    private Label description;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public EventCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        title.setText(task.getTitle().title);
        type.setText("haha");
        loadStatus(task);
        loadDescription(task);
        loadDateTime(task);
    }

    private boolean loadStatus(Task task) {
        statusSign.setSelected(true);
        status.setText("haha");
        return true;
    }

    private boolean loadDateTime(Task task) {
//        if (task.getDateTime().equals(DateTime.defaultDateTime())) {
//            card.getChildren().remove(dateTimeHolder);
//            return false;
//        } else {
//            dateTime.setText(task.getDateTime().toString());
//            return true;
//        }
        return false;
    }

    private boolean loadDescription(Task task) {
        if (task.getDescription().equals(Description.defaultDescription())) {
            card.getChildren().remove(descriptionHolder);
            return false;
        } else {
            description.setText(task.getDescription().value.toString());
            return true;
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeadlineCard)) {
            return false;
        }

        // state check
        DeadlineCard card = (DeadlineCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
