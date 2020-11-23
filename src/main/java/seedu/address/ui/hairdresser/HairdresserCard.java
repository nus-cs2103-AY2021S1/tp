package seedu.address.ui.hairdresser;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Hairdresser}.
 */
public class HairdresserCard extends UiPart<Region> {

    private static final String FXML = "HairdresserListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Hairdresser hairdresser;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label gender;
    @FXML
    private Label email;
    @FXML
    private Label title;
    @FXML
    private FlowPane specialisations;

    /**
     * Creates a {@code HairdresserCode} with the given {@code Hairdresser} and index to display.
     */
    public HairdresserCard(Hairdresser hairdresser, int displayedIndex) {
        super(FXML);
        this.hairdresser = hairdresser;
        id.setText(String.format("hid: %s ", hairdresser.getId()));
        name.setText(hairdresser.getName().fullName);
        phone.setText(hairdresser.getPhone().value);
        gender.setText(hairdresser.getGender().toString());
        email.setText(hairdresser.getEmail().value);
        title.setText(hairdresser.getTitle().value);
        hairdresser.getSpecs()
                .forEach(specialisation -> specialisations.getChildren()
                        .add(new Label(specialisation.getSpecInString())));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HairdresserCard)) {
            return false;
        }

        // state check
        HairdresserCard card = (HairdresserCard) other;
        return id.getText().equals(card.id.getText())
                && hairdresser.equals(card.hairdresser);
    }
}
