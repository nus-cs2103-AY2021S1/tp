package com.eva.ui.profile.staff.view;

import com.eva.model.person.staff.leave.Leave;
import com.eva.model.person.staff.leave.LeaveTaken;
import com.eva.ui.UiPart;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * A UI component that displays the list of leaves.
 */
public class LeaveInfoDisplay extends UiPart<Region> {
    private static final String FXML = "LeaveInfoDisplay.fxml";
    private static final String DISPLAY_NAME = "Leaves";

    @FXML
    private ListView<Leave> leaveListView;
    @FXML
    private Label leavesTaken;
    @FXML
    private Label display;

    /**
     * Creates a {@code LeaveInfoDisplay} with the given {@code ObservableList}.
     */
    LeaveInfoDisplay(ObservableList<Leave> leaves, LeaveTaken leaveTaken) {
        super(FXML);
        leaveListView.setItems(leaves);
        leaveListView.setCellFactory(listView -> new LeaveListViewCell());
        leavesTaken.setText(leaveTaken.toString());
        display.setText(DISPLAY_NAME);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Leave} using a {@code LeaveInfoCard}.
     */
    class LeaveListViewCell extends ListCell<Leave> {
        @Override
        protected void updateItem(Leave leave, boolean empty) {
            super.updateItem(leave, empty);

            if (empty || leave == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LeaveInfoCard(leave).getRoot());
            }
        }
    }
}
