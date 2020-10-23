package com.eva.ui.profile.staff.view;

import static com.eva.commons.core.PanelState.STAFF_PROFILE;
import static com.eva.commons.util.DateUtil.dateToString;

import com.eva.commons.core.PanelState;
import com.eva.model.CurrentView;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.ui.UiPart;
import com.eva.ui.profile.BasicInfoDisplay;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class StaffProfilePanel extends UiPart<Region> {
    public static final PanelState PANEL_NAME = STAFF_PROFILE;

    private static final String FXML = "StaffProfilePanel.fxml";

    public final CurrentView<Staff> staff;

    private BasicInfoDisplay basicInfoDisplay;
    // private CommentListPanel commentListPanel;

    @FXML
    private HBox cardPane;
    @FXML
    private FlowPane comments;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane leaves;

    @FXML
    private StackPane basicInfoPlaceholder;
    // @FXML
    // private StackPane commentListPanelPlaceholder;

    /**
     * Creates a {@code StaffProfilePanel} with the given {@code Staff}.
     */
    public StaffProfilePanel(CurrentView<Staff> staff) {
        super(FXML);
        this.staff = staff;
        // tags.getChildren().add(new Label("staff"));
        /*
        staff.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        staff.getLeaves().stream()
                .sorted(Comparator.comparing(leave -> leave.startDate))
                .forEach(leave -> leaves.getChildren().add(leaveToDisplay(leave)));
        staff.getComments().stream()
                .forEach(comment -> comments.getChildren()
                        .add(new Label(comment.toString())));
         */
    }

    private Label leaveToDisplay(Leave leave) {
        return new Label(
                leave.startDate.equals(leave.endDate)
                        ? dateToString(leave.startDate)
                        : String.format(
                        "%s to %s", dateToString(leave.startDate), dateToString(leave.endDate)));
    }

    /**
     * Fills up all the placeholders of this window.
     */
    public void fillInnerParts() {
        if (this.staff.getCurrentView().isPresent()) {
            Staff currentStaff = this.staff.getCurrentView().get();
            basicInfoDisplay = new BasicInfoDisplay(currentStaff);
            basicInfoPlaceholder.getChildren().add(basicInfoDisplay.getRoot());

            // commentListPanel = new CommentListPanel(currentStaff.getComments());
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
