package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on ClientList level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane phone;
    @FXML
    private FlowPane address;
    @FXML
    private FlowPane email;
    @FXML
    private FlowPane note;
    @FXML
    private FlowPane priority;
    @FXML
    private FlowPane clientSources;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);

        if (person.getPhone() != null) {
            phone.getChildren().add(new Label(person.getPhone().value));
        }

        if (person.getAddress() != null) {
            address.getChildren().add(new Label(person.getAddress().value));
        }

        if (person.getEmail() != null) {
            email.getChildren().add(new Label(person.getEmail().value));
        }

        if (person.getNote() != null) {
            note.getChildren().add(new Label(person.getNote().noteName));
        }
        priority.getChildren().add(new Label(person.getPriority().value));

        person.getClientSources().stream()
                .sorted(Comparator.comparing(clientSource -> clientSource.clientSourceName))
                .forEach(clientSource -> clientSources.getChildren().add(
                        new Label(clientSource.clientSourceName)
                ));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
