package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.StatisticsData;
import seedu.address.model.tag.Tag;


public class DataAnalysisPanel extends UiPart<Region> {
    private static final String FXML = "DataAnalysisPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DataAnalysisPanel.class);

    private StatsSummaryPanel statsSummaryPanel;

    @FXML
    private StackPane pieChartPlaceholder;
    @FXML
    private PieChart pieChart;
    @FXML
    private Pane statsSummaryPanelPlaceholder;

    /**
     * Creates a Data Analysis Panel UI.
     * @param statsData lesson list to be analysed.
     */
    public DataAnalysisPanel(StatisticsData statsData) {
        super(FXML);
        loadPieChart(statsData);
        loadSummary(statsData);
    }

    private void loadPieChart(StatisticsData statsData) {
        pieChart.setData(transformToPieChartData(statsData));
        pieChart.setLabelsVisible(false);
        pieChart.setTitle("Breakdown of time spent");
    }

    private void loadSummary(StatisticsData statsData) {
        statsSummaryPanel = new StatsSummaryPanel(statsData);
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
}
