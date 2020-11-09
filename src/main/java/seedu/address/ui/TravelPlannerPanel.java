package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.travelplan.TravelPlan;
import seedu.address.ui.cards.NameCard;

/**
 * Panel containing the wishlist and list of travel plans in the travel planner.
 */
public class TravelPlannerPanel extends UiPart<Region> {
    private static final String FXML = "TravelPlannerPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TravelPlanPanel.class);

    @FXML
    private ListView<TravelPlan> travelPlanListView;

    /**
     * Creates a {@code TraverPlannerPannel} with the given {@code ObservableList}.
     */
    public TravelPlannerPanel(ObservableList<TravelPlan> travelPlanList) {
        super(FXML);
        travelPlanListView.setItems(travelPlanList);
        travelPlanListView.setCellFactory(travelPlan -> new TravelPlanListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the {@code Wishlist} or name of a {@code TravelPlan} using a
     * {@code NameCard}.
     */
    class TravelPlanListViewCell extends ListCell<TravelPlan> {
        @Override
        protected void updateItem(TravelPlan travelPlan, boolean empty) {
            super.updateItem(travelPlan, empty);

            if (empty || travelPlan == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new NameCard(travelPlan.getName().toString(), getIndex() + 1).getRoot());
            }
        }
    }

}
