package seedu.address.ui;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.project.Deadline;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Project}.
 */
public class TaskDashboard extends UiPart<Region> {

    private static final String FXML = "TaskDashboard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on MainCatalogue level 4</a>
     */

    public final Task task;

    @FXML
    private HBox taskDashboardPane;
    @FXML
    private Label taskName;
    @FXML
    private Label taskDescription;
    @FXML
    private Label publishDate;
    @FXML
    private Label deadline;
    @FXML
    private Label progress;
    @FXML
    private Label header1;
    @FXML
    private FlowPane assignees;

    /**
     * Creates a {@code TaskDashboardCode} with the given {@code Task} to display.
     */
    public TaskDashboard(Optional<Task> task) {
        super(FXML);
        this.task = task.get();
        taskName.setText(this.task.getTaskName());
        taskDescription.setText("Task description: " + this.task.getDescription());
        publishDate.setText("Task published date: " + this.task.getPublishDate());
        deadline.setText("Task deadline: " + this.task.getDeadline().map(Deadline::toString).orElse("null"));
        progress.setText("Task progress: " + this.task.getProgress());
        header1.setText("Task assignees: ");
        this.task.getAssignees()
                .forEach(participation -> assignees.getChildren()
                        .add(new Label(participation)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskDashboard)) {
            return false;
        }

        // state check
        TaskDashboard card = (TaskDashboard) other;
        return task.equals(card.task);
    }
}
