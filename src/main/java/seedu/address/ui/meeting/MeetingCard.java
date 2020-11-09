package seedu.address.ui.meeting;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.meeting.Meeting;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class MeetingCard extends UiPart<Region> {

    private static final String FXML = "meeting/MeetingListCard.fxml";

    public final Meeting meeting;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label typeOfMeeting;
    @FXML
    private Label bidderId;
    @FXML
    private Label propertyId;
    @FXML
    private Label meetingDate;
    @FXML
    private Label venue;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public MeetingCard(Meeting meeting, int displayedIndex) {
        super(FXML);
        this.meeting = meeting;
        id.setText(displayedIndex + ". ");
        typeOfMeeting.setText(getMeetingType(meeting));
        bidderId.setText("Bidder Id: " + meeting.getBidderId().toString());
        propertyId.setText("Prop. Id: " + meeting.getPropertyId().toString());
        meetingDate.setText("Date: " + meeting.getMeetingDate().date);
        venue.setText("Venue: " + meeting.getVenue().venue);
        startTime.setText("Start: " + meeting.getStartTime().startTime);
        endTime.setText("End: " + meeting.getEndTime().endTime);
    }

    public String getMeetingType(Meeting meeting) {
        if (meeting.isAdmin()) {
            return "Admin Meeting:";
        } else if (meeting.isPaperWork()) {
            return "PaperWork Meeting:";
        } else if (meeting.isViewing()) {
            return "Viewing Meeting:";
        } else {
            return "ERROR: Meeting:";
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingCard)) {
            return false;
        }
        // state check
        MeetingCard card = (MeetingCard) other;
        return id.getText().equals(card.id.getText())
                && meeting.equals(card.meeting);
    }
}
