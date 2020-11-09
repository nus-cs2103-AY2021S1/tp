package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.booking.Booking;

/**
 * Panel containing the list of bookings.
 */
public class BookingListPanel extends UiPart<Region> {
    private static final String FXML = "BookingListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);
    private ObservableList<Booking> masterData = FXCollections.observableArrayList();

    @FXML
    private ListView<Booking> bookingListView;

    /**
     * Creates a {@code BookingListPanel} with the given {@code ObservableList}.
     */
    public BookingListPanel(ObservableList<Booking> bookingList) {
        super(FXML);
        masterData = bookingList;

        SortedList<Booking> sortedData = new SortedList<>(masterData, new Comparator<Booking>() {
            @Override
            public int compare(Booking o1, Booking o2) {
                if (o1.isActive() && !o2.isActive()) {
                    return -1;
                } else if (o2.isActive() && !o1.isActive()) {
                    return 1;
                } else {
                    return o2.getStartDate().compareTo(o1.getStartDate());
                }
            }
        });
        bookingListView.setItems(sortedData);
        bookingListView.setCellFactory(listView -> new BookingListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Booking} using a {@code BookingCard}.
     */
    class BookingListViewCell extends ListCell<Booking> {
        @Override
        protected void updateItem(Booking booking, boolean empty) {
            super.updateItem(booking, empty);

            if (empty || booking == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new BookingCard(booking, getIndex() + 1).getRoot());
            }
        }
    }
}
