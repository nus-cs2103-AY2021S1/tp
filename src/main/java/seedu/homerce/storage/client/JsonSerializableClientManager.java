package seedu.homerce.storage.client;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.homerce.commons.exceptions.IllegalValueException;
import seedu.homerce.model.client.Client;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ReadOnlyClientManager;

/**
 * An Immutable client manager that is serializable to JSON format.
 */
@JsonRootName(value = "clientmanager")
class JsonSerializableClientManager {

    public static final String MESSAGE_DUPLICATE_CLIENT = "Clients list contains duplicate client(s).";

    private final List<JsonAdaptedClient> clients = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableClientManager} with the given clients.
     */
    @JsonCreator
    public JsonSerializableClientManager(@JsonProperty("clients") List<JsonAdaptedClient> clients) {
        this.clients.addAll(clients);
    }

    /**
     * Converts a given {@code ReadOnlyClientManager} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableClientManager}.
     */
    public JsonSerializableClientManager(ReadOnlyClientManager source) {
        clients.addAll(source.getClientList().stream().map(JsonAdaptedClient::new).collect(Collectors.toList()));
    }

    /**
     * Converts this homerce into the model's {@code ClientManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ClientManager toModelType() throws IllegalValueException {
        ClientManager clientManager = new ClientManager();
        for (JsonAdaptedClient jsonAdaptedClient : clients) {
            Client client = jsonAdaptedClient.toModelType();
            if (clientManager.hasClient(client)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLIENT);
            }
            clientManager.addClient(client);
        }
        return clientManager;
    }

}
