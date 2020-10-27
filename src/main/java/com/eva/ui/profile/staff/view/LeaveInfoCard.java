package com.eva.ui.profile.staff.view;

import static com.eva.commons.util.DateUtil.dateToString;

import com.eva.model.person.staff.leave.Leave;
import com.eva.ui.UiPart;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * A UI component that displays information of a {@code Leave}.
 */
public class LeaveInfoCard extends UiPart<Region> {
    private static final String FXML = "LeaveInfoCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on EvaDatabase level 4</a>
     */

    public final Leave leave;

    @FXML
    private HBox cardPane;
    @FXML
    private Label date;
    @FXML
    private Label duration;

    /**
     * Creates a {@code LeaveInfoCard} with the given {@code Leave} and index to display.
     */
    public LeaveInfoCard(Leave leave) {
        super(FXML);
        this.leave = leave;
        date.setText(formatDate(leave));
        duration.setText(leave.getLeaveLength() + " Days");
    }

    /**
     * Formats the date for display according to whether it's a single date leave of a multiple date leave.
     */
    private String formatDate(Leave leave) {
        return leave.startDate.equals(leave.endDate)
                        ? dateToString(leave.startDate)
                        : String.format(
                        "%s to %s", dateToString(leave.startDate), dateToString(leave.endDate));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LeaveInfoCard)) {
            return false;
        }

        // state check
        LeaveInfoCard card = (LeaveInfoCard) other;
        return date.getText().equals(card.date.getText())
                && duration.equals(card.duration);
    }
}
