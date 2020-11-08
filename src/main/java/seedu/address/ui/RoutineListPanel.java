package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.routine.Routine;

/**
 * Panel containing the list of routines.
 */
public class RoutineListPanel extends UiPart<Region> {
    private static final String FXML = "RoutineListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RoutineListPanel.class);

    @FXML
    private ListView<Routine> routineListView;
    @FXML
    private ListView<Integer> lv;

    /**
     * Creates a {@code RoutineListPanel} with the given {@code ObservableList}.
     */
    public RoutineListPanel(ObservableList<Routine> routineList) {
        super(FXML);
        routineListView.setItems(routineList);
        routineListView.setCellFactory(listView -> new RoutineListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Routine} using a {@code RoutineCard}.
     */
    class RoutineListViewCell extends ListCell<Routine> {
        @Override
        protected void updateItem(Routine routine, boolean empty) {
            super.updateItem(routine, empty);

            if (empty || routine == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new RoutineCard(routine, getIndex() + 1).getRoot());
            }
        }
    }

}
