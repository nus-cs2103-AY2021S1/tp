package seedu.pivot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.pivot.model.investigationcase.caseperson.CasePerson;

public class CasePersonCard extends UiPart<Region> {

    private static final String FXML = "CasePersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final CasePerson casePerson;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label sex;
    @FXML
    private Label phone;
    @FXML
    private Label phoneTitle;
    @FXML
    private Label email;
    @FXML
    private Label emailTitle;
    @FXML
    private Label address;
    @FXML
    private Label addressTitle;


    /**
     * Creates a {@code DocumentCard} with the given {@code Case} and index to display.
     */
    public CasePersonCard(CasePerson casePerson, int displayedIndex) {
        super(FXML);
        this.casePerson = casePerson;
        id.setText(displayedIndex + ". ");
        name.setText(casePerson.getName().toString());
        sex.setText("(" + casePerson.getSex().toString() + ")");

        String checkPhone = casePerson.getPhone().toString();
        boolean phoneTest = !checkPhone.isBlank();
        if (phoneTest) {
            phone.setText(casePerson.getPhone().toString());
        } else {
            phone.setManaged(false);
        }
        phoneTitle.setManaged(phoneTest);

        String checkEmail = casePerson.getEmail().toString();
        boolean emailTest = !checkEmail.isBlank();
        if (emailTest) {
            email.setText(casePerson.getEmail().toString());
        } else {
            email.setManaged(false);
        }
        emailTitle.setManaged(emailTest);

        String checkAddress = casePerson.getAddress().toString();
        boolean addressTest = !checkAddress.isBlank();
        if (addressTest) {
            address.setText(casePerson.getAddress().toString());
        } else {
            address.setManaged(false);
        }
        addressTitle.setManaged(addressTest);


    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CasePersonCard)) {
            return false;
        }

        // state check
        CasePersonCard card = (CasePersonCard) other;
        return id.getText().equals(card.id.getText())
                && casePerson.equals(card.casePerson);
    }
}
