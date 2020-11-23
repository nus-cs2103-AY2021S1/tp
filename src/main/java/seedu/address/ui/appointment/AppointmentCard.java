package seedu.address.ui.appointment;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.Appointment;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Appointment}.
 */
public class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "AppointmentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Appointment appointment;

    @FXML
    private BorderPane borderPane;
    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label status;
    @FXML
    private Label clientName;
    @FXML
    private Label hairdresserName;
    @FXML
    private Label clientId;
    @FXML
    private Label hairdresserId;
    @FXML
    private Label date;
    @FXML
    private Label time;

    /**
     * Creates a {@code AppointmentCode} with the given {@code Appointment} and index to display.
     */
    public AppointmentCard(Appointment appointment, int displayedIndex) {
        super(FXML);
        this.appointment = appointment;
        id.setText(String.format("aid: %s", appointment.getId()));
        clientName.setText(appointment.getClient().getName().fullName);
        hairdresserName.setText(appointment.getHairdresser().getName().fullName);
        clientId.setText(appointment.getClient().getId().toString());
        hairdresserId.setText(appointment.getHairdresser().getId().toString());
        date.setText(appointment.getDate().toString());
        time.setText(appointment.getTime().toString());

        String apptStatus = appointment.getAppointmentStatus().name().toLowerCase();
        this.status.setText(apptStatus);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentCard)) {
            return false;
        }

        // state check
        AppointmentCard card = (AppointmentCard) other;
        return appointment.equals(card.appointment);
    }
}
