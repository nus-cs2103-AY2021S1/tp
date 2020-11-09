package quickcache.ui;

import static java.util.Objects.requireNonNull;

import java.util.Set;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import quickcache.commons.core.LogsCenter;
import quickcache.logic.commands.Feedback;
import quickcache.model.flashcard.Statistics;

public class PieChartDisplay extends UiPart<Region> {
    private static final String FXML = "PieChartDisplay.fxml";
    private final Logger logger = LogsCenter.getLogger(PieChartDisplay.class);

    @FXML
    private PieChart pieChartDisplay;

    /**
     * Creates a {@code PieChartDisplay}.
     */
    public PieChartDisplay() {
        super(FXML);
    }

    /**
     * Displays the statistics of the flashcard to the user.
     *
     * @param feedbackToUser the feedback to be displayed to the user.
     */
    public void displayStatistics(Feedback feedbackToUser) {
        requireNonNull(feedbackToUser);

        int timesTested = feedbackToUser.getStatistics().map(Statistics::getTimesTested).orElse(0);
        int timesTestedCorrect = feedbackToUser.getStatistics().map(Statistics::getTimesTestedCorrect).orElse(0);
        double correctRate = feedbackToUser.getStatistics().map(Statistics::getCorrectRate).orElse(0.0);
        double incorrectRate = correctRate == 0.0 && timesTested == 0 ? 0.0 : 100.0 - correctRate;

        pieChartDisplay.setTitle("Flashcard Statistics");

        String itemCorrect = String.format("Correct (%.2f%%)", correctRate);
        String itemIncorrect = String.format("Incorrect (%.2f%%)", incorrectRate);

        PieChart.Data correctPie = new PieChart.Data(itemCorrect, timesTestedCorrect);
        PieChart.Data wrongPie = new PieChart.Data(itemIncorrect, timesTested - timesTestedCorrect);

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(correctPie, wrongPie);

        pieChartDisplay.getData().clear();
        pieChartDisplay.setData(data);

        correctPie.getNode().setStyle("-fx-pie-color: #86d948;");
        wrongPie.getNode().setStyle("-fx-pie-color: #e84520;");


        //Solution below adapted from https://gist.github.com/jewelsea/1422628
        Set<Node> items = pieChartDisplay.lookupAll("Label.chart-legend-item");
        int i = 0;
        // these colors came from caspian.css .default-color0..4.chart-pie
        Color[] colors = { Color.web("#86d948"), Color.web("#e84520")};
        for (Node item : items) {
            Label label = (Label) item;
            final Rectangle rectangle = new Rectangle(10, 10, colors[i]);
            label.setGraphic(rectangle);
            i++;
        }
    }
}
