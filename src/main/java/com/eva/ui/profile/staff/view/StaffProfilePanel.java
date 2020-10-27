package com.eva.ui.profile.staff.view;

import static com.eva.commons.core.PanelState.STAFF_PROFILE;

import com.eva.commons.core.PanelState;
import com.eva.model.current.view.CurrentViewStaff;
import com.eva.model.person.staff.Staff;
import com.eva.ui.UiPart;
import com.eva.ui.profile.BasicInfoDisplay;
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

    public final CurrentViewStaff staff;

    private BasicInfoDisplay basicInfoDisplay;
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
    public StaffProfilePanel(CurrentViewStaff staff) {
        super(FXML);
        this.staff = staff;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    public void fillInnerParts() {
        if (this.staff.getCurrentView().isPresent()) {
            Staff currentStaff = this.staff.getCurrentView().get();
            basicInfoDisplay = new BasicInfoDisplay(currentStaff);
            basicInfoPlaceholder.getChildren().add(basicInfoDisplay.getRoot());

            commentListPanel = new CommentListPanel(staff.getCommentList());
            commentListPanelPlaceholder.getChildren().add(commentListPanel.getRoot());

            leaveInfoDisplay = new LeaveInfoDisplay(staff.getLeaveList());
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
