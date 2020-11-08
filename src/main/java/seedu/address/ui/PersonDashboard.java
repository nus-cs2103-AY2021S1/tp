package seedu.address.ui;

import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonDashboard extends UiPart<Region> {

    private static final String FXML = "PersonDashboard.fxml";
    public static final int WRAPPING_WIDTH = 500;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on MainCatalogue level 4</a>
     */

    public final Person person;

    @FXML
    private HBox dashboardPane;
    @FXML
    private Label personName;
    @FXML
    private Label gitUserName;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Text address;
    @FXML
    private Label header1;
    @FXML
    private Label header2;
    @FXML
    private Text projects;
    @FXML
    private Text tasks;

    /**
     * Creates a {@code PersonDashboardCode} with the given {@code Person} and index to display.
     */
    public PersonDashboard(Optional<Person> person) {
        super(FXML);
        this.person = person.get();
        personName.setText(this.person.getPersonName().fullPersonName);
        gitUserName.setText("GitHub username: " + this.person.getGitUserNameString());
        phone.setText("Contact number: " + this.person.getPhone().value);
        email.setText("Email: " + this.person.getEmail().value);
        address.setWrappingWidth(WRAPPING_WIDTH);
        address.setText("Address: " + this.person.getAddress().value);
        displayProjects();
        displayTasks();

    }

    private void displayTasks() {
        header2.setText("Tasks: ");
        AtomicInteger index = new AtomicInteger();
        index.getAndIncrement();
        tasks.setText(this.person.getTasks().stream()
                .sorted(Comparator.comparing(Task::getTaskName))
                .map(Task::getTaskName).reduce("", (a, b) -> a + index.getAndIncrement() + ". " + b + "\n"));
    }

    private void displayProjects() {
        header1.setText("Projects: ");
        AtomicInteger index = new AtomicInteger();
        index.getAndIncrement();
        projects.setText(this.person.getProjects().stream()
                .sorted(Comparator.comparing(project -> project.getProjectName().fullProjectName))
                .map(p -> p.getProjectName().fullProjectName)
                .reduce("", (a, b) -> a + index.getAndIncrement() + ". " + b + "\n"));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonDashboard)) {
            return false;
        }

        // state check
        PersonDashboard card = (PersonDashboard) other;
        return person.equals(card.person);
    }
}
