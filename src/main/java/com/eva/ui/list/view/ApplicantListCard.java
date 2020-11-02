package com.eva.ui.list.view;

import java.util.Comparator;
import java.util.Optional;

import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.InterviewDate;
import com.eva.ui.UiPart;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;


/**
 * An UI component that displays information of a {@code Applicant}.
 */
public class ApplicantListCard extends UiPart<Region> {
    private static final String FXML = "ApplicantListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on EvaDatabase level 4</a>
     */

    public final Applicant applicant;

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
    private Label applicationStatus;
    @FXML
    private Label interviewDate;
    @FXML
    private FlowPane comments;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane leaves;

    /**
     * Creates a {@code Applicant Card} with the given {@code Applicant} and index to display.
     */
    public ApplicantListCard(Applicant applicant, int displayedIndex) {
        super(FXML);
        this.applicant = applicant;

        name.setWrapText(true);
        phone.setWrapText(true);
        address.setWrapText(true);
        email.setWrapText(true);

        id.setText(displayedIndex + ". ");
        name.setText(applicant.getName().fullName);
        phone.setText(applicant.getPhone().value);
        address.setText(applicant.getAddress().value);
        email.setText(applicant.getEmail().value);
        applicationStatus.setText(applicant.getApplicationStatus().toString());
        interviewDate.setText(interviewDateToDisplay(applicant.getInterviewDate()));
        tags.getChildren().add(new Label("applicant"));
        applicant.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        applicant.getComments().stream()
                .forEach(comment -> comments.getChildren()
                        .add(new Label(comment.toString())));
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
        if (!(other instanceof ApplicantListCard)) {
            return false;
        }

        // state check
        ApplicantListCard card = (ApplicantListCard) other;
        return id.getText().equals(card.id.getText())
                && applicant.equals(card.applicant);
    }
}
