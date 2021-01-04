package seedu.homerce.model.appointment.uniquelist;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.util.uniquelist.UniqueList;

/**
 * Extension of Unique list to accommodate appointments. Sorts based on date and time
 * whenever new entries are added into the list.
 */
public class UniqueAppointmentList extends UniqueList<Appointment> {
    private final AppointmentComparator appointmentComparator = new AppointmentComparator();

    @Override
    public void add(Appointment toAdd) {
        super.add(toAdd);
        FXCollections.sort(internalList, appointmentComparator);
    }

    @Override
    public void setItem(Appointment target, Appointment edited) {
        super.setItem(target, edited);
        FXCollections.sort(internalList, appointmentComparator);
    }

    @Override
    public void setItems(UniqueList<Appointment> replacement) {
        super.setItems(replacement);
        FXCollections.sort(internalList, appointmentComparator);
    }

    @Override
    public void setItems(List<Appointment> items) {
        super.setItems(items);
        FXCollections.sort(internalList, appointmentComparator);
    }

    public ObservableList<Appointment> asModifiableList() {
        return internalList;
    }
}
