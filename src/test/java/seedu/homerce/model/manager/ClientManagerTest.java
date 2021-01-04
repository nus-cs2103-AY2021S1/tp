package seedu.homerce.model.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.client.TypicalClients.ALICE;
import static seedu.homerce.testutil.client.TypicalClients.getTypicalClientManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.homerce.model.client.Client;
import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.util.uniquelist.exceptions.DuplicateItemException;
import seedu.homerce.testutil.client.ClientBuilder;

public class ClientManagerTest {

    private final ClientManager clientManager = new ClientManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), clientManager.getClientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> clientManager.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyClientManager_replacesData() {
        ClientManager newData = getTypicalClientManager();
        clientManager.resetData(newData);
        assertEquals(newData, clientManager);
    }

    @Test
    public void resetData_withDuplicateClients_throwsDuplicateItemException() {
        // Two clients with the same identity fields
        Client editedAlice = new ClientBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Client> newClients = Arrays.asList(ALICE, editedAlice);
        ClientManagerStub newData = new ClientManagerStub(newClients, FXCollections.observableArrayList());

        assertThrows(DuplicateItemException.class, () -> clientManager.resetData(newData));
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> clientManager.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInClientManager_returnsFalse() {
        assertFalse(clientManager.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientInClientManager_returnsTrue() {
        clientManager.addClient(ALICE);
        assertTrue(clientManager.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientWithSameIdentityFieldsInClientManager_returnsTrue() {
        clientManager.addClient(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(clientManager.hasClient(editedAlice));
    }

    @Test
    public void getClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> clientManager.getClientList().remove(0));
    }

    /**
     * A stub ReadOnlyClientManager whose clients list can violate interface constraints.
     */
    private static class ClientManagerStub implements ReadOnlyClientManager {
        private final ObservableList<Client> clients = FXCollections.observableArrayList();

        ClientManagerStub(Collection<Client> clients, Collection<Expense> expenses) {
            this.clients.setAll(clients);
        }

        @Override
        public ObservableList<Client> getClientList() {
            return clients;
        }
    }
}


