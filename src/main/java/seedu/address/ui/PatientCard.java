package seedu.address.ui;

import java.util.Comparator;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.patient.Appointment;
import seedu.address.model.patient.Patient;

/**
 * An UI component that displays information of a {@code Patient}.
 */
public class PatientCard extends UiPart<Region> {

    private static final String FXML = "PatientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on HospifyBook level 4</a>
     */

    public final Patient patient;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label nric;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane allergies;
    @FXML
    private Label noAllergiesLabel;
    @FXML
    private Label numberOfAppointments;
    @FXML
    private Button copyButton;
    @FXML
    private Label copyButtonNotification;


    /**
     * Creates a {@code PatientCard} with the given {@code Patient} and index to display.
     */
    public PatientCard(Patient patient, int displayedIndex) {
        super(FXML);
        this.patient = patient;
        id.setText(displayedIndex + ". ");
        name.setText(StringUtil.stringBreaker(patient.getName().fullName, 7, 70, false, 3, ".."));
        nric.setText(patient.getNric().value);
        phone.setText(StringUtil.stringBreaker(patient.getPhone().value, 0, 80, false));
        address.setText(StringUtil.stringBreaker(patient.getAddress().value, 8, 80, false, 5, ".."));
        String patientEmail = StringUtil.stringBreaker(patient.getEmail().value, 0, 80, false, 4, "..");

        if (patientEmail.isEmpty()) {
            email.setText("NONE");
        } else {
            email.setText(patientEmail);
        }

        if (patient.getAllergies().isEmpty()) {
            noAllergiesLabel.setText("NONE");
        } else {
            noAllergiesLabel.setText("");
        }

        Set<Appointment> appointmentList = patient.getAppointments();
        int numOfAppts = 0;
        for (Appointment appt : appointmentList) {
            if (!Appointment.isPassed(appt.getAppointmentTime())) {
                numOfAppts++;
            }
        }
        numberOfAppointments.setText(Integer.toString(numOfAppts));
        patient.getAllergies().stream()
                .sorted(Comparator.comparing(allergy -> allergy.allergyName))
                .forEach(allergy -> allergies.getChildren()
                        .add(new Label(StringUtil.stringBreaker(allergy.allergyName, 8, 80, false))));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PatientCard)) {
            return false;
        }

        // state check
        PatientCard card = (PatientCard) other;
        return id.getText().equals(card.id.getText())
                && patient.equals(card.patient);
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        copyButtonNotification.setText("Link Copied!");
        url.putString(patient.getMedicalRecord().value);
        clipboard.setContent(url);
    }

    /**
     * Shows the Appointment window when patient is clicked.
     */
    @FXML
    private void onDoubleClick() {
        AppointmentWindow appointmentWindow = MainWindow.appointmentWindow;
        appointmentWindow.setAppointmentWindow(patient);
        if (appointmentWindow.isShowing()) {
            appointmentWindow.focus();
        } else {
            appointmentWindow.show();
        }
    }

}
