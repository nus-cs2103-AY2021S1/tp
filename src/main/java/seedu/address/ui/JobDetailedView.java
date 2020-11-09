package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.information.Job;

/**
 * UI component that displays the details of a {@code Job}.
 */
public class JobDetailedView extends UiPart<Region> {
    private static final String FXML = "JobDetailedView.fxml";

    private final Job job;

    @FXML
    private StackPane detailPersonView;
    @FXML
    private Label detailedJobName;
    @FXML
    private Label detailedCompanyName;
    @FXML
    private Label detailedCompanyEmail;
    @FXML
    private Label detailedCompanyPhone;
    @FXML
    private Label detailedCompanyAddress;
    @FXML
    private Label detailedCompanyVacancy;

    /**
     * Creates a detailed view of a {@code Job} with given Job.
     */
    public JobDetailedView(Job job) {
        super(FXML);
        this.job = job;
        String jobName = this.job.getJobTitle().toString();
        String companyName = job.getCompanyName().fullCompanyName;
        String companyPhone = job.getPhone().toString();
        String companyEmail = job.getEmail().toString();
        String companyAddress = job.getAddress().toString();
        String vacancy = job.getVacancy().toString();
        detailedJobName.setText(jobName);
        detailedCompanyName.setText(companyName);
        detailedCompanyPhone.setText(companyPhone);
        detailedCompanyEmail.setText(companyEmail);
        detailedCompanyAddress.setText(companyAddress);
        detailedCompanyVacancy.setText(vacancy);
    }
}
