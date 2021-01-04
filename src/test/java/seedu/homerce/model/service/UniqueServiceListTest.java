package seedu.homerce.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.service.TypicalServices.LASH_LIFT;
import static seedu.homerce.testutil.service.TypicalServices.MANICURE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.util.uniquelist.exceptions.DuplicateItemException;
import seedu.homerce.model.util.uniquelist.exceptions.ItemNotFoundException;
import seedu.homerce.testutil.service.ServiceBuilder;

public class UniqueServiceListTest {

    private final UniqueServiceList uniqueServiceList = new UniqueServiceList();

    @Test
    public void contains_serviceNotInList_returnsFalse() {
        assertFalse(uniqueServiceList.contains(LASH_LIFT));
    }

    @Test
    public void contains_serviceInList_returnsTrue() {
        uniqueServiceList.add(LASH_LIFT);
        assertTrue(uniqueServiceList.contains(LASH_LIFT));
    }

    @Test
    public void contains_serviceWithSameIdentityFieldsInList_returnsTrue() {
        uniqueServiceList.add(LASH_LIFT);
        Service editedAlice = new ServiceBuilder(LASH_LIFT).build();
        assertTrue(uniqueServiceList.contains(editedAlice));
    }

    @Test
    public void add_nullService_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueServiceList.add(null));
    }

    @Test
    public void add_duplicateService_throwsDuplicateItemException() {
        uniqueServiceList.add(LASH_LIFT);
        assertThrows(DuplicateItemException.class, () -> uniqueServiceList.add(LASH_LIFT));
    }

    @Test
    public void setService_nullTargetService_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueServiceList.setItem(null, LASH_LIFT));
    }

    @Test
    public void setService_nullEditedService_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueServiceList.setItem(LASH_LIFT, null));
    }

    @Test
    public void setService_targetServiceNotInList_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueServiceList.setItem(LASH_LIFT, LASH_LIFT));
    }

    @Test
    public void setService_editedServiceIsSameService_success() {
        uniqueServiceList.add(LASH_LIFT);
        uniqueServiceList.setItem(LASH_LIFT, LASH_LIFT);
        UniqueServiceList expectedUniqueServiceList = new UniqueServiceList();
        expectedUniqueServiceList.add(LASH_LIFT);
        assertEquals(expectedUniqueServiceList, uniqueServiceList);
    }

    @Test
    public void setService_editedServiceHasSameIdentity_success() {
        uniqueServiceList.add(LASH_LIFT);
        Service editedAlice = new ServiceBuilder(LASH_LIFT).build();
        uniqueServiceList.setItem(LASH_LIFT, editedAlice);
        UniqueServiceList expectedUniqueServiceList = new UniqueServiceList();
        expectedUniqueServiceList.add(editedAlice);
        assertEquals(expectedUniqueServiceList, uniqueServiceList);
    }

    @Test
    public void setService_editedServiceHasDifferentIdentity_success() {
        uniqueServiceList.add(LASH_LIFT);
        uniqueServiceList.setItem(LASH_LIFT, MANICURE);
        UniqueServiceList expectedUniqueServiceList = new UniqueServiceList();
        expectedUniqueServiceList.add(MANICURE);
        assertEquals(expectedUniqueServiceList, uniqueServiceList);
    }

    @Test
    public void setService_editedServiceHasNonUniqueIdentity_throwsDuplicateItemException() {
        uniqueServiceList.add(LASH_LIFT);
        uniqueServiceList.add(MANICURE);
        assertThrows(DuplicateItemException.class, () -> uniqueServiceList.setItem(LASH_LIFT, MANICURE));
    }

    @Test
    public void remove_nullService_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueServiceList.remove(null));
    }

    @Test
    public void remove_serviceDoesNotExist_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueServiceList.remove(LASH_LIFT));
    }

    @Test
    public void remove_existingService_removesService() {
        uniqueServiceList.add(LASH_LIFT);
        uniqueServiceList.remove(LASH_LIFT);
        UniqueServiceList expectedUniqueServiceList = new UniqueServiceList();
        assertEquals(expectedUniqueServiceList, uniqueServiceList);
    }

    @Test
    public void setServices_nullUniqueServiceList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueServiceList.setItems((UniqueServiceList) null));
    }

    @Test
    public void setItems_uniqueServiceList_replacesOwnListWithProvidedUniqueServiceList() {
        uniqueServiceList.add(LASH_LIFT);
        UniqueServiceList expectedUniqueServiceList = new UniqueServiceList();
        expectedUniqueServiceList.add(MANICURE);
        uniqueServiceList.setItems(expectedUniqueServiceList);
        assertEquals(expectedUniqueServiceList, uniqueServiceList);
    }

    @Test
    public void setItems_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueServiceList.setItems((List<Service>) null));
    }

    @Test
    public void setItems_list_replacesOwnListWithProvidedList() {
        uniqueServiceList.add(LASH_LIFT);
        List<Service> serviceList = Collections.singletonList(MANICURE);
        uniqueServiceList.setItems(serviceList);
        UniqueServiceList expectedUniqueServiceList = new UniqueServiceList();
        expectedUniqueServiceList.add(MANICURE);
        assertEquals(expectedUniqueServiceList, uniqueServiceList);
    }

    @Test
    public void setItems_listWithDuplicateServices_throwsDuplicateServiceException() {
        List<Service> listWithDuplicateServices = Arrays.asList(LASH_LIFT, LASH_LIFT);
        assertThrows(DuplicateItemException.class, () -> uniqueServiceList.setItems(listWithDuplicateServices));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueServiceList.asUnmodifiableObservableList().remove(0));
    }
}
