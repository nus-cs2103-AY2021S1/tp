package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.calendar.CalendarMeeting;



public class CalendarListPanel extends UiPart<Region> {
    private static final String FXML = "CalendarListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarListPanel.class);

    @FXML
    private ListView<CalendarMeeting> calendarMeetingListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */

    public CalendarListPanel(ObservableList<CalendarMeeting> calendarMeetingList) {
        super(FXML);
        calendarMeetingListView.setItems(calendarMeetingList);
        calendarMeetingListView.setCellFactory(listView -> new CalendarMeetingListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class CalendarMeetingListViewCell extends ListCell<CalendarMeeting> { // person to bid
        @Override
        protected void updateItem(CalendarMeeting meeting, boolean empty) { //changed Person to Bid
            super.updateItem(meeting, empty);

            if (empty || meeting == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CalendarCard(meeting, getIndex() + 1).getRoot());
            }
        }
    }
}
