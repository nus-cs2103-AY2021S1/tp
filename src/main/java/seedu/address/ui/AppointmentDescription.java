package seedu.address.ui;

import java.time.LocalDateTime;

import javafx.beans.property.SimpleStringProperty;
import seedu.address.model.patient.Appointment;

public class AppointmentDescription {
    private final LocalDateTime date;
    private final SimpleStringProperty description = new SimpleStringProperty("");

    AppointmentDescription(LocalDateTime date, String description) {
        assert date != null;
        if (description == null || description.isEmpty()) {
            this.description.set("No Description available for this Appointment");
        } else {
            this.description.set(description);
        }
        this.date = date;
    }

    /**
     * Returns Appointment date.
     * @return date.
     */
    public String getDate() {
        return Appointment.getAppointmentTimeString(date);
    }

    /**
     * Return date as a LocalDateTime.
     *
     * @return date.
     */
    public LocalDateTime getLocalDateTime() {
        return date;
    }

    /**
     * Returns Appointment Description.
     * @return
     */
    public String getDescription() {
        return description.get();
    }

    /**
     * Sets the Appointment description.
     * @param description
     */
    public void setDescription(String description) {
        this.description.set(description);
    }
}
