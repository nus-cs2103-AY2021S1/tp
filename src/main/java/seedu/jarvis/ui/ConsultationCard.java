package seedu.jarvis.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.jarvis.model.consultation.Consultation;

public class ConsultationCard extends UiPart<Region> {

    private static final String FXML = "ConsultationListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Consultation consultation;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label studentName;
    @FXML
    private Label dateAndTime;

    /**
     * Creates a {@code ConsultationCard} with the given {@code Consultation} and index to display.
     */
    public ConsultationCard(Consultation consultation, int displayedIndex) {
        super(FXML);
        this.consultation = consultation;
        id.setText(displayedIndex + ". ");
        studentName.setText(consultation.getStudentName());
        dateAndTime.setText(consultation.getFormattedDateTime());
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
