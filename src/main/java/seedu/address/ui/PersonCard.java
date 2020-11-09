package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.information.Person;

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
    private HBox cardPane;
    @FXML
    private Label personName;
    @FXML
    private Label personId;
    @FXML
    private Label personPhone;
    @FXML
    private Label personExperience;
    @FXML
    private Label personDateOfApplication;
    @FXML
    private Label personBlacklistStatus;
    @FXML
    private Label personSalary;
    @FXML
    private FlowPane personTags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        personId.setText(displayedIndex + ". ");
        personName.setText(person.getName().fullName);
        personPhone.setText(person.getPhone().value);
        personExperience.setText(String.format("%.2f years", person.getExperience().experienceInYears));
        personDateOfApplication.setText(person.getDateOfApplication().dateString);
        personBlacklistStatus.setText(person.getBlacklistStatus().isBlacklisted ? "Blacklisted" : "Not Blacklisted");
        person.getSalaryOptional().ifPresent(sal -> personSalary.setText("$" + sal.toString()));
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> personTags.getChildren().add(new Label(tag.tagName + " ")));
        personBlacklistStatus.setStyle(person.getBlacklistStatus().isBlacklisted
                ? "-fx-background-color: black; -fx-text-fill: white;"
                : "-fx-background-color: green; -fx-text-fill:white;");
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
        return personId.getText().equals(card.personId.getText())
                && person.equals(card.person);
    }
}
