package seedu.expense.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.expense.commons.core.LogsCenter;
import seedu.expense.model.ChartDataCollector;
import seedu.expense.model.PieChartData;
import seedu.expense.model.ReadOnlyExpenseBook;

/**
 * Controller for graph page.
 */
public class GraphicalDisplayWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(GraphicalDisplayWindow.class);
    private static final String FXML = "GraphicalDisplayWindow.fxml";

    public final ChartDataCollector chartDataCollector;

    @FXML
    private StackPane placeHolder;

    @FXML
    private PieChart pieChart;

    /**
     * Creates a new GraphDisplayWindow.
     *
     * @param expenseBook Data of expenses and tags used to initialise charts.
     */
    public GraphicalDisplayWindow(ReadOnlyExpenseBook expenseBook) {
        super(FXML, new Stage());
        this.chartDataCollector = new PieChartData(expenseBook);

        this.pieChart.setTitle("Expenditure by Category");
        this.pieChart.setData(chartDataCollector.retrieveData());
    }

    /**
     * Shows the help window.
     *
     * @throws IllegalStateException <ul>
     *                                   <li>
     *                                       if this method is called on a thread other than the JavaFX Application
     *                                       Thread.
     *                                   </li>
     *                                   <li>
     *                                       if this method is called during animation or layout processing.
     *                                   </li>
     *                                   <li>
     *                                       if this method is called on the primary stage.
     *                                   </li>
     *                                   <li>
     *                                       if {@code dialogStage} is already showing.
     *                                   </li>
     *                               </ul>
     */
    public void show() {
        logger.fine("Showing graph page of expenses and categories");
        logger.info(chartDataCollector.getDataAsString());
        updatePieChart();
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     *  Updates data values of PieChart.
     */
    public void updatePieChart() {
        pieChart.getData().clear();
        this.pieChart.setData(chartDataCollector.retrieveData());
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the graph window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the graph window.
     */
    public void focus() {
        updatePieChart();
        getRoot().requestFocus();
    }

}
