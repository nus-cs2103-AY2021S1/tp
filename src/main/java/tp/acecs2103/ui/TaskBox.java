package tp.acecs2103.ui;

import java.util.Objects;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import tp.acecs2103.commons.core.LogsCenter;
import tp.acecs2103.model.task.Task;


/**
 * Encapsulate a task box to display information for a particular task.
 */
public class TaskBox extends UiPart<Region> {
    private static final String FXML = "TaskBox.fxml";
    public final Task task;
    private final Logger logger = LogsCenter.getLogger(TaskBox.class);

    @FXML
    private VBox box;
    @FXML
    private HBox cardPane;
    @FXML
    private Label index;
    @FXML
    private Label weekNumber;
    @FXML
    private Label description;
    @FXML
    private Label officialDeadline;
    @FXML
    private Label customizedDeadline;
    @FXML
    private Label remark;
    /**
     * Creates a TaskBox object.
     * @param task is a task object.
     */
    public TaskBox(Task task) {
        super(FXML);
        this.task = task;

        if (!Objects.isNull(task.getIndex())) {
            index.setText("Index: " + task.getIndex());
        }
        if (!Objects.isNull(task.getWeekNumber())) {
            weekNumber.setText("Week number: " + task.getWeekNumber().toString());
        }
        if (!Objects.isNull(task.getDescription())) {
            description.setText("Description: " + task.getDescription());
        }
        if (!Objects.isNull(task.getOfficialDeadline())) {
            officialDeadline.setText("Official Deadline: " + task.getOfficialDeadline().toString());
        }
        if (!Objects.isNull(task.getCustomizedDeadline())) {
            customizedDeadline.setText("Customized Deadline: " + task.getCustomizedDeadline().toString());
        }
        if (!Objects.isNull(task.getRemark())) {
            remark.setText("Remark: " + task.getRemark());
        }

        if (task.isDone()) {
            logger.info("done");
            box.getStyleClass().add("done-task");
        } else {
            if (task.isOverdue()) {
                logger.info("overdue");
                box.getStyleClass().add("overdue-task");
            } else {
                logger.info("pending");
                box.getStyleClass().add("pending-task");
            }
        }

    }
}
