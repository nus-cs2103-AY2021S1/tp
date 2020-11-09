package seedu.address.ui.meeting;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.meeting.Meeting;
import seedu.address.ui.UiPart;


public class MeetingListPanel extends UiPart<Region> {
    private static final String FXML = "meeting/MeetingListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MeetingListPanel.class);

    @FXML
    private ListView<Meeting> meetingListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */

    public MeetingListPanel(ObservableList<Meeting> meetingList) {
        super(FXML);
        meetingListView.setItems(meetingList);
        meetingListView.setCellFactory(listView -> new MeetingListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class MeetingListViewCell extends ListCell<Meeting> { // person to bid
        @Override
        protected void updateItem(Meeting meeting, boolean empty) { //changed Person to Bid
            super.updateItem(meeting, empty);

            if (empty || meeting == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MeetingCard(meeting, getIndex() + 1).getRoot());
            }
        }
    }
}
