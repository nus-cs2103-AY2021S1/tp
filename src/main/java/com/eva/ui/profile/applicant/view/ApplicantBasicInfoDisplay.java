package com.eva.ui.profile.applicant.view;

import com.eva.model.person.applicant.Applicant;
import com.eva.ui.UiPart;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.util.Comparator;

public class ApplicantBasicInfoDisplay extends UiPart<Region> {
    private static final String FXML = "ApplicantBasicInfoDisplay.fxml";

    public final Applicant applicant;

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
    private Label interviewDate;
    @FXML
    private Label applicationStatus;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code StaffBasicInfoDisplay} with the given {@code Person}.
     */
    public ApplicantBasicInfoDisplay(Applicant applicant) {
        super(FXML);
        this.applicant = applicant;
        name.setText("Name: " + this.applicant.getName().fullName);
        phone.setText("Phone: " + this.applicant.getPhone().value);
        address.setText("Address: " + this.applicant.getAddress().value);
        email.setText("Email: " + this.applicant.getEmail().value);
        interviewDate.setText("Interview Date: " +
                (this.applicant.getInterviewDate().isPresent()
                        ? this.applicant.getInterviewDate().get().toString()
                        : "Not set yet")
        );
        applicationStatus.setText("Application Status: "
                + this.applicant.getApplicationStatus().toString());
        tags.getChildren().add(new Label("Applicant"));
        applicant.getTags().stream()
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
        if (!(other instanceof ApplicantBasicInfoDisplay)) {
            return false;
        }

        // state check
        ApplicantBasicInfoDisplay card = (ApplicantBasicInfoDisplay) other;
        return applicant.equals(card.applicant);
    }
}
