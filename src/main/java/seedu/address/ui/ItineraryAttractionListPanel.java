package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.model.itinerary.ItineraryAttractionDayCounter;
import seedu.address.model.itinerary.ItineraryAttractionIndexCounter;

/**
 * Panel containing the list of itinerary attractions.
 */
public class ItineraryAttractionListPanel extends UiPart<Region> {
    private static final String FXML = "ItineraryAttractionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ItineraryAttractionListPanel.class);

    @FXML
    private ListView<ItineraryAttraction> itineraryAttractionListView;

    /**
     * Creates a {@code ItineraryAttractionListPanel} with the given {@code ObservableList}.
     */
    public ItineraryAttractionListPanel(ObservableList<ItineraryAttraction> itineraryAttractionList) {
        super(FXML);
        itineraryAttractionListView.setItems(itineraryAttractionList);
        itineraryAttractionListView.setCellFactory(listView -> new ItineraryAttractionListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code ItineraryAttraction} using
     * a {@code ItineraryAttractionCard}.
     */
    class ItineraryAttractionListViewCell extends ListCell<ItineraryAttraction> {
        @Override
        protected void updateItem(ItineraryAttraction itineraryAttraction, boolean empty) {
            super.updateItem(itineraryAttraction, empty);

            if (empty || itineraryAttractionListView == null) {
                setGraphic(null);
                setText(null);
            } else if (itineraryAttraction instanceof ItineraryAttractionDayCounter) {
                setGraphic(new ItineraryAttractionDayCounterCard(itineraryAttraction).getRoot());
            } else if (itineraryAttraction instanceof ItineraryAttractionIndexCounter) {
                switch (itineraryAttraction.getNumOfFilledFields()) {
                case 0:
                    setGraphic(new ItineraryAttractionCard0Field(itineraryAttraction).getRoot());
                    break;
                case 1:
                    setGraphic(new ItineraryAttractionCard1Field(itineraryAttraction).getRoot());
                    break;
                case 2:
                    setGraphic(new ItineraryAttractionCard2Field(itineraryAttraction).getRoot());
                    break;
                case 3:
                    setGraphic(new ItineraryAttractionCard3Field(itineraryAttraction).getRoot());
                    break;
                case 4:
                    setGraphic(new ItineraryAttractionCard4Field(itineraryAttraction).getRoot());
                    break;
                case 5:
                    setGraphic(new ItineraryAttractionCard5Field(itineraryAttraction).getRoot());
                    break;
                default:
                    break;
                }

            }
        }

    }
}
