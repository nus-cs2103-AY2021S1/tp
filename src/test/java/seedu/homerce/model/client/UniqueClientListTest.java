package seedu.homerce.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.client.TypicalClients.ALICE;
import static seedu.homerce.testutil.client.TypicalClients.AMY;
import static seedu.homerce.testutil.client.TypicalClients.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.client.exceptions.ClientNotFoundException;
import seedu.homerce.model.util.uniquelist.exceptions.DuplicateItemException;
import seedu.homerce.model.util.uniquelist.exceptions.ItemNotFoundException;
import seedu.homerce.testutil.client.ClientBuilder;

public class UniqueClientListTest {

    private final UniqueClientList uniqueClientList = new UniqueClientList();

    @Test
    public void contains_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.containsPhone(null));
        assertThrows(NullPointerException.class, () -> uniqueClientList.contains(null));
    }

    @Test
    public void contains_clientNotInList_returnsFalse() {
        assertFalse(uniqueClientList.contains(ALICE));
    }

    @Test
    public void contains_clientInList_returnsTrue() {
        uniqueClientList.add(ALICE);
        assertTrue(uniqueClientList.contains(ALICE));
    }

    @Test
    public void contains_clientWithSameIdentityFieldsInList_returnsTrue() {
        uniqueClientList.add(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueClientList.contains(editedAlice));
    }

    @Test
    public void add_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.add(null));
    }

    @Test
    public void add_duplicateClient_throwsDuplicateItemException() {
        uniqueClientList.add(ALICE);
        assertThrows(DuplicateItemException.class, () -> uniqueClientList.add(ALICE));
    }

    @Test
    public void setClient_nullTargetClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setItem(null, ALICE));
    }

    @Test
    public void setClient_nullEditedClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setItem(ALICE, null));
    }

    @Test
    public void setClient_targetClientNotInList_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueClientList.setItem(ALICE, ALICE));
    }

    @Test
    public void setClient_editedClientIsSame_success() {
        uniqueClientList.add(ALICE);
        uniqueClientList.setItem(ALICE, ALICE);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(ALICE);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClient_editedClientHasSameIdentity_success() {
        uniqueClientList.add(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueClientList.setItem(ALICE, editedAlice);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(editedAlice);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClient_editedClientHasDifferentIdentity_success() {
        uniqueClientList.add(ALICE);
        uniqueClientList.setItem(ALICE, BOB);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(BOB);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClient_editedClientHasNonUniqueIdentity_throwsDuplicateItemException() {
        uniqueClientList.add(ALICE);
        uniqueClientList.add(BOB);
        assertThrows(DuplicateItemException.class, () -> uniqueClientList.setItem(ALICE, BOB));
    }

    @Test
    public void remove_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.remove(null));
    }

    @Test
    public void remove_clientDoesNotExist_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueClientList.remove(ALICE));
    }

    @Test
    public void remove_existingClient_removesClient() {
        uniqueClientList.add(ALICE);
        uniqueClientList.remove(ALICE);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClients_nullUniqueClientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setItems((UniqueClientList) null));
    }

    @Test
    public void setClients_uniqueClientList_replacesOwnListWithProvidedUniqueClientList() {
        uniqueClientList.add(ALICE);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(BOB);
        uniqueClientList.setItems(expectedUniqueClientList);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClients_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setItems((List<Client>) null));
    }

    @Test
    public void setClients_list_replacesOwnListWithProvidedList() {
        uniqueClientList.add(ALICE);
        List<Client> clientList = Collections.singletonList(BOB);
        uniqueClientList.setItems(clientList);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(BOB);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClients_listWithDuplicateClients_throwsDuplicateItemException() {
        List<Client> listWithDuplicateClients = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateItemException.class, () -> uniqueClientList.setItems(listWithDuplicateClients));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueClientList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void testHashCode() {
        UniqueClientList firstList = new UniqueClientList();
        UniqueClientList secondList = new UniqueClientList();
        firstList.add(ALICE);
        secondList.add(ALICE);

        assertEquals(firstList.hashCode(), secondList.hashCode());

        secondList.add(BOB);
        assertNotEquals(firstList.hashCode(), secondList.hashCode());
    }

    @Test
    public void getClientByPhone() {
        uniqueClientList.add(BOB);
        uniqueClientList.add(ALICE);
        uniqueClientList.add(AMY);

        Phone validPhone = new Phone("94351253");
        Phone invalidPhone = new Phone("92121212");

        assertEquals(uniqueClientList.getClientByPhone(validPhone), ALICE);
        assertNotEquals(uniqueClientList.getClientByPhone(validPhone), BOB);

        assertThrows(ClientNotFoundException.class, () -> uniqueClientList.getClientByPhone(invalidPhone));
    }
}
