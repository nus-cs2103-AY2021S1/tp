package com.eva.ui.list.view;

import static com.eva.commons.util.DateUtil.dateToString;

import java.util.Comparator;

import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.ui.UiPart;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Staff}.
 */
public class StaffListCard extends UiPart<Region> {
    private static final String FXML = "StaffListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on EvaDatabase level 4</a>
     */

    public final Staff staff;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane comments;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane leaves;

    /**
     * Creates a {@code StaffListCard} with the given {@code Staff} and index to display.
     */
    public StaffListCard(Staff staff, int displayedIndex) {
        super(FXML);
        this.staff = staff;
        id.setText(displayedIndex + ". ");
        name.setText(staff.getName().fullName);
        phone.setText(staff.getPhone().value);
        address.setText(staff.getAddress().value);
        email.setText(staff.getEmail().value);
        tags.getChildren().add(new Label("staff"));
        staff.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        staff.getLeaves().stream()
                .sorted(Comparator.comparing(leave -> leave.startDate))
                .forEach(leave -> leaves.getChildren().add(leaveToDisplay(leave)));
        staff.getComments().stream()
                .forEach(comment -> comments.getChildren()
                        .add(new Label(comment.toString())));
    }

    private Label leaveToDisplay(Leave leave) {
        return new Label(
            leave.startDate.equals(leave.endDate)
                ? dateToString(leave.startDate)
                : String.format(
                    "%s to %s", dateToString(leave.startDate), dateToString(leave.endDate)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StaffListCard)) {
            return false;
        }

        // state check
        StaffListCard card = (StaffListCard) other;
        return id.getText().equals(card.id.getText())
                && staff.equals(card.staff);
    }
}
