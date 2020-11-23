package seedu.address.ui.appointment;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.ui.EntityListPanel;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of appointments.
 */
public class AppointmentListPanel extends EntityListPanel<Appointment> {

    private final Logger logger = LogsCenter.getLogger(AppointmentListPanel.class);

    /**
     * Creates a {@code AppointmentListPanel} with the given {@code ObservableList}.
     */
    public AppointmentListPanel(ObservableList<Appointment> appointmentList) {
        super(appointmentList);
        labelName.setText("Appointments");
    }

    @Override
    public UiPart<Region> getCard(Appointment appointment, int index) {
        return new AppointmentCard(appointment, index);
    }


}
