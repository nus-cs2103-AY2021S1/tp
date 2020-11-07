package seedu.address.ui.card;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.StatisticsData;
import seedu.address.model.tag.Tag;
import seedu.address.ui.UiPart;

public class StatsSummaryCard extends UiPart<Region> {
    private static final String FXML = "card/StatsSummaryCard.fxml";

    public final Tag tag;

    @FXML
    private VBox statsSummaryCardPlaceholder;
    @FXML
    private Label moduleCode;
    @FXML
    private Label lessonTime;
    @FXML
    private Label taskTime;
    @FXML
    private Label totalTime;

    /**
     * Create a StatsSummaryCard
     * @param tag the tag
     * @param dataSet the statistic data
     */
    public StatsSummaryCard(Tag tag, StatisticsData dataSet, String tagColor) {
        super(FXML);
        this.tag = tag;
        statsSummaryCardPlaceholder.setStyle(
                "-fx-background-color: linear-gradient("
                        + "to bottom right, " + tagColor + ", derive(" + tagColor + ", 60%), "
                        + tagColor + ");"
        );
        moduleCode.setText(tag.tagName.equals("") ? "Untagged" : tag.tagName);
        lessonTime.setText("" + dataSet.getTotalLessonTime(tag) + " mins");
        taskTime.setText("" + dataSet.getTotalTaskTime(tag) + " mins");
        totalTime.setText("" + dataSet.getTotalTime(tag) + " mins");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LessonCard)) {
            return false;
        }

        // state check
        StatsSummaryCard card = (StatsSummaryCard) other;
        return tag.equals(card.tag);
    }
}
