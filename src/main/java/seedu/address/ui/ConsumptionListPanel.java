package seedu.address.ui;

import java.util.logging.Logger;

import com.jfoenix.controls.JFXListView;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.consumption.Consumption;

/**
 * Panel containing the list of consumption.
 */
public class ConsumptionListPanel extends UiPart<Region> {
    private static final String FXML = "ConsumptionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ConsumptionListPanel.class);

    @javafx.fxml.FXML
    private JFXListView<Consumption> consumptionListView;

    /**
     * Creates a {@code ConsumptionListPanel} with the given {@code ObservableList}.
     */
    public ConsumptionListPanel(ObservableList<Consumption> consumptionList) {
        super(FXML);
        consumptionListView.setItems(consumptionList);
        consumptionListView.setCellFactory(listView -> new ConsumptionListViewCell());
        consumptionListView.depthProperty().set(1);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Consumption} using a {@code ConsumptionCard}.
     */
    class ConsumptionListViewCell extends ListCell<Consumption> {
        @Override
        protected void updateItem(Consumption consumption, boolean empty) {
            super.updateItem(consumption, empty);

            if (empty || consumption == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ConsumptionCard(consumption, getIndex() + 1).getRoot());
            }
        }
    }

}

