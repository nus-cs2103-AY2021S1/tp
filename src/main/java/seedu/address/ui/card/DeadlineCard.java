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
import seedu.address.model.task.deadline.Deadline;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class DeadlineCard extends UiPart<Region> {

    private static final String FXML = "DeadlineCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     */

    public final Deadline deadline;

    @FXML
    private HBox cardPane;
    @FXML
    private VBox card;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label status;
    @FXML
    private CheckBox statusSign;
    @FXML
    private HBox durationHolder;
    @FXML
    private Label duration;
    @FXML
    private HBox dateTimeHolder;
    @FXML
    private Label dateTime;
    @FXML
    private HBox descriptionHolder;
    @FXML
    private Label description;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public DeadlineCard(Deadline deadline, int displayedIndex) {
        super(FXML);
        this.deadline = deadline;
        id.setText(displayedIndex + ". ");
        title.setText(deadline.getTitle().title);
        statusSign.setSelected(deadline.getStatus().isCompleted);
        status.setText(deadline.getStatus().toString());
        loadDescription(deadline);
        loadDateTime(deadline);
        loadDuration(deadline);
    }

    private boolean loadDateTime(Deadline deadline) {
        if (!deadline.getDeadlineDateTime().isFilled()) {
            card.getChildren().remove(dateTimeHolder);
            return false;
        } else {
            dateTime.setText(deadline.getDeadlineDateTime().toString());
            return true;
        }
    }

    private boolean loadDescription(Deadline deadline) {
        if (deadline.getDescription().equals(Description.defaultDescription())) {
            card.getChildren().remove(descriptionHolder);
            return false;
        } else {
            description.setText(deadline.getDescription().value);
            return true;
        }
    }

    private boolean loadDuration(Deadline deadline) {
        if (deadline.getDuration().isFilled()) {
            card.getChildren().remove(durationHolder);
            return false;
        } else {
            duration.setText(deadline.getTimeTaken() + " mins");
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
                && deadline.equals(card.deadline);
    }
}
