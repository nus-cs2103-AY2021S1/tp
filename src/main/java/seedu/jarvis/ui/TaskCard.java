package seedu.jarvis.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.jarvis.model.task.Task;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

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
    private Label description;
    @FXML
    private Label id;
    @FXML
    private Label dateTime;
    @FXML
    private Label taskType;

    /**
     * Creates a {@code TaskCard} with the given {@code Task}.
     */
    public TaskCard(Task task) {
        super(FXML);
        this.task = task;
        id.setText(task.getTaskId());
        description.setText(task.getDescription());
        dateTime.setText(task.getFormattedPossibleDateTime(task));
        taskType.setText(getTaskType(task));
        taskType.setStyle("-fx-text-fill: rgb(72,184,158); -fx-font-size: 10px;");
    }

    public String getTaskType(Task task) {
        String type = Task.getType(task);
        switch(type) {
        case "T":
            return "TODO";
        case "E":
            return "EVENT";
        case "D":
            return "DEADLINE";
        default:
            return "task";
        }
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
                && task.equals(card.task);
    }
}
