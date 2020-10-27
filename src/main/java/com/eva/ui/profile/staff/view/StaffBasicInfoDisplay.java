package com.eva.ui.profile.staff.view;

import java.util.Comparator;

import com.eva.model.person.staff.Staff;
import com.eva.ui.UiPart;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class StaffBasicInfoDisplay extends UiPart<Region> {
    private static final String FXML = "StaffBasicInfoDisplay.fxml";

    public final Staff staff;

    @FXML
    private HBox displayPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code StaffBasicInfoDisplay} with the given {@code Person}.
     */
    public StaffBasicInfoDisplay(Staff staff) {
        super(FXML);
        this.staff = staff;
        name.setText(this.staff.getName().fullName);
        phone.setText(this.staff.getPhone().value);
        address.setText(this.staff.getAddress().value);
        email.setText(this.staff.getEmail().value);
        tags.getChildren().add(new Label("staff"));
        staff.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StaffBasicInfoDisplay)) {
            return false;
        }

        // state check
        StaffBasicInfoDisplay card = (StaffBasicInfoDisplay) other;
        return staff.equals(card.staff);
    }
}
