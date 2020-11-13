package com.eva.ui.list.view;

import static com.eva.commons.core.PanelState.STAFF_LIST;

import java.util.logging.Logger;

import com.eva.commons.core.LogsCenter;
import com.eva.commons.core.PanelState;
import com.eva.model.person.staff.Staff;
import com.eva.ui.UiPart;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of staffs.
 */
public class StaffListPanel extends UiPart<Region> {
    public static final PanelState PANEL_NAME = STAFF_LIST;

    private static final String FXML = "StaffListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StaffListPanel.class);

    @FXML
    private ListView<Staff> staffListView;

    /**
     * Creates a {@code StaffListPanel} with the given {@code ObservableList}.
     */
    public StaffListPanel(ObservableList<Staff> staffList) {
        super(FXML);
        staffListView.setItems(staffList);
        staffListView.setCellFactory(listView -> new StaffListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Staff} using a {@code StaffListCard}.
     */
    class StaffListViewCell extends ListCell<Staff> {
        @Override
        protected void updateItem(Staff staff, boolean empty) {
            super.updateItem(staff, empty);

            if (empty || staff == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StaffListCard(staff, getIndex() + 1).getRoot());
            }
        }
    }

}

