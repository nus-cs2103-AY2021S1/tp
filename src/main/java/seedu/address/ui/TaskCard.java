package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TodoListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private FlowPane taskTags;
    @FXML
    private Label priority;
    @FXML
    private Label date;
    @FXML
    private Label redStatus;
    @FXML
    private Label orangeStatus;
    @FXML
    private Label greenStatus;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label remainingDays;

    /**
     * Creates a {@code TaskCard} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        name.setText(task.getNameForUi());
        task.getTagsForUi().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> taskTags.getChildren().add(new Label(tag.tagName)));
        priority.setText(task.getPriorityForUi());
        date.setText(task.getDateForUi());
        if (task.getStatus().get().equals(Status.COMPLETED)) {
            greenStatus.setText("COMPLETED");
        } else if (task.isOverdue()) {
            redStatus.setText("OVERDUE");
        } else {
            orangeStatus.setText("NOT COMPLETED");
        }
        progressBar.setProgress(task.getProgressPercentageForUi());
        remainingDays.setText(task.getDaysUntilDeadlineForUi());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        TaskCard card = (TaskCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(((TaskCard) other).task);
    }
}
