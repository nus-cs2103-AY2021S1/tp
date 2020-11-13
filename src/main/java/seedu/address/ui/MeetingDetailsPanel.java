package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.meeting.meeting.Meeting;

/**
 * Panel containing the expanded details of a meeting.
 */
public class MeetingDetailsPanel extends UiPart<Region> {
    private static final String FXML = "MeetingDetailsPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MeetingDetailsPanel.class);

    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label dateTime;
    @FXML
    private Label venue;
    @FXML
    private VBox contactList;

    /**
     * Creates a {@code MeetingDetailsPanel} with the given {@code Meeting}
     */

    public MeetingDetailsPanel(Meeting meeting) {
        super(FXML);
        assert meeting != null;
        title.setText(meeting.getTitle().value);
        description.setText(meeting.getDescription().toString());
        dateTime.setText(String.format("%s to %s", meeting.getFrom(), meeting.getTo()));
        venue.setText(meeting.getLocation().toString());
        meeting.getContacts().value.ifPresentOrElse(contacts -> {
            for (String contact : contacts.split(",")) {
                String cleanedContact = contact.trim().replaceAll("\\s+", " ");
                Label label = new Label("-" + cleanedContact);
                label.setWrapText(true);
                label.setStyle("-fx-pref-width: 400");
                contactList.getChildren().add(label);
            }
        }, () -> contactList.getChildren().add(new Label(meeting.getContacts().toString())));

    }

}
