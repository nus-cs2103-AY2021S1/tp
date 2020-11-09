package seedu.flashcard.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * An UI component that displays statistics information of a {@code Flashcard}.
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
     * Creates a {@code FlashcardStatsCard} with the given {@code Flashcard} to display.
     */
    public FlashcardStatsCard(Flashcard flashcard) {
        super(FXML);
        this.flashcard = flashcard;
        question.setText(flashcard.getQuestion().toString());
        int successFrequency = flashcard.getStatistics().getSuccessFrequency();
        int reviewedFrequency = flashcard.getStatistics().getReviewFrequency();
        int failureFrequency = reviewedFrequency - successFrequency;
        if (reviewedFrequency > 0) {
            double successRate = ((double) successFrequency / reviewedFrequency) * 100;
            double failureRate = 100 - successRate;
            PieChart.Data successPie = new PieChart.Data(String.format("Correct (%.1f%%)", successRate),
                    successFrequency);
            PieChart.Data failurePie = new PieChart.Data(String.format("Wrong (%.1f%%)", failureRate),
                    failureFrequency);
            pieChart.setData(FXCollections.observableArrayList(successPie, failurePie));
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
