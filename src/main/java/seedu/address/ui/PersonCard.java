package seedu.address.ui;

import static java.util.Objects.requireNonNull;

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
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox personCardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label actualId;
    @FXML
    private Label phone;
    @FXML
    private Label telegramAddress;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private HBox tagsBox;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, boolean isPersonList, int displayedIndex, int actualIndex) {
        super(FXML);
        requireNonNull(person);
        assert displayedIndex > 0 : "Displayed index should be greater than 0";
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        telegramAddress.setText("@" + person.getTelegramAddress().value);
        email.setText(person.getEmail().value);

        initializeActualIndex(isPersonList, actualIndex);
        initializeTags();
    }

    /**
     * Initializa the actual Index for each {@Code PersonCard}
     */
    private void initializeActualIndex(boolean isPersonList, int actualIndex) {
        if (isPersonList) {
            actualId.setVisible(false);
        } else {
            actualId.setText("(Actual Index: " + actualIndex + ")");
        }
    }


    /**
     * Initialize all the tags for each {@Code PersonCard}
     */
    public void initializeTags() {
        if (!person.getTagNames().isEmpty()) {
            person.getTagNames().stream()
                    .sorted(Comparator.comparing(tagName -> tagName.tagName))
                    .forEach(tagName -> tags.getChildren().add(new Label(tagName.tagName)));
        } else {
            tagsBox.setVisible(false);
            tagsBox.setManaged(false);
        }
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
