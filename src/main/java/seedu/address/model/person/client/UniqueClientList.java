package seedu.address.model.person.client;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.transformation.FilteredList;
import seedu.address.model.UniqueEntityList;
import seedu.address.model.person.RecordContainsClientIdPredicate;
import seedu.address.model.person.client.exception.ClientNotFoundException;
import seedu.address.model.person.client.exception.DuplicateClientException;


public class UniqueClientList extends UniqueEntityList<Client> {


    /**
     * Replaces the client {@code target} in the list with {@code editedClient}.
     * {@code target} must exist in the list.
     * The client identity of {@code editedClient} must not be the same as another existing client in the list.
     */
    public void setClient(Client target, Client editedClient) {
        setEntity(target, editedClient);
    }


    public void setClients(UniqueClientList replacement) {
        setEntities(replacement);
    }

    /**
     * Replaces the contents of this list with {@code clients}.
     * {@code clients} must not contain duplicate clients.
     */
    public void setClients(List<Client> clients) {
        setEntities(clients);
    }

    /**
     * Returns Client with given ClientId.
     */
    public Client findClientById(ClientId idToCheck) {
        requireNonNull(idToCheck);
        Predicate<Client> predicate = new RecordContainsClientIdPredicate(idToCheck);
        FilteredList<Client> clientWithId = internalList.filtered(predicate);
        if (clientWithId.isEmpty()) {
            return null;
        }
        return clientWithId.get(0);
    }

    @Override
    public DuplicateClientException duplicateException() {
        return new DuplicateClientException();
    }

    @Override
    public ClientNotFoundException notFoundException() {
        return new ClientNotFoundException();
    }
}

