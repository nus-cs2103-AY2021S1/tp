package seedu.address.ui;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.StatisticsData;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.tag.Tag;
import seedu.address.storage.Statistics;


public class DataAnalysisPanel extends UiPart<Region> {
    private static final String FXML = "DataAnalysisPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DataAnalysisPanel.class);

    private StatisticsData dataSet = Statistics.generateStatistics(LocalDate.now().minusDays(3),
            LocalDate.now().plusDays(3));

    @FXML
    private PieChart pieChart;

    /**
     * Creates a Data Analysis Panel UI.
     * @param lessonList lesson list to be analysed.
     */
    public DataAnalysisPanel(ObservableList<Lesson> lessonList) {
        super(FXML);
        loadPieChart(lessonList);
        loadBarChart(lessonList);
    }

    private void loadPieChart(ObservableList<Lesson> lessonList) {
        pieChart.setData(transformToPieChartData(lessonList));
    }

    private void loadBarChart(ObservableList<Lesson> lessonList) {

    }

    private ObservableList<PieChart.Data> transformToPieChartData(ObservableList<Lesson> lessonList) {
        ObservableList<PieChart.Data> dataList = FXCollections.observableArrayList();
        for (Lesson lesson : lessonList) {
            Tag tag = lesson.getTag();
            int tagTotaltime = dataSet.getTotalTime(tag);
            PieChart.Data data = new PieChart.Data(tag.tagName, tagTotaltime);
            System.out.println(tagTotaltime);
            dataList.add(data);
        }
        return dataList;
    }
}
