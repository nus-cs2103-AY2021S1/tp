package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.IdCounter;

public class JsonAdaptedIdCounter {
    private final int clientId;
    private final int hairdresserId;
    private final int appointmentId;


    /**
     * Constructs a {@code JsonAdaptedIdCounter} with the given {@code IdCounter} details.
     */
    @JsonCreator
    protected JsonAdaptedIdCounter(@JsonProperty("clientId") int clientId,
                                   @JsonProperty("hairdresserId") int hairdresserId,
                                   @JsonProperty("appointmentId") int appointmentId) {
        this.clientId = clientId;
        this.hairdresserId = hairdresserId;
        this.appointmentId = appointmentId;
    }

    /**
     * Converts a given {@code PersonIdCounter} into this class for Jackson use.
     */
    protected JsonAdaptedIdCounter(IdCounter source) {
        clientId = source.getCurrentMaxClientId();
        hairdresserId = source.getCurrentMaxHairdresserId();
        appointmentId = source.getCurrentMaxAppointmentId();

    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code PersonIdCounter} object.
     */
    public IdCounter toModelType() {
        IdCounter.getInstance().setCurrentMaxClientId(clientId);
        IdCounter.getInstance().setCurrentMaxHairdresserId(hairdresserId);
        IdCounter.getInstance().setCurrentMaxAppointmentId(appointmentId);
        return IdCounter.getInstance();
    }
}
