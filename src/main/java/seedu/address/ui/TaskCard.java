package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    public final int roomNumber;
    public final int taskNumber;
    public final int totalNumOfTasksInRoom;
    public final Task task;

    private final String roomIdText = "[Room %1$d]";
    private final String taskIdText = "Task %1$d of %2$d";
    private final String dueAtText = "Due Date: %1$s";

    @FXML
    private HBox cardPane;
    @FXML
    private Label roomId;
    @FXML
    private Label taskId;
    @FXML
    private TextField description;
    @FXML
    private Label dueAt;

    /**
     * Creates a {@code TaskCard} with the given {@code Task}.
     *
     * @param roomNumber The room number of the room in which the task is found.
     * @param taskNumber The task number of the task, which is also its index relative to other tasks in the room.
     * @param totalNumOfTasksInRoom The total number of tasks in the room which the task is found.
     * @param task The task.
     */
    public TaskCard(int roomNumber, int taskNumber, int totalNumOfTasksInRoom, Task task) {
        super(FXML);
        this.roomNumber = roomNumber;
        this.taskNumber = taskNumber;
        this.totalNumOfTasksInRoom = totalNumOfTasksInRoom;
        this.task = task;
        setTaskCard(task);
    }

    /**
     * Sets the labels in {@code TaskCard} based on the information in {@code Task}.
     *
     * @param task The task from which the information is retrieved to set the labels.
     */
    private void setTaskCard(Task task) {
        roomId.setText(String.format(roomIdText, roomNumber));
        taskId.setText(String.format(taskIdText, taskNumber, totalNumOfTasksInRoom));
        description.setText(task.getDescription().toString());
        dueAt.setText(String.format(dueAtText, task.getDueAt().toString()));
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
        return roomNumber == ((TaskCard) other).roomNumber
                && taskNumber == ((TaskCard) other).taskNumber
                && task.equals(((TaskCard) other).task);
    }
}
