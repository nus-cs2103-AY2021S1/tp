package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.deliverable.deliverable.Deliverable;


/**
 * Panel containing a list of deliverables.
 */
public class DeliverableListPanel extends UiPart<Region> {
    private static final String FXML = "DeliverableListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DeliverableListPanel.class);

    @FXML
    private ListView<Deliverable> deliverableListView;

    /**
     * Creates a {@code DeliverableListPanel} with the given {@code ObservableList}.
     */
    public DeliverableListPanel(ObservableList<Deliverable> deliverableList) {
        super(FXML);
        deliverableListView.setItems(deliverableList);
        deliverableListView.setCellFactory(listView -> new DeliverableListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Deliverable} using a {@code DeliverableCard}.
     */
    class DeliverableListViewCell extends ListCell<Deliverable> {
        @Override
        protected void updateItem(Deliverable deliverable, boolean empty) {
            super.updateItem(deliverable, empty);

            if (empty || deliverable == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DeliverableCard(deliverable, getIndex() + 1).getRoot());
            }
        }
    }
}
