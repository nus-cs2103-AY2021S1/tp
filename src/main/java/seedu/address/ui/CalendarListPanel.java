package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.deliverable.deliverable.Deliverable;
import seedu.address.model.event.TimeEvent;
import seedu.address.model.meeting.meeting.Meeting;

/**
 * Panel containing the list of persons.
 */
public class CalendarListPanel extends UiPart<Region> {
    private static final String FXML = "CalendarListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarListPanel.class);

    @FXML
    private ListView<TimeEvent> timeEventListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public CalendarListPanel(ObservableList<TimeEvent> timeEventList) {
        super(FXML);
        timeEventListView.setItems(timeEventList);
        timeEventListView.setCellFactory(listView -> new TimeEventViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class TimeEventViewCell extends ListCell<TimeEvent> {
        @Override
        protected void updateItem(TimeEvent timeEvent, boolean empty) {
            super.updateItem(timeEvent, empty);

            if (empty || timeEvent == null) {
                setGraphic(null);
                setText(null);
            } else if (timeEvent instanceof Deliverable) {
                setGraphic(new CalendarDeliverableCard((Deliverable) timeEvent).getRoot());
            } else if (timeEvent instanceof Meeting) {
                setGraphic(new CalendarMeetingCard((Meeting) timeEvent).getRoot());
            }
        }
    }

}
