package jimmy.mcgymmy.ui;

import static jimmy.mcgymmy.commons.util.CollectionUtil.requireAllNonNull;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;

public class SummaryDisplay extends UiPart<AnchorPane> {
    private static final String FXML = "SummaryDisplay.fxml";
    private static final String TOTAL_CALORIES_TEXT = "Total Calories %d";
    private static final String TOTAL_PROTEIN_TEXT =
            "Protein: %s";
    private static final String TOTAL_CARBOHYDRATE_TEXT =
            "Carbs: %s";
    private static final String TOTAL_FAT_TEXT =
            "Fats: %s";

    @FXML
    private PieChart pieChart;

    /**
     * Creates a summary display.
     * Contains summary of the items shown.
     */
    public SummaryDisplay() {
        super(FXML);
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(25);
        pieChart.setLabelsVisible(true);
    }

    public void setTotalMacronutrients(int totalCalories, int totalProtein, int totalCarbs, int totalFats) {
        requireAllNonNull(totalCalories, totalProtein, totalCarbs, totalFats);
        pieChart.setTitle(String.format(TOTAL_CALORIES_TEXT, totalCalories));
        pieChart.getData().setAll(
                new PieChart.Data(String.format(TOTAL_CARBOHYDRATE_TEXT, totalCarbs), totalCarbs),
                new PieChart.Data(String.format(TOTAL_PROTEIN_TEXT, totalProtein), totalProtein),
                new PieChart.Data(String.format(TOTAL_FAT_TEXT, totalFats), totalFats)
        );
    }
}
