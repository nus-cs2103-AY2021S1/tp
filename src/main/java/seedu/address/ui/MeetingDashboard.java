package seedu.address.ui;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.meeting.Meeting;

/**
 * An UI component that displays information of a {@code Project}.
 */
public class MeetingDashboard extends UiPart<Region> {

    private static final String FXML = "MeetingDashboard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on MainCatalogue level 4</a>
     */

    public final Meeting meeting;

    @FXML
    private HBox meetingDashboardPane;
    @FXML
    private Label name;
    @FXML
    private Label description;
    @FXML
    private Label publishDate;
    @FXML
    private Label duration;
    @FXML
    private Label note;

    /**
     * Creates a {@code TaskDashboardCode} with the given {@code Task} to display.
     */
    public MeetingDashboard(Optional<Meeting> meeting) {
        super(FXML);
        this.meeting = meeting.get();
        name.setText(this.meeting.getMeetingName());
        description.setText("Meeting description : " + this.meeting.getDescription());
        publishDate.setText("Meeting published on: " + this.meeting.getPublishDate());
        duration.setText("Meeting duration: " + this.meeting.getDuration());
        note.setText("Meeting note: " + this.meeting.getNote());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingDashboard)) {
            return false;
        }

        // state check
        MeetingDashboard card = (MeetingDashboard) other;
        return meeting.equals(card.meeting);
    }
}
