package com.eva.ui.profile.applicant.view;

import java.util.Comparator;
import java.util.Optional;

import com.eva.commons.core.index.Index;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.InterviewDate;
import com.eva.ui.UiPart;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class ApplicantBasicInfoDisplay extends UiPart<Region> {
    private static final String FXML = "ApplicantBasicInfoDisplay.fxml";

    public final Applicant applicant;

    @FXML
    private HBox displayPane;
    @FXML
    private Label id;
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
     * Create ApplicantBasicInfoDIsplay object
     *
     * @param applicant
     * @param index
     */
    public ApplicantBasicInfoDisplay(Applicant applicant, Index index) {
        super(FXML);
        this.applicant = applicant;

        id.setText(index.getOneBased() + ". ");
        name.setText(this.applicant.getName().fullName);
        phone.setText(this.applicant.getPhone().value);
        address.setText(this.applicant.getAddress().value);
        email.setText(this.applicant.getEmail().value);
        interviewDate.setText((this.applicant.getInterviewDate().isPresent()
                ? this.applicant.getInterviewDate().get().toString()
                : "Not set yet")
        );
        applicationStatus.setText(this.applicant.getApplicationStatus().toString());
        tags.getChildren().add(new Label("Applicant"));
        applicant.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private String interviewDateToDisplay(Optional<InterviewDate> interviewDateOptional) {
        return interviewDateOptional.map(date -> "Interview on: " + date.toString())
                .orElse("Interview Date not set yet.");
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
