package trackitnus.ui.upcoming;

import java.time.LocalDate;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import trackitnus.logic.Logic;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.model.lesson.Lesson;
import trackitnus.model.task.Task;
import trackitnus.ui.UiPart;
import trackitnus.ui.task.TaskCard;

public class DayCard extends UiPart<Region> {

    private static final String FXML = "Upcoming/DayCard.fxml";

    public final Day day;
    private final int lessonRowHeight = 30;
    private final int taskRowHeight = 45;
    private final Logic logic;

    @FXML
    private ListView<Task> taskListView;
    @FXML
    private ListView<Lesson> lessonListView;
    @FXML
    private HBox cardPane;
    @FXML
    private Label date;

    /**
     * Constructor for DayCard
     *
     * @param day
     */
    public DayCard(Day day, ObservableList<Task> taskList, ObservableList<Lesson> lessonList, Logic logic) {
        super(FXML);
        this.day = day;
        this.logic = logic;

        if (date.equals(LocalDate.now())) {
            date.setText("TODAY - " + day.getSectionHeader());
        } else {
            date.setText(day.getSectionHeader());
        }

        if (lessonList.isEmpty()) {
            lessonListView.setStyle("-fx-background-color: transparent");
        }

        setUpLessonView(lessonList);
        setUpTaskView(taskList);
    }

    private void setUpLessonView(ObservableList<Lesson> lessonList) {
        lessonListView.prefHeightProperty().bind(Bindings.size(lessonList).multiply(lessonRowHeight).add(10));
        lessonListView.setItems(lessonList);
        lessonListView.setCellFactory(listView -> new LessonListViewCell());
    }

    private void setUpTaskView(ObservableList<Task> taskList) {
        taskListView.prefHeightProperty().bind(Bindings.size(taskList).multiply(taskRowHeight).add(35));
        taskListView.setItems(taskList);
        taskListView.setCellFactory(listView -> new TaskListViewCell());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DayCard)) {
            return false;
        }

        // state check
        DayCard card = (DayCard) other;
        return date.getText().equals(card.date.getText());
    }

    class TaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                try {
                    setGraphic(new TaskCard(task, logic.getTaskIndex(task).getOneBased()).getRoot());
                } catch (CommandException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class LessonListViewCell extends ListCell<Lesson> {
        @Override
        protected void updateItem(Lesson lesson, boolean empty) {
            super.updateItem(lesson, empty);

            if (empty || lesson == null) {
                setGraphic(null);
                setText(null);
            } else {
                try {
                    setGraphic(new UpcomingLessonCard(lesson, logic.getLessonIndex(lesson).getOneBased()).getRoot());
                } catch (CommandException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


