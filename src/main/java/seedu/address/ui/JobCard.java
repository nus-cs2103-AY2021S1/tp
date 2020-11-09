package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.information.Job;

/**
 * An UI component that displays information of a {@code Job}.
 */
public class JobCard extends UiPart<Region> {

    private static final String FXML = "JobListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Job job;

    @FXML
    private HBox cardPane;
    @FXML
    private Label jobName;
    @FXML
    private Label companyName;
    @FXML
    private Label companyId;
    @FXML
    private Label companyAddress;
    @FXML
    private Label jobPriority;
    @FXML
    private Label jobVacancy;
    @FXML
    private FlowPane jobTags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public JobCard(Job job, int displayedIndex) {
        super(FXML);
        this.job = job;
        companyId.setText(displayedIndex + ". ");
        jobName.setText(job.getJobTitle().fullName);
        companyName.setText(job.getCompanyName().fullCompanyName);
        companyAddress.setText(job.getAddress().value);
        jobPriority.setText("Priority: " + job.getPriority().value);
        jobVacancy.setText("Vacancies: " + job.getVacancy().value);
        job.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> jobTags.getChildren().add(new Label(tag.tagName + " ")));
        if (job.getPriority().value.equals("high")) {
            jobPriority.setStyle("-fx-background-color: #ff6961; -fx-text-fill:black;");
        } else if (job.getPriority().value.equals("moderate")) {
            jobPriority.setStyle("-fx-background-color: #fdfd96; -fx-text-fill:black;");
        } else if (job.getPriority().value.equals("low")) {
            jobPriority.setStyle("-fx-background-color: #b0ffad; -fx-text-fill:black;");
        } else {
            jobPriority.setStyle("-fx-background-color:black;");
        }
        companyName.setStyle("-fx-text-fill:#5f66b9;");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof JobCard)) {
            return false;
        }

        // state check
        JobCard card = (JobCard) other;
        return companyId.getText().equals(card.companyId.getText())
                && job.equals(card.job);
    }
}
