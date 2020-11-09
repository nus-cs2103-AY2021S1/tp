package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.information.Person;

/**
 * UI component that displays the details of a {@code Person}.
 */
public class PersonDetailedView extends UiPart<Region> {
    private static final String FXML = "PersonDetailedView.fxml";

    private final Person person;

    @FXML
    private StackPane detailPersonView;

    @FXML
    private Label detailedName;
    @FXML
    private Label detailedEmail;
    @FXML
    private Label detailedExperience;
    @FXML
    private Label detailedDateOfApplication;
    @FXML
    private Label detailedAddress;
    @FXML
    private Label detailedUrlLink;
    @FXML
    private Label detailedSalary;
    @FXML
    private Label detailedPhone;

    /**
     * Creates a detailed view of a {@code Person} with given person.
     */
    public PersonDetailedView(Person person) {
        super(FXML);
        this.person = person;
        String name = person.getName().fullName;
        String email = person.getEmail().value;
        String yearsOfExperience = String.format("%.2f years", person.getExperience().experienceInYears);
        String dateOfApplication = person.getDateOfApplication().dateString;
        String address = person.getAddressOptional().map(add -> add.value).orElse("Empty!");
        String profileLink = person.getUrlLinkOptional().map(url -> url.value).orElse("Empty!");
        String salary = person.getSalaryOptional().map(sal -> String.valueOf(sal.salary)).orElse("Empty!");
        String phone = person.getPhone().value;
        detailedName.setText(name);
        detailedEmail.setText(email);
        detailedExperience.setText(yearsOfExperience);
        detailedAddress.setText(address);
        detailedUrlLink.setText(profileLink);
        detailedDateOfApplication.setText(dateOfApplication);
        detailedSalary.setText(salary);
        detailedPhone.setText(phone);
    }
}

