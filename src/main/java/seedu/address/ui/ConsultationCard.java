package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.consultation.Consultation;

/**
 * Represents the UI part of Consultation object in JavaFX.
 */
public class ConsultationCard extends UiPart<Region> {
    private static final String FXML = "ConsultationListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Tasker level 4</a>
     */

    public final Consultation consultation;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private Label address;
    @FXML
    private Label type;

    /**
     * Creates a {@code ConsultationCode} with the given {@code Consultation} and index to display.
     *
     * @param consultation Consultation.
     * @param displayedIndex Index of consultation to display.
     */
    public ConsultationCard(Consultation consultation, int displayedIndex) {
        super(FXML);
        this.consultation = consultation;
        System.out.println(consultation.toString());
        id.setText(displayedIndex + ". ");
        name.setText(consultation.getName().fullName);
        date.setText(consultation.getDate().toString());
        time.setText(consultation.getTime().toString());
        address.setText(consultation.getLocation().value);
        type.setText(consultation.getType().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ConsultationCard)) {
            return false;
        }

        // state check
        ConsultationCard card = (ConsultationCard) other;
        return id.getText().equals(card.id.getText())
                && consultation.equals(card.consultation);
    }
}
