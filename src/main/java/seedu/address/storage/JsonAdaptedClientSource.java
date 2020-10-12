package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.clientsource.ClientSource;

/**
 * Jackson-friendly version of {@link ClientSource}.
 */
class JsonAdaptedClientSource {

    private final String clientSourceName;

    /**
     * Constructs a {@code JsonAdaptedClientSource} with the given {@code clientSourceName}.
     */
    @JsonCreator
    public JsonAdaptedClientSource(String clientSourceName) {
        this.clientSourceName = clientSourceName;
    }

    /**
     * Converts a given {@code ClientSource} into this class for Jackson use.
     */
    public JsonAdaptedClientSource(ClientSource source) {
        clientSourceName = source.clientSourceName;
    }

    @JsonValue
    public String getClientSourceName() {
        return clientSourceName;
    }

    /**
     * Converts this Jackson-friendly adapted clientsource object into the model's {@code ClientSource} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted clientsource.
     */
    public ClientSource toModelType() throws IllegalValueException {
        if (!ClientSource.isValidClientSourceName(clientSourceName)) {
            throw new IllegalValueException(ClientSource.MESSAGE_CONSTRAINTS);
        }
        return new ClientSource(clientSourceName);
    }

}
