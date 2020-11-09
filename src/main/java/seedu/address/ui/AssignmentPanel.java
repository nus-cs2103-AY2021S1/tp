package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.module.grade.Assignment;

public class AssignmentPanel extends UiPart<Region> {
    private static final String FXML = "AssignmentPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AssignmentPanel.class);

    @FXML
    private ListView<Assignment> assignmentListView;

    /**
     * Creates a {@code AssignmentPanel} with the given {@code ObservableList}.
     */
    public AssignmentPanel(ObservableList<Assignment> assignmentObservableList) {
        super(FXML);
        assignmentListView.setItems(assignmentObservableList);
        assignmentListView.setCellFactory(listView -> new AssignmentViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Assignment} using a {@code AssignmentCard}.
     */
    class AssignmentViewCell extends ListCell<Assignment> {
        @Override
        protected void updateItem(Assignment assignment, boolean empty) {
            super.updateItem(assignment, empty);

            if (empty || assignment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AssignmentCard(assignment, getIndex() + 1).getRoot());
            }
        }
    }
}
