package seedu.address.ui;

import javafx.beans.property.SimpleStringProperty;

public class AppointmentDescription {
    private final SimpleStringProperty date = new SimpleStringProperty("");
    private final SimpleStringProperty description = new SimpleStringProperty("");

    AppointmentDescription(String date, String description) {
        assert date != null;
        if (description == null || description.isEmpty()) {
            this.description.set("No Description available for this Appointment");
        } else {
            this.description.set(description);
        }
        this.date.set(date);
    }

    /**
     * Returns Appointment date.
     * @return date.
     */
    public String getDate() {
        return date.get();
    }

    /**
     * Sets the Appointment date.
     * @param date
     */
    public void setDate(String date) {
        this.date.set(date);
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
