package jimmy.mcgymmy.ui;

import static java.util.Objects.requireNonNull;
import static jimmy.mcgymmy.commons.util.CollectionUtil.requireAllNonNull;

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
    private Group pieChartPlaceholder;

    @FXML
    private PieChart pieChart;

    /**
     * Creates a summary display.
     * Contains summary of the items shown.
     */
    public SummaryDisplay() {
        super(FXML);
        pieChart.setTitle("Summary");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
    }

    public void setTotalMacronutrients(int totalProtein, int totalCarbs, int totalFats) {
        requireAllNonNull(totalProtein, totalCarbs, totalFats);
        pieChart.getData().setAll(
                new PieChart.Data(String.format("Protein: %s", totalProtein), totalProtein),
                new PieChart.Data(String.format("Carbohydrate: %s", totalCarbs), totalCarbs),
                new PieChart.Data(String.format("Fat: %s", totalFats), totalFats)
        );
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
