package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.person.Person;
import seedu.address.model.person.person.Role;

/**
 * Panel containing the expanded contact details of a person.
 */
public class PersonDetailsPanel extends UiPart<Region> {
    private static final String FXML = "PersonDetailsPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonDetailsPanel.class);

    @FXML
    private Label name;
    @FXML
    private Label description;
    @FXML
    private Label role;
    @FXML
    private Label email;
    @FXML
    private Label phone;

    /**
     * Creates a {@code PersonDetailsPanel} with the given {@code Person}
     */
    public PersonDetailsPanel(Person person) {
        super(FXML);
        assert person != null;
        name.setText(person.getName().fullName);
        description.setText(person.getDescription().toString());
        role.setText(person.getRole().toString());
        if (person.getRole().equals(Role.DEVELOPER)) {
            role.setStyle("-fx-background-color: #32cd32");
        } else {
            role.setStyle("-fx-background-color: #ffa500");
        }
        email.setText(person.getEmail().value);
        phone.setText(person.getPhone().toString());
    }

}
