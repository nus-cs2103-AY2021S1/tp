package com.eva.ui.profile.applicant.view;

import static com.eva.commons.core.PanelState.APPLICANT_PROFILE;

import com.eva.commons.core.PanelState;
import com.eva.model.current.view.ReadOnlyCurrentViewApplicant;
import com.eva.model.person.applicant.Applicant;
import com.eva.ui.UiPart;
import com.eva.ui.profile.CommentListPanel;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * A UI component that displays the current applicant profile.
 */
public class ApplicantProfilePanel extends UiPart<Region> {
    public static final PanelState PANEL_NAME = APPLICANT_PROFILE;

    private static final String FXML = "ApplicantProfilePanel.fxml";

    public final ReadOnlyCurrentViewApplicant applicant;

    private ApplicantBasicInfoDisplay applicantBasicInfoDisplay;
    private ApplicationInfoDisplay applicationInfoDisplay;
    private CommentListPanel commentListPanel;

    @FXML
    private VBox cardPane;
    @FXML
    private FlowPane leaves;

    @FXML
    private StackPane basicInfoPlaceholder;
    @FXML
    private StackPane applicationInfoPlaceholder;
    @FXML
    private StackPane commentListPanelPlaceholder;

    /**
     * Creates a {@code ApplicantProfilePanel} with the given {@code Applicant}.
     */
    public ApplicantProfilePanel(ReadOnlyCurrentViewApplicant applicant) {
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

            applicationInfoDisplay = new ApplicationInfoDisplay(applicant.getApplication());
            applicationInfoPlaceholder.getChildren().add(applicationInfoDisplay.getRoot());

            commentListPanel = new CommentListPanel(applicant.getCommentList());
            commentListPanelPlaceholder.getChildren().add(commentListPanel.getRoot());
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
