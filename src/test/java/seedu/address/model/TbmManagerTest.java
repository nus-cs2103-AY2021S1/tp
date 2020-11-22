package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestUtil.basicEqualsTests;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.getTypicalTbmManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.client.exceptions.ClientNotFoundException;
import seedu.address.model.client.exceptions.DuplicateClientException;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;
import seedu.address.testutil.ClientBuilder;

public class TbmManagerTest {

    private static final Client CLIENT = new ClientBuilder(ALICE).build();
    private static final CountryNote COUNTRY_NOTE = new CountryNote("country note", new Country("US"));
    private TbmManager tbmManager;

    private TbmManager tbmManagerWithClient;
    private TbmManager tbmManagerWithCountryNote;

    @BeforeEach
    public void setUp() {
        tbmManager = new TbmManager();
        tbmManagerWithClient = new TbmManager();
        tbmManagerWithCountryNote = new TbmManager();
        tbmManagerWithClient.addClient(CLIENT);
        tbmManagerWithCountryNote.addCountryNote(COUNTRY_NOTE);
    }

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), tbmManager.getClientList());
        assertEquals(Collections.emptyList(), tbmManager.getCountryNoteList());

        TbmManager newTbmManager = new TbmManager();
        newTbmManager.addCountryNote(COUNTRY_NOTE);
        newTbmManager.addClient(CLIENT);
        tbmManager = new TbmManager(newTbmManager);
        assertEquals(tbmManager.getCountryNoteList().size(), 1);
        assertEquals(tbmManager.getClientList().size(), 1);
        assertEquals(tbmManager.getCountryNoteList().get(0), COUNTRY_NOTE);
        assertEquals(tbmManager.getClientList().get(0), CLIENT);
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tbmManager.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTbmManager_replacesData() {
        TbmManager newData = getTypicalTbmManager();
        tbmManager.resetData(newData);
        assertEquals(newData, tbmManager);
    }

    @Test
    public void resetData_withDuplicateClients_throwsDuplicateClientException() {
        // Two clients with the same identity fields
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        List<Client> newClients = Arrays.asList(ALICE, editedAlice);
        TbmManagerStub newData = new TbmManagerStub(newClients);

        assertThrows(DuplicateClientException.class, () -> tbmManager.resetData(newData));
    }

    @Test
    public void setClient_nullClients_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tbmManager.setClient(null, CLIENT));
        assertThrows(NullPointerException.class, () -> tbmManager.setClient(CLIENT, null));
    }

    @Test
    public void setClient_clientNotInTbmManager_throwsClientNotFoundException() {
        assertThrows(ClientNotFoundException.class, () -> tbmManager.setClient(ALICE, ALICE));
    }

    @Test
    public void setClient_clientInTbmManager_success() {
        Client editedClient = new ClientBuilder(ALICE).withName("EditedClient").build();
        tbmManagerWithClient.setClient(CLIENT, editedClient);
        assertFalse(tbmManagerWithClient.hasClient(CLIENT));
        assertTrue(tbmManagerWithClient.hasClient(editedClient));
    }

    @Test
    public void removeClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tbmManager.removeClient(null));
    }

    @Test
    public void removeClient_clientNotInTbmManager_throwsClientNotFoundException() {
        assertThrows(ClientNotFoundException.class, () -> tbmManager.removeClient(ALICE));
    }

    @Test
    public void removeClient_clientInTbmManager_success() {
        assertTrue(tbmManagerWithClient.hasClient(CLIENT));
        tbmManagerWithClient.removeClient(CLIENT);
        assertFalse(tbmManagerWithClient.hasClient(CLIENT));
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tbmManager.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInTbmManager_returnsFalse() {
        assertFalse(tbmManager.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientInTbmManager_returnsTrue() {
        tbmManager.addClient(ALICE);
        assertTrue(tbmManager.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientWithSameIdentityFieldsInTbmManager_returnsTrue() {
        tbmManager.addClient(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(tbmManager.hasClient(editedAlice));
    }

    @Test
    public void getClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> tbmManager.getClientList().remove(0));
    }

    @Test
    public void addCountryNote_updateCountryNoteList() {
        CountryNote countryNote = new CountryNote("random", new Country("SG"));
        assertFalse(tbmManager.hasCountryNote(countryNote));
        tbmManager.addCountryNote(countryNote);
        assertTrue(tbmManager.hasCountryNote(countryNote));
    }

    @Test
    public void deleteCountryNote_updateCountryNoteList() {
        CountryNote countryNote = new CountryNote("random2", new Country("SG"));
        assertFalse(tbmManager.hasCountryNote(countryNote));
        tbmManager.addCountryNote(countryNote);
        assertTrue(tbmManager.hasCountryNote(countryNote));
        tbmManager.deleteCountryNote(countryNote);
        assertFalse(tbmManager.hasCountryNote(countryNote));
    }

    @Test
    public void toString_test() {
        assertEquals(tbmManager.toString(), "0 clients");
        assertEquals(tbmManagerWithCountryNote.toString(), "0 clients");
        assertEquals(tbmManagerWithClient.toString(), "1 clients");
    }

    @Test
    public void equals() {
        // basic equals tests
        basicEqualsTests(tbmManager);

        // same clients and country notes -> returns true
        assertTrue(tbmManager.equals(new TbmManager()));

        // different clients -> returns false
        assertFalse(tbmManager.equals(tbmManagerWithClient));

        // different country notes -> returns false
        assertFalse(tbmManager.equals(tbmManagerWithCountryNote));
    }

    @Test
    public void hashCode_test() {
        // same object -> same hashcode
        assertEquals(tbmManager.hashCode(), tbmManager.hashCode());

        // same clients and country notes -> same hashcode
        assertEquals(tbmManager.hashCode(), new TbmManager().hashCode());

        // different clients -> different hashcode
        assertNotEquals(tbmManager.hashCode(), tbmManagerWithClient.hashCode());

        // different country notes -> different hashcode
        assertNotEquals(tbmManager.hashCode(), tbmManagerWithCountryNote.hashCode());
    }

    /**
     * A stub ReadOnlyTbmManager whose clients list can violate interface constraints.
     */
    private static class TbmManagerStub implements ReadOnlyTbmManager {
        private final ObservableList<Client> clients = FXCollections.observableArrayList();

        TbmManagerStub(Collection<Client> clients) {
            this.clients.setAll(clients);
        }

        @Override
        public ObservableList<Client> getClientList() {
            return clients;
        }

        @Override
        public ObservableList<CountryNote> getCountryNoteList() {
            return null;
        }
    }

}
