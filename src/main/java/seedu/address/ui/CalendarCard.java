package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.calendar.CalendarMeeting;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class CalendarCard extends UiPart<Region> {

    private static final String FXML = "CalendarListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final CalendarMeeting meeting;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label typeOfMeeting;
    @FXML
    private Label calenderBidderId;
    @FXML
    private Label calenderPropertyId;
    @FXML
    private Label time;
    @FXML
    private Label venue;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public CalendarCard(CalendarMeeting meeting, int displayedIndex) {
        super(FXML);
        this.meeting = meeting;
        id.setText(displayedIndex + ". ");
        typeOfMeeting.setText(getMeetingType(meeting));
        calenderBidderId.setText(meeting.getCalendarBidderId().bidderId);
        calenderPropertyId.setText(meeting.getCalendarPropertyId().propertyId);
        time.setText(meeting.getCalendarTime().time);
        venue.setText(meeting.getCalendarVenue().venue);
    }

    public String getMeetingType(CalendarMeeting meeting) {
        if (meeting.isAmin()) {
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
        if (!(other instanceof CalendarCard)) {
            return false;
        }
        // state check
        CalendarCard card = (CalendarCard) other;
        return id.getText().equals(card.id.getText())
                && meeting.equals(card.meeting);
    }
}
