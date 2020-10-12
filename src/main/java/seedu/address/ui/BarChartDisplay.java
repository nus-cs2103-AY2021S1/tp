package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.flashcard.Statistics;
import seedu.address.logic.commands.Feedback;

import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

public class BarChartDisplay extends UiPart<Region> {
    private static final String FXML = "BarChartDisplay.fxml";
    private final Logger logger = LogsCenter.getLogger(OptionListPanel.class);

    @FXML
    private BarChart<String, Number> barChartDisplay;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    public BarChartDisplay() {
        super(FXML);
    }

    public void displayStatistics(Feedback feedbackToUser) {
        requireNonNull(feedbackToUser);

        int timesTested = feedbackToUser.getStatistics().map(Statistics::getTimesTested).orElse(0);
        int timesTestedCorrect = feedbackToUser.getStatistics().map(Statistics::getTimesTestedCorrect).orElse(0);


        barChartDisplay.setTitle(feedbackToUser.getQuestion().map(Object::toString)
                .orElseGet(() -> "No Question to display"));

        xAxis.setLabel("Flashcard Statistics");
        yAxis.setLabel("Number of times");

        XYChart.Series<String, Number> data = new XYChart.Series<>();

        data.setName("Tested");

        data.getData().add(new XYChart.Data<>("Correct", timesTestedCorrect));
        data.getData().add(new XYChart.Data<>("Total", timesTested));

        barChartDisplay.getData().clear();
        barChartDisplay.getData().add(data);
    }
}
