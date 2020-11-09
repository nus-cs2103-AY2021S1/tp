package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.itinerary.Itinerary;

/**
 * Panel containing the list of itineraries.
 */
public class ItineraryListPanel extends UiPart<Region> {
    private static final String FXML = "ItineraryListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ItineraryListPanel.class);

    @FXML
    private ListView<Itinerary> itineraryListView;

    /**
     * Creates a {@code ItineraryListPanel} with the given {@code ObservableList}.
     */
    public ItineraryListPanel(ObservableList<Itinerary> itineraryList) {
        super(FXML);
        itineraryListView.setItems(itineraryList);
        itineraryListView.setCellFactory(listView -> new ItineraryListViewCell());
    }


    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Itinerary} using a {@code ItineraryCard}.
     */
    class ItineraryListViewCell extends ListCell<Itinerary> {
        @Override
        protected void updateItem(Itinerary itinerary, boolean empty) {
            super.updateItem(itinerary, empty);

            if (empty || itineraryListView == null) {
                setGraphic(null);
                setText(null);
            } else {
                switch (itinerary.getNumOfFilledFields()) {
                case 0:
                    setGraphic(new ItineraryListCard0Field(itinerary, getIndex() + 1).getRoot());
                    break;
                case 1:
                    setGraphic(new ItineraryListCard1Field(itinerary, getIndex() + 1).getRoot());
                    break;
                case 2:
                    setGraphic(new ItineraryListCard2Field(itinerary, getIndex() + 1).getRoot());
                    break;
                case 3:
                    setGraphic(new ItineraryListCard3Field(itinerary, getIndex() + 1).getRoot());
                    break;
                default:
                    break;
                }

            }
        }
    }

}
