package seedu.flashcard.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * An UI component that displays information of a {@code Flashcard}.
 */
public class FlashcardStatsCard extends UiPart<Region> {

    private static final String FXML = "FlashcardStatsCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     */

    public final Flashcard flashcard;

    @FXML
    private PieChart pieChart;

    @FXML
    private Label question;

    @FXML
    private Label reviewCount;

    @FXML
    private Label correctCount;

    @FXML
    private Label errorMessage;

    /**
     * Creates a {@code FlashcardListCard} with the given {@code Flashcard} and index to display.
     */
    public FlashcardStatsCard(Flashcard flashcard) {
        super(FXML);
        this.flashcard = flashcard;
        question.setText(flashcard.getQuestion().toString());
        int successFrequency = flashcard.getStatistics().getSuccessFrequency();
        int reviewedFrequency = flashcard.getStatistics().getReviewFrequency();
        int failureFrequency = reviewedFrequency - successFrequency;
        double successRate = ((double) successFrequency / (double) reviewedFrequency) * 100;
        double failureRate = 100 - successRate;
        if (reviewedFrequency > 0) {
            PieChart.Data successPie = new PieChart.Data(String.format("Correct (%.1f%%)", successRate), successFrequency);
            PieChart.Data wrongPie = new PieChart.Data(String.format("Wrong (%.1f%%)", failureRate), failureFrequency);
            pieChart.setData(FXCollections.observableArrayList(successPie, wrongPie));
            pieChart.setLegendVisible(false);
        } else {
            errorMessage.setVisible(true);
            pieChart.setVisible(false);
            pieChart.managedProperty().bind(pieChart.visibleProperty());
        }
        reviewCount.setText("Reviewed count: " + reviewedFrequency);
        correctCount.setText("Correct count: " + successFrequency);

    }
}
