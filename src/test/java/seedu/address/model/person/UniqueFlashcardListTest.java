package seedu.address.model.person;

public class UniqueFlashcardListTest {

//    private final UniquePersonList uniquePersonList = new UniquePersonList();
//
//    @Test
//    public void contains_nullPerson_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> uniquePersonList.contains(null));
//    }
//
//    @Test
//    public void contains_personNotInList_returnsFalse() {
//        assertFalse(uniquePersonList.contains(ALICE));
//    }
//
//    @Test
//    public void contains_personInList_returnsTrue() {
//        uniquePersonList.add(ALICE);
//        assertTrue(uniquePersonList.contains(ALICE));
//    }
//
//    @Test
//    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
//        uniquePersonList.add(ALICE);
//        Flashcard editedAlice = new FlashcardBuilder(ALICE).withAnswer(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
//                .build();
//        assertTrue(uniquePersonList.contains(editedAlice));
//    }
//
//    @Test
//    public void add_nullPerson_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> uniquePersonList.add(null));
//    }
//
//    @Test
//    public void add_duplicatePerson_throwsDuplicatePersonException() {
//        uniquePersonList.add(ALICE);
//        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.add(ALICE));
//    }
//
//    @Test
//    public void setPerson_nullTargetPerson_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> uniquePersonList.setFlashcard(null, ALICE));
//    }
//
//    @Test
//    public void setPerson_nullEditedPerson_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> uniquePersonList.setFlashcard(ALICE, null));
//    }
//
//    @Test
//    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
//        assertThrows(PersonNotFoundException.class, () -> uniquePersonList.setFlashcard(ALICE, ALICE));
//    }
//
//    @Test
//    public void setPerson_editedPersonIsSamePerson_success() {
//        uniquePersonList.add(ALICE);
//        uniquePersonList.setFlashcard(ALICE, ALICE);
//        UniquePersonList expectedUniquePersonList = new UniquePersonList();
//        expectedUniquePersonList.add(ALICE);
//        assertEquals(expectedUniquePersonList, uniquePersonList);
//    }
//
//    @Test
//    public void setPerson_editedPersonHasSameIdentity_success() {
//        uniquePersonList.add(ALICE);
//        Flashcard editedAlice = new FlashcardBuilder(ALICE).withAnswer(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
//                .build();
//        uniquePersonList.setFlashcard(ALICE, editedAlice);
//        UniquePersonList expectedUniquePersonList = new UniquePersonList();
//        expectedUniquePersonList.add(editedAlice);
//        assertEquals(expectedUniquePersonList, uniquePersonList);
//    }
//
//    @Test
//    public void setPerson_editedPersonHasDifferentIdentity_success() {
//        uniquePersonList.add(ALICE);
//        uniquePersonList.setFlashcard(ALICE, BOB);
//        UniquePersonList expectedUniquePersonList = new UniquePersonList();
//        expectedUniquePersonList.add(BOB);
//        assertEquals(expectedUniquePersonList, uniquePersonList);
//    }
//
//    @Test
//    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
//        uniquePersonList.add(ALICE);
//        uniquePersonList.add(BOB);
//        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.setFlashcard(ALICE, BOB));
//    }
//
//    @Test
//    public void remove_nullPerson_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> uniquePersonList.remove(null));
//    }
//
//    @Test
//    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
//        assertThrows(PersonNotFoundException.class, () -> uniquePersonList.remove(ALICE));
//    }
//
//    @Test
//    public void remove_existingPerson_removesPerson() {
//        uniquePersonList.add(ALICE);
//        uniquePersonList.remove(ALICE);
//        UniquePersonList expectedUniquePersonList = new UniquePersonList();
//        assertEquals(expectedUniquePersonList, uniquePersonList);
//    }
//
//    @Test
//    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((UniquePersonList) null));
//    }
//
//    @Test
//    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
//        uniquePersonList.add(ALICE);
//        UniquePersonList expectedUniquePersonList = new UniquePersonList();
//        expectedUniquePersonList.add(BOB);
//        uniquePersonList.setPersons(expectedUniquePersonList);
//        assertEquals(expectedUniquePersonList, uniquePersonList);
//    }
//
//    @Test
//    public void setPersons_nullList_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((List<Flashcard>) null));
//    }
//
//    @Test
//    public void setPersons_list_replacesOwnListWithProvidedList() {
//        uniquePersonList.add(ALICE);
//        List<Flashcard> flashcardList = Collections.singletonList(BOB);
//        uniquePersonList.setPersons(flashcardList);
//        UniquePersonList expectedUniquePersonList = new UniquePersonList();
//        expectedUniquePersonList.add(BOB);
//        assertEquals(expectedUniquePersonList, uniquePersonList);
//    }
//
//    @Test
//    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
//        List<Flashcard> listWithDuplicateFlashcards = Arrays.asList(ALICE, ALICE);
//        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.setPersons(listWithDuplicateFlashcards));
//    }
//
//    @Test
//    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
//        assertThrows(UnsupportedOperationException.class, ()
//            -> uniquePersonList.asUnmodifiableObservableList().remove(0));
//    }
}
