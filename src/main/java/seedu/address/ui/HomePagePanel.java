package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.booking.Booking;

public class HomePagePanel extends UiPart<Region> {
    private static final String FXML = "HomePagePanel.fxml";

    @FXML
    private Label roomsAvailableTitle;

    @FXML
    private Label singleRoomsText;

    @FXML
    private Label doubleRoomsText;

    @FXML
    private Label suiteRoomsText;

    @FXML
    private Label roomServicesTitle;

    @FXML
    private Label wifiService;

    @FXML
    private Label diningService;

    @FXML
    private Label massageService;

    @FXML
    private Label recentBookingsTitle;

    @FXML
    private ListView<Booking> recentBookingsView;


    /**
     * Creates a HomePagePanel
     */
    public HomePagePanel(ObservableList<Booking> bookingList) {
        super(FXML);
        roomsAvailableTitle.setText("Type of Rooms Available:");
        singleRoomsText.setText(
                "Single Rooms: 2103 ~ 2112 (Price: $70/night)");
        doubleRoomsText.setText(
                "Double Rooms: 2113 ~ 2122 (Price: $100/night)");
        suiteRoomsText.setText(
                "Suite Rooms: 2123 ~ 2132 (Price: $150/night)");
        roomServicesTitle.setText("Room Services Available:");
        wifiService.setText("WIFI: Get 1GB of fast wifi (Price: $15)");
        diningService.setText("DINING: Order a meal delivered to your doorstep (Price: $50)");
        massageService.setText("MASSAGE: Get a full body massage (Price: $70)");
        recentBookingsTitle.setText("Recently Added Bookings:");
        FilteredList<Booking> filteredBookingList = new FilteredList<>(bookingList, Booking::isActive);
        SortedList<Booking> sortedBookingList = new SortedList<>(filteredBookingList, (o1, o2)
            -> o2.getId().compareTo(o1.getId()));
        recentBookingsView.setItems(sortedBookingList);
        recentBookingsView.setCellFactory(listView -> new RecentBookingCell());
    }

    class RecentBookingCell extends ListCell<Booking> {
        @Override
        protected void updateItem(Booking booking, boolean empty) {
            super.updateItem(booking, empty);
            if (empty || booking == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new Label(
                        String.format("%s. Person %s, Room Number %s, %s",
                                getIndex() + 1, booking.getPersonId(),
                                booking.getRoomId(), booking.getStartDate().toString())
                ));
            }
        }
    }
}
