package seedu.tr4cker.ui.countdown;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.ui.UiPart;

/**
 * Panel containing the list of events in Countdown.
 */
public class CountdownEventListPanel extends UiPart<Region> {

    private static final String FXML = "CountdownEventListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CountdownEventListPanel.class);

    @FXML
    private Label countdownEventListPanel;

    @FXML
    private ListView<Event> eventListView;

    /**
     * Creates a {@code CountdownEventsListPanel} with the given {@code ObservableList}.
     */
    public CountdownEventListPanel(ObservableList<Event> eventList) {
        super(FXML);
        logger.fine("Initialising Countdown Events List panel...");
        eventListView.setItems(eventList);
        eventListView.setCellFactory(listView -> new EventListViewCell());
        logger.fine("Created Countdown Events List panel.");
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Event} using a {@code CountdownEventCard}.
     */
    static class EventListViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CountdownEventCard(event, getIndex() + 1).getRoot());
            }
        }
    }

}
