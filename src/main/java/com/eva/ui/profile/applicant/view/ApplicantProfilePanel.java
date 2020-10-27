package com.eva.ui.profile.applicant.view;

import static com.eva.commons.core.PanelState.APPLICANT_PROFILE;

import com.eva.commons.core.PanelState;
import com.eva.model.current.view.CurrentViewApplicant;
import com.eva.model.person.applicant.Applicant;
import com.eva.ui.UiPart;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ApplicantProfilePanel extends UiPart<Region> {
    public static final PanelState PANEL_NAME = APPLICANT_PROFILE;

    private static final String FXML = "StaffProfilePanel.fxml";

    public final CurrentViewApplicant applicant;

    private ApplicantBasicInfoDisplay applicantBasicInfoDisplay;
    //private ApplicationInfoDisplay applicationInfoDisplay;

    @FXML
    private VBox cardPane;
    @FXML
    private FlowPane leaves;

    @FXML
    private StackPane basicInfoPlaceholder;
    @FXML
    private StackPane applicationInfoPlaceholder;

    /**
     * Creates a {@code StaffProfilePanel} with the given {@code Staff}.
     */
    public ApplicantProfilePanel(CurrentViewApplicant applicant) {
        super(FXML);
        this.applicant = applicant;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    public void fillInnerParts() {
        if (this.applicant.getCurrentView().isPresent()) {
            Applicant currentApplicant = this.applicant.getCurrentView().get();
            applicantBasicInfoDisplay = new ApplicantBasicInfoDisplay(currentApplicant);
            basicInfoPlaceholder.getChildren().add(applicantBasicInfoDisplay.getRoot());
            //applicationInfoDisplay = new ApplicationInfoDisplay(applicant.getApplication());
            //applicationInfoPlaceholder.getChildren().add(applicationInfoDisplay.getRoot());
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicantProfilePanel)) {
            return false;
        }

        // state check
        ApplicantProfilePanel card = (ApplicantProfilePanel) other;
        return applicant.equals(card.applicant);
    }
}
