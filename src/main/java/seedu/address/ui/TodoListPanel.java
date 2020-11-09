package seedu.address.ui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.task.Task;
import seedu.address.model.task.TodoListUtil;


public class TodoListPanel extends UiPart<Region> {
    private static final String FXML = "TodoListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TodoListPanel.class);

    @FXML
    private ListView<Task> todoListView;
    @FXML
    private Label description;
    @FXML
    private PieChart completionPie;
    @FXML
    private BarChart<String, Integer> futureBar;

    /**
     * Creates a {@code TodoListPanel} with the given {@code ObservableList}.
     */
    public TodoListPanel(ObservableList<Task> todoList) {
        super(FXML);
        todoListView.setItems(todoList);
        todoListView.setCellFactory(listView -> new TodoListViewCell());
        updateCompletionChart(todoList);
        updateFutureBar(todoList);
    }

    /**
     * Updates the completion chart and the description with the required data.
     *
     * @param todoList todoList for filling the chart
     */
    public void updateCompletionChart(ObservableList<Task> todoList) {
        int[] statistics = TodoListUtil.getStatistics(todoList);
        int overdue = statistics[0];
        int notCompleted = statistics[1];
        int completed = statistics[2];

        List<PieChart.Data> dataAsList = new ArrayList<>();
        dataAsList.add(new PieChart.Data("Overdue [" + overdue + "]", overdue));
        dataAsList.add(new PieChart.Data("Not Completed [" + notCompleted + "]", notCompleted));
        dataAsList.add(new PieChart.Data("Completed [" + completed + "]", completed));

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(dataAsList);
        description.setText("Hey Buddy! You have " + (notCompleted + overdue) + " unfinished tasks!");

        completionPie.setData(pieChartData);
    }

    /**
     * Updates the future tasks chart with the required data.
     *
     * @param todoList todoList for filling the chart
     */
    public void updateFutureBar(ObservableList<Task> todoList) {
        HashMap<DayOfWeek, Integer> futureTasks = TodoListUtil.getFutureTasks(todoList);
        LocalDate currentDate = LocalDate.now();
        DayOfWeek day0 = currentDate.getDayOfWeek();
        DayOfWeek day1 = currentDate.plusDays(1).getDayOfWeek();
        DayOfWeek day2 = currentDate.plusDays(2).getDayOfWeek();
        DayOfWeek day3 = currentDate.plusDays(3).getDayOfWeek();
        DayOfWeek day4 = currentDate.plusDays(4).getDayOfWeek();
        DayOfWeek day5 = currentDate.plusDays(5).getDayOfWeek();
        DayOfWeek day6 = currentDate.plusDays(6).getDayOfWeek();

        List<XYChart.Series<String, Integer>> dataAsList = new ArrayList<>();

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        series.getData().add(new XYChart.Data<>(day0.toString(), futureTasks.get(day0)));
        series.getData().add(new XYChart.Data<>(day1.toString(), futureTasks.get(day1)));
        series.getData().add(new XYChart.Data<>(day2.toString(), futureTasks.get(day2)));
        series.getData().add(new XYChart.Data<>(day3.toString(), futureTasks.get(day3)));
        series.getData().add(new XYChart.Data<>(day4.toString(), futureTasks.get(day4)));
        series.getData().add(new XYChart.Data<>(day5.toString(), futureTasks.get(day5)));
        series.getData().add(new XYChart.Data<>(day6.toString(), futureTasks.get(day6)));

        dataAsList.add(series);

        ObservableList<XYChart.Series<String, Integer>> barChartData = FXCollections.observableArrayList(dataAsList);

        futureBar.setData(barChartData);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    class TodoListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TaskCard(task, getIndex() + 1).getRoot());
            }
        }
    }
}
