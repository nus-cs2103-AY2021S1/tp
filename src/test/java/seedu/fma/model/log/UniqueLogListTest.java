//package seedu.address.model.log;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
//import static seedu.address.testutil.Assert.assertThrows;
//import static seedu.address.testutil.TypicalLogs.ALICE;
//import static seedu.address.testutil.TypicalLogs.BOB;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.model.log.exceptions.DuplicateLogException;
//import seedu.address.model.log.exceptions.LogNotFoundException;
//import seedu.address.testutil.LogBuilder;
//
//public class UniqueLogListTest {
//
////    private final UniqueLogList UniqueLogList = new UniqueLogList();
////
////    @Test
////    public void contains_nullPerson_throwsNullPointerException() {
////        assertThrows(NullPointerException.class, () -> UniqueLogList.contains(null));
////    }
////
////    @Test
////    public void contains_personNotInList_returnsFalse() {
////        assertFalse(UniqueLogList.contains(ALICE));
////    }
////
////    @Test
////    public void contains_personInList_returnsTrue() {
////        UniqueLogList.add(ALICE);
////        assertTrue(UniqueLogList.contains(ALICE));
////    }
////
////    @Test
////    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
////        UniqueLogList.add(ALICE);
////        Log editedAlice = new LogBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
////                .build();
////        assertTrue(UniqueLogList.contains(editedAlice));
////    }
////
////    @Test
////    public void add_nullPerson_throwsNullPointerException() {
////        assertThrows(NullPointerException.class, () -> UniqueLogList.add(null));
////    }
////
////    @Test
////    public void add_duplicatePerson_throwsDuplicatePersonException() {
////        UniqueLogList.add(ALICE);
////        assertThrows(DuplicateLogException.class, () -> UniqueLogList.add(ALICE));
////    }
////
////    @Test
////    public void setPerson_nullTargetPerson_throwsNullPointerException() {
////        assertThrows(NullPointerException.class, () -> UniqueLogList.setPerson(null, ALICE));
////    }
////
////    @Test
////    public void setPerson_nullEditedPerson_throwsNullPointerException() {
////        assertThrows(NullPointerException.class, () -> UniqueLogList.setPerson(ALICE, null));
////    }
////
////    @Test
////    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
////        assertThrows(LogNotFoundException.class, () -> UniqueLogList.setPerson(ALICE, ALICE));
////    }
////
////    @Test
////    public void setPerson_editedPersonIsSamePerson_success() {
////        UniqueLogList.add(ALICE);
////        UniqueLogList.setPerson(ALICE, ALICE);
////        UniqueLogList expectedUniqueLogList = new UniqueLogList();
////        expectedUniqueLogList.add(ALICE);
////        assertEquals(expectedUniqueLogList, UniqueLogList);
////    }
////
////    @Test
////    public void setPerson_editedPersonHasSameIdentity_success() {
////        UniqueLogList.add(ALICE);
////        Log editedAlice = new LogBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
////                .build();
////        UniqueLogList.setPerson(ALICE, editedAlice);
////        UniqueLogList expectedUniqueLogList = new UniqueLogList();
////        expectedUniqueLogList.add(editedAlice);
////        assertEquals(expectedUniqueLogList, UniqueLogList);
////    }
////
////    @Test
////    public void setPerson_editedPersonHasDifferentIdentity_success() {
////        UniqueLogList.add(ALICE);
////        UniqueLogList.setPerson(ALICE, BOB);
////        UniqueLogList expectedUniqueLogList = new UniqueLogList();
////        expectedUniqueLogList.add(BOB);
////        assertEquals(expectedUniqueLogList, UniqueLogList);
////    }
////
////    @Test
////    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
////        UniqueLogList.add(ALICE);
////        UniqueLogList.add(BOB);
////        assertThrows(DuplicateLogException.class, () -> UniqueLogList.setPerson(ALICE, BOB));
////    }
////
////    @Test
////    public void remove_nullPerson_throwsNullPointerException() {
////        assertThrows(NullPointerException.class, () -> UniqueLogList.remove(null));
////    }
////
////    @Test
////    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
////        assertThrows(LogNotFoundException.class, () -> UniqueLogList.remove(ALICE));
////    }
////
////    @Test
////    public void remove_existingPerson_removesPerson() {
////        UniqueLogList.add(ALICE);
////        UniqueLogList.remove(ALICE);
////        UniqueLogList expectedUniqueLogList = new UniqueLogList();
////        assertEquals(expectedUniqueLogList, UniqueLogList);
////    }
////
////    @Test
////    public void setPersons_nullUniqueLogList_throwsNullPointerException() {
////        assertThrows(NullPointerException.class, () -> UniqueLogList.setPersons((UniqueLogList) null));
////    }
////
////    @Test
////    public void setPersons_UniqueLogList_replacesOwnListWithProvidedUniqueLogList() {
////        UniqueLogList.add(ALICE);
////        UniqueLogList expectedUniqueLogList = new UniqueLogList();
////        expectedUniqueLogList.add(BOB);
////        UniqueLogList.setPersons(expectedUniqueLogList);
////        assertEquals(expectedUniqueLogList, UniqueLogList);
////    }
////
////    @Test
////    public void setPersons_nullList_throwsNullPointerException() {
////        assertThrows(NullPointerException.class, () -> UniqueLogList.setPersons((List<Log>) null));
////    }
////
////    @Test
////    public void setPersons_list_replacesOwnListWithProvidedList() {
////        UniqueLogList.add(ALICE);
////        List<Log> logList = Collections.singletonList(BOB);
////        UniqueLogList.setPersons(logList);
////        UniqueLogList expectedUniqueLogList = new UniqueLogList();
////        expectedUniqueLogList.add(BOB);
////        assertEquals(expectedUniqueLogList, UniqueLogList);
////    }
////
////    @Test
////    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
////        List<Log> listWithDuplicateLogs = Arrays.asList(ALICE, ALICE);
////        assertThrows(DuplicateLogException.class, () -> UniqueLogList.setPersons(listWithDuplicateLogs));
////    }
////
////    @Test
////    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
////        assertThrows(UnsupportedOperationException.class, ()
////            -> UniqueLogList.asUnmodifiableObservableList().remove(0));
////    }
//}
