package seedu.address.model;

import seedu.address.model.appointment.AppointmentId;
import seedu.address.model.person.client.ClientId;
import seedu.address.model.person.hairdresser.HairdresserId;

/**
 * This class is a singleton, only one instance can exist at any one time.
 * Stores the current maximum client, hairdresser and appointment IDs to generate
 * the next unique client, hairdresser, or appointment.
 */
public final class IdCounter {

    private static IdCounter instance;
    private static int clientId = 0;
    private static int hairdresserId = 0;
    private static int appointmentId = 0;

    private IdCounter() {

    }

    public static IdCounter getInstance() {
        if (instance == null) {
            instance = new IdCounter();
        }

        return instance;
    }

    /**
     * A method to reset the instance of IdCounter.
     */
    public static void reset() {
        instance = new IdCounter();
        clientId = 0;
        hairdresserId = 0;
        appointmentId = 0;
    }

    /**
     * Creates a new unique client ID when called.
     */
    public ClientId generateNewClientId() {
        clientId += 1;
        return new ClientId(String.valueOf(clientId));
    }

    /**
     * Creates a new unique hairdresser ID when called.
     */
    public HairdresserId generateNewHairdresserId() {
        hairdresserId += 1;
        return new HairdresserId(String.valueOf(hairdresserId));
    }

    /**
     * Creates a new unique appointment ID when called.
     */
    public AppointmentId generateNewAppointmentId() {
        appointmentId += 1;
        return new AppointmentId(String.valueOf(appointmentId));
    }

    public void setCurrentMaxClientId(int id) {
        clientId = id;
    }
    public void setCurrentMaxHairdresserId(int id) {
        hairdresserId = id;
    }
    public void setCurrentMaxAppointmentId(int id) {
        appointmentId = id;
    }

    public int getCurrentMaxClientId() {
        return clientId;
    }
    public int getCurrentMaxHairdresserId() {
        return hairdresserId;
    }
    public int getCurrentMaxAppointmentId() {
        return appointmentId;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IdCounter // instanceof handles nulls
                && clientId == ((((IdCounter) other).getCurrentMaxClientId()))
                && hairdresserId == ((((IdCounter) other).getCurrentMaxHairdresserId()))
                && appointmentId == ((((IdCounter) other).getCurrentMaxAppointmentId())));
    }
}
