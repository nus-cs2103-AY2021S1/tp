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
import seedu.address.model.patient.Patient;

public class AppointmentWindow extends UiPart<Stage> {

    private final Logger logger = LogsCenter.getLogger(getClass());
    private static final String FXML = "AppointmentWindow.fxml";
    private final ObservableList<AppointmentDescription> appointmentDescriptions = FXCollections.observableArrayList();

    @FXML
    private TableView<AppointmentDescription> appointmentTable;

    @FXML
    private Label patientName;

    @FXML
    private Label patientNric;

    @FXML
    private Label patientAddress;

    @FXML
    private FlowPane patientAllergies;


    public AppointmentWindow() {
        this(new Stage());
    }

    public AppointmentWindow(Stage root) {
        super(FXML, root);
        appointmentTable.setItems(appointmentDescriptions);
    }

    public void setPatientAppointments(Patient patient) {
        patientName.setText(patient.getName().fullName);
        patientAddress.setText(patient.getAddress().value);
        patientNric.setText(patient.getNric().value);
        patient.getAllergies().stream()
                .sorted(Comparator.comparing(allergy -> allergy.allergyName))
                .forEach(allergy -> patientAllergies.getChildren().add(new Label(allergy.allergyName)));

        patient.getAppointments().forEach(appointment -> appointmentDescriptions.add(
                new AppointmentDescription(appointment.getAppointmentTimeString(), appointment
                        .getAppointmentDescription())));
    }

    public void show() {
        logger.fine("Showing appointment of patient");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
