package seedu.address.ui.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.ui.util.ScheduleUiUtil.MARGIN_PER_MINUTE;
import static seedu.address.ui.util.ScheduleUiUtil.checkTimePattern;
import static seedu.address.ui.util.ScheduleUiUtil.getMarginFromDateTime;
import static seedu.address.ui.util.ScheduleUiUtil.toAmPmTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.task.Task;
import seedu.address.ui.UiPart;

public class TaskCell extends UiPart<Region> {
    private static final String FXML = "schedule/TaskCell.fxml";

    private static final double MINIMUM_CELL_HEIGHT = 13.0; // The height of title label.

    @FXML
    private Label startTime;

    @FXML
    private VBox taskHolder;

    @FXML
    private Label title;

    private Task task;

    /**
     * Construct a TaskCell from a {@Code task}
     */
    public TaskCell(Task task) {
        super(FXML);
        requireNonNull(task);
        this.task = task;

        // Set title and startTime
        title.setText(task.getTitle().title);
        startTime.setText(getTimeFromTask(task));

        // Violation of LoD, may need to improve.
        // Calculate the height of the cell;
        double height = getTaskCellHeight();
        taskHolder.setPrefHeight(height);

        //only shows the title when the duration is less than an hour.
        if (!(getTaskDuration() > 60)) {
            taskHolder.getChildren().remove(startTime);
            taskHolder.setAlignment(Pos.CENTER);
        }

    }


    /**
     * Method used to update the startTime.
     * @param startTimeStr must be in the form of hh:mm AM/PM
     */
    public void setStartTime(String startTimeStr) {
        assert checkTimePattern(startTimeStr);
        this.startTime.setText(startTimeStr);
    }

    /**
     * Calculate the margin top by the task for the TimeScale.
     */
    public double marginTop() {
        return getMarginFromDateTime(task.getStartTime());
    }

    /**
     * Method used to update the title.
     * @param titleStr
     */
    private void setTitle(String titleStr) {
        this.title.setText(titleStr);
    }

    private String getTimeFromTask(Task task) {
        LocalDateTime dateTime = task.getStartTime();
        DateTimeFormatter formmater = DateTimeFormatter.ofPattern("HH:mm");
        return toAmPmTime(formmater.format(dateTime));
    }

    private double getTaskCellHeight() {
        double calculatedVal = getTaskDuration() * MARGIN_PER_MINUTE;
        return calculatedVal < MINIMUM_CELL_HEIGHT ? MINIMUM_CELL_HEIGHT : calculatedVal;
    }

    private int getTaskDuration() {
        int duration = task.getTimeTaken();
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof TaskCell)) {
            return false;
        }

        return this.task.equals(((TaskCell) o).task);
    }

}
