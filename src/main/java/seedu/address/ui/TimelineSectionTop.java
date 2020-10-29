package seedu.address.ui;

import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class TimelineSectionTop extends UiPart<Region> {
    private static final String FXML = "TimelineSectionTop.fxml";

    @javafx.fxml.FXML
    private StackPane meetingCard;

    private final TimelineMeetingCard timelineMeetingCard;

    TimelineSectionTop(TimelineMeetingCard timelineMeetingCard) {
        super(FXML);
        this.timelineMeetingCard = timelineMeetingCard;
        meetingCard.getChildren().add(timelineMeetingCard.getRoot());
    }
}
