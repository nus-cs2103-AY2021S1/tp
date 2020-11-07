package seedu.address.ui.panel;

import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.logic.Logic;
import seedu.address.model.StatisticsData;
import seedu.address.model.tag.Tag;
import seedu.address.ui.UiPart;
import seedu.address.ui.schedule.UpcomingSchedule;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class DisplayPanel extends UiPart<Region> {

    private static final String FXML = "panel/DisplayPanel.fxml";

    private Logic logic;
    private TaskListPanel taskListPanel;
    private LessonListPanel lessonListPanel;
    private CalendarPanel calendarPanel;
    private UpcomingSchedule upcomingSchedule;
    private StatsSummaryPanel statsSummaryPanel;
    private PieChart pieChart;
    private HashMap<String, String> tagColors;

    @FXML
    private TabPane tabPane;

    @FXML
    private StackPane taskListPanelPlaceholder;

    @FXML
    private StackPane lessonListPanelPlaceholder;

    @FXML
    private StackPane dataAnalysisPanelPlaceholder;

    @FXML
    private StackPane calendarPanelPlaceholder;

    @FXML
    private StackPane schedulePanelPlaceholder;

    @FXML
    private StackPane pieChartPlaceholder;

    @FXML
    private Pane statsSummaryPanelPlaceholder;

    @FXML
    private Tab lists;

    @FXML
    private Tab calendar;

    /**
     * Creates a display panel to hold all feature related contents
     * @param logic the logic object that manages the ui and the data
     */
    public DisplayPanel(Logic logic) {
        super(FXML);
        this.logic = logic;
        this.tagColors = new HashMap<>();
        fillInnerPart();
        listenerOnChange();
    }

    private void listenerOnChange() {
        addTabPaneListener();
    }

    private void addTabPaneListener() {
        tabPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            tabPane.setTabMinWidth((tabPane.getWidth() - 40) / tabPane.getTabs().size());
            tabPane.setTabMaxWidth((tabPane.getWidth() - 40) / tabPane.getTabs().size());
        });
    }

    private void fillInnerPart() {
        taskListPanel = new TaskListPanel(logic.getFilteredTaskList());
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());

        lessonListPanel = new LessonListPanel(logic.getFilteredLessonList());
        lessonListPanelPlaceholder.getChildren().add(lessonListPanel.getRoot());

        upcomingSchedule = new UpcomingSchedule(logic.getFilteredCalendarList());
        schedulePanelPlaceholder.getChildren().add(upcomingSchedule.getRoot());

        calendarPanel = new CalendarPanel(logic.getFilteredCalendarList(), upcomingSchedule);
        calendarPanelPlaceholder.getChildren().add(calendarPanel.getRoot());

        loadPieChart(logic.getStatisticsData());
        loadSummary(logic.getStatisticsData());
    }

    public void showLists() {
        tabPane.getSelectionModel().select(lists);
    }

    public void showCalendar() {
        tabPane.getSelectionModel().select(calendar);
    }

    /**
     * Update statistics
     * @param newStatsData new Stats data
     */
    public void updateStats(StatisticsData newStatsData) {
        fillPieChart(newStatsData);
        loadSummary(newStatsData);
    }

    private void loadPieChart(StatisticsData statsData) {
        pieChart = new PieChart();
        fillPieChart(statsData);
        pieChart.setMaxSize(400, 400);
        pieChart.setLegendVisible(false);
        pieChartPlaceholder.getChildren().add(pieChart);
        pieChartPlaceholder.getStylesheets().add("view/pieChart.css");
    }

    private void fillPieChart(StatisticsData statsData) {
        ObservableList<PieChart.Data> dataList = transformToPieChartData(statsData);
        pieChart.setData(dataList);
        for (PieChart.Data data : dataList) {
            assignePieColor(data);
        }
    }

    private void loadSummary(StatisticsData statsData) {
        statsSummaryPanel = new StatsSummaryPanel(statsData, tagColors);
        statsSummaryPanelPlaceholder.getChildren().add(statsSummaryPanel.getRoot());
    }

    private ObservableList<PieChart.Data> transformToPieChartData(StatisticsData statsData) {
        ObservableList<PieChart.Data> dataList = FXCollections.observableArrayList();
        for (Tag tag : statsData.getTags()) {
            int tagTotaltime = statsData.getTotalTime(tag);
            PieChart.Data data = tag.tagName.equals("")
                    ? new PieChart.Data("Untagged", tagTotaltime)
                    : new PieChart.Data(tag.tagName, tagTotaltime);
            dataList.add(data);
        }
        return dataList;
    }

    private void assignePieColor(PieChart.Data data) {
        String dataName = data.getName();

        if (!tagColors.containsKey(dataName)) {
            String newColor = getRandomColor();
            while (tagColors.containsValue(newColor)) {
                newColor = getRandomColor();
            }
            tagColors.put(dataName, newColor);
        }
        data.getNode().setStyle(
                "-fx-pie-color: " + tagColors.get(dataName) + ";"
        );
    }

    private String getRandomColor() {
        String letters = "0123456789ABCDEF";
        String color = "#00";
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                color += letters.charAt((int) Math.floor(Math.random() * 4) * 2);
                continue;
            }

            color += letters.charAt((int) Math.floor(Math.random() * 16));
        }
        return color;
    }

    public void endThread() {
        upcomingSchedule.endThread();
    }
}
