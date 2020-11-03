package com.eva.ui.profile.staff.view;

import static com.eva.commons.core.PanelState.STAFF_PROFILE;

import com.eva.commons.core.PanelState;
import com.eva.commons.core.index.Index;
import com.eva.model.current.view.ReadOnlyCurrentViewStaff;
import com.eva.model.person.staff.Staff;
import com.eva.ui.UiPart;
import com.eva.ui.profile.CommentListPanel;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Panel containing the profile panel of the current {@code staff}.
 */
public class StaffProfilePanel extends UiPart<Region> {
    public static final PanelState PANEL_NAME = STAFF_PROFILE;

    private static final String FXML = "StaffProfilePanel.fxml";

    public final ReadOnlyCurrentViewStaff staff;

    private StaffBasicInfoDisplay staffBasicInfoDisplay;
    private LeaveInfoDisplay leaveInfoDisplay;
    private CommentListPanel commentListPanel;

    @FXML
    private VBox cardPane;

    @FXML
    private StackPane basicInfoPlaceholder;
    @FXML
    private StackPane commentListPanelPlaceholder;
    @FXML
    private StackPane leaveInfoPlaceholder;

    /**
     * Creates a {@code StaffProfilePanel} with the given {@code Staff}.
     */
    public StaffProfilePanel(ReadOnlyCurrentViewStaff staff) {
        super(FXML);
        this.staff = staff;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    public void fillInnerParts() {
        if (this.staff.getCurrentView().isPresent()) {
            Staff currentStaff = this.staff.getCurrentView().get();
            Index index = this.staff.getIndex();
            staffBasicInfoDisplay = new StaffBasicInfoDisplay(currentStaff, index);
            basicInfoPlaceholder.getChildren().add(staffBasicInfoDisplay.getRoot());

            commentListPanel = new CommentListPanel(staff.getCommentList());
            commentListPanelPlaceholder.getChildren().add(commentListPanel.getRoot());

            leaveInfoDisplay = new LeaveInfoDisplay(staff.getLeaveList(), currentStaff.getLeaveTaken());
            leaveInfoPlaceholder.getChildren().add(leaveInfoDisplay.getRoot());
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StaffProfilePanel)) {
            return false;
        }

        // state check
        StaffProfilePanel card = (StaffProfilePanel) other;
        return staff.equals(card.staff);
    }
}
