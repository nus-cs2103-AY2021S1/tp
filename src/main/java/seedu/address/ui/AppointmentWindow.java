package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.patient.Patient;

public class AppointmentWindow extends UiPart<Stage> {

    private static final String FXML = "AppointmentWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());
    private final ObservableList<AppointmentDescription> appointmentDescriptions = FXCollections.observableArrayList();
    private boolean sortByEarliestPolicy = true;

    @FXML
    private TableView<AppointmentDescription> appointmentTable;

    @FXML
    private Label patientName;

    @FXML
    private Label patientNric;

    @FXML
    private Label patientPhone;

    @FXML
    private Label patientAddress;

    @FXML
    private Label patientEmail;

    @FXML
    private FlowPane patientAllergies;

    @FXML
    private Label noAllergiesLabel;

    /**
     * Creates a Appointment Window.
     */
    public AppointmentWindow() {
        this(new Stage());
    }

    /**
     * Creates a Appointment Window.
     *
     * @param root Creates a Appointment Window with stage as the root.
     */
    public AppointmentWindow(Stage root) {
        super(FXML, root);
        appointmentTable.sortPolicyProperty().set(table -> {
            Comparator<AppointmentDescription> comparator;
            if (sortByEarliestPolicy) {
                comparator = (a1, a2) -> a2.getLocalDateTime().compareTo(a1.getLocalDateTime());
                sortByEarliestPolicy = false;
            } else {
                sortByEarliestPolicy = true;
                comparator = Comparator.comparing(AppointmentDescription::getLocalDateTime);
            }
            FXCollections.sort(table.getItems(), comparator);
            return true;
        });
        appointmentTable.setItems(appointmentDescriptions);
    }

    /**
     * Set the Appointment Window of the patient.
     *
     * @param patient Patient information to set.
     */
    public void setAppointmentWindow(Patient patient) {
        String name = StringUtil.stringBreaker(patient.getName().fullName, 5, 30,
                false, 1, "..");
        patientName.setText(name);
        patientAddress.setText(StringUtil.stringBreaker(patient.getAddress().value, 5, 85,
                false, 3, ".."));
        patientNric.setText(patient.getNric().value);
        patientPhone.setText(patient.getPhone().value);

        String email = patient.getEmail().value;
        if (email.isEmpty()) {
            patientEmail.setText("NONE");
        } else {
            patientEmail.setText(StringUtil.stringBreaker(email, 5, 120, false, 2, ".."));
        }

        if (patient.getAllergies().isEmpty()) {
            noAllergiesLabel.setText("NONE");
        } else {
            noAllergiesLabel.setText("");
        }

        // clear the lists
        patientAllergies.getChildren().clear();
        appointmentDescriptions.clear();
        appointmentTable.getColumns().get(1).setSortable(false);
        patient.getAllergies().stream()
                .sorted(Comparator.comparing(allergy -> allergy.allergyName))
                .forEach(allergy -> patientAllergies.getChildren()
                        .add(new Label(StringUtil.stringBreaker(allergy.allergyName, 7, 70, false))));

        patient.getAppointments().forEach(appointment -> appointmentDescriptions.add(
                new AppointmentDescription(appointment.getAppointmentTime(), appointment
                        .getAppointmentDescription())));
    }

    /**
     * Show the Appointment window.
     */
    public void show() {
        logger.fine("Showing appointment of patient");
        getRoot().show();
        getRoot().centerOnScreen();
        appointmentTable.sort();
    }

    /**
     * Returns true if Appointment window is showing.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the Appointment window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the Appointment window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
