package jimmy.mcgymmy.ui;

import static java.util.Objects.requireNonNull;
import static jimmy.mcgymmy.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class SummaryDisplay extends UiPart<AnchorPane> {
    private static final String FXML = "SummaryDisplay.fxml";
    private static final String TOTAL_CALORIES_TEXT = "Total Calories %d";

    @FXML
    private Label totalCalories;

    @FXML
    private Group group;

    private ObservableList<PieChart.Data> data;

    private PieChart pieChart;

    /**
     * Creates a summary display.
     * Contains summary of the items shown.
     */
    public SummaryDisplay() {
        super(FXML);
        setTotalCalories(200);
        pieChart = new PieChart();
        group.getChildren().add(pieChart);

        pieChart.setTitle("Summary");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
    }

    public void setTotalMacronutrients(int totalProtein, int totalCarbs, int totalFats) {
        requireAllNonNull(totalProtein, totalCarbs, totalFats);
        data = FXCollections.observableArrayList(
                new PieChart.Data("Protein", totalProtein),
                new PieChart.Data("Carbohydrate", totalCarbs),
                new PieChart.Data("Fat", totalFats)
        );
        pieChart.setData(data);
    }

    /**
     * Set the label to show the correct total calories.
     *
     * @param totalCalories total calories in current items.
     */
    public void setTotalCalories(int totalCalories) {
        requireNonNull(totalCalories);
        this.totalCalories.setText(String.format(TOTAL_CALORIES_TEXT, totalCalories));
    }
}
