package seedu.homerce.ui.appointmentpanel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.ui.UiPart;

/**
 * An UI component that displays information of a {@code Appointment}.
 */
public class AppointmentCard extends UiPart<Region> {
    //TODO Fix issue with fxml rendering.
    private static final String FXML = "appointmentpanel/AppointmentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable titles cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Appointment appointment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label clientName;
    @FXML
    private Label clientPhone;
    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label timeOfDay;
    @FXML
    private Label service;
    @FXML
    private Label isDone;

    /**
     * Creates a {@code ServiceCode} with the given {@code Service} and index to display.
     */
    public AppointmentCard(Appointment appointment, int displayedIndex) {
        super(FXML);
        this.appointment = appointment;
        id.setText("A" + displayedIndex);
        clientName.setText("Client: " + appointment.getClient().getName().fullName);
        clientPhone.setText("Contact: " + appointment.getClient().getPhone().value);
        date.setText(appointment.getAppointmentDate().toUiString());
        timeOfDay.setText("Time: " + appointment.getAppointmentStartTime().toUiString());
        service.setText("Service: " + appointment.getService().getTitle().value);
        isDone.setText(appointment.getStatus().isDone() ? "done" : "not done");
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
        return id.getText().equals(card.id.getText())
                && appointment.equals(card.appointment);
    }
}
