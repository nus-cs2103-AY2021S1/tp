package seedu.address.ui;

import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.model.project.Participation;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Project}.
 */
public class ProjectDashboard extends UiPart<Region> {

    private static final String FXML = "ProjectDashboard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on MainCatalogue level 4</a>
     */

    public final Project project;

    @FXML
    private HBox dashboardPane;
    @FXML
    private Label projectName;
    @FXML
    private Label deadline;
    @FXML
    private Text projectDescription;
    @FXML
    private Label repoUrl;
    @FXML
    private Label header1;
    @FXML
    private Label header2;
    @FXML
    private Label header3;
    @FXML
    private Text teammates;
    @FXML
    private Text tasks;

    /**
     * Creates a {@code ProjectDashboardCode} with the given {@code Project} and index to display.
     */
    public ProjectDashboard(Optional<Project> project) {
        super(FXML);
        this.project = project.get();
        projectName.setText(this.project.getProjectName().fullProjectName);
        deadline.setText("Project deadline: " + this.project.getDeadline().toString());
        repoUrl.setText("Project repourl: " + this.project.getRepoUrl().value);
        header1.setText("Project description: ");
        projectDescription.setWrappingWidth(500);
        projectDescription.setText(this.project.getProjectDescription().value);
        header2.setText("Teammates: ");
        String listOfTeammates = "";
        int ctr1 = 1;
        for (Participation teammate : this.project.getTeammates()) {
            listOfTeammates += ctr1 + ". " + teammate.getPerson().getPersonName().toString() + " ("
                    + teammate.getPerson().getGitUserNameString() + ")" + "\n";
            ctr1++;
        }
        teammates.setText(listOfTeammates);
        String headerOfListOfTasks = "Filtered List Of Tasks: ";
        if (this.project.isFullListOfTasks()) {
            headerOfListOfTasks = "All Tasks: ";
        }
        header3.setText(headerOfListOfTasks);
        AtomicInteger index = new AtomicInteger();
        index.getAndIncrement();
        tasks.setText(this.project.getTasks().stream()
                .sorted(Comparator.comparing(task -> task.taskName))
                .map(Task::getTaskName).reduce("", (a, b) -> a + index.getAndIncrement() + ". " + b + "\n"));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProjectDashboard)) {
            return false;
        }

        // state check
        ProjectDashboard card = (ProjectDashboard) other;
        return project.equals(card.project);
    }
}
