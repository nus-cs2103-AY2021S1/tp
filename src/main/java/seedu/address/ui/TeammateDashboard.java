package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.project.Participation;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Project}.
 */
public class TeammateDashboard extends UiPart<Region> {

    private static final String FXML = "TeammateDashboard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on MainCatalogue level 4</a>
     */

    public final Participation teammate;
    public final List<String> listOfTasksName = new ArrayList<>();

    @FXML
    private HBox teammateDashboardPane;
    @FXML
    private Label teammateName;
    @FXML
    private Label gitUserName;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private Label header1;
    @FXML
    private FlowPane tasks;

    /**
     * Creates a {@code TaskDashboardCode} with the given {@code Task} to display.
     * @param teammate
     */
    public TeammateDashboard(Optional<Participation> teammate) {
        super(FXML);
        this.teammate = teammate.get();
        Person person = this.teammate.getPerson();
        teammateName.setText(person.getPersonName().toString());
        gitUserName.setText("Teammate gitUserName: " + person.getGitUserNameString());
        phone.setText("Teammate phone number: " + person.getPhone());
        email.setText("Teammate email: " + person.getEmail());
        address.setText("Teammate address: " + person.getAddress());
        header1.setText("Tasks assigned: ");
        for (Participation p : person.getParticipations().values()) {
            System.out.println(p);
            String projectName = p.getProject().getProjectName().toString();
            Project proj = p.getProject();
            Participation pp = proj.getParticipation(person.getGitUserNameString());
            System.out.println(projectName);
            for (Task task : pp.getTasks()) {
                System.out.println(task);
                listOfTasksName.add(projectName + ": " + task.getTaskName());
            }
        }
        System.out.println("fasudhasodsa");
        for (String str : listOfTasksName) {
            System.out.println(str);
        }
        this.listOfTasksName.stream()
                .forEach(tasksName -> tasks.getChildren()
                        .add(new Label(tasksName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TeammateDashboard)) {
            return false;
        }

        // state check
        TeammateDashboard card = (TeammateDashboard) other;
        return teammate.equals(card.teammate);
    }
}
