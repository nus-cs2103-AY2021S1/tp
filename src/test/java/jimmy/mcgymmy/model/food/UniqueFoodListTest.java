package jimmy.mcgymmy.model.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import jimmy.mcgymmy.model.person.UniquePersonList;
import jimmy.mcgymmy.model.person.exceptions.DuplicatePersonException;
import jimmy.mcgymmy.model.person.exceptions.PersonNotFoundException;
import jimmy.mcgymmy.testutil.Assert;
import jimmy.mcgymmy.testutil.TypicalFoods;

public class UniqueFoodListTest {

    private final UniquePersonList uniquePersonList = new UniquePersonList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniquePersonList.contains(TypicalFoods.ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniquePersonList.add(TypicalFoods.ALICE);
        assertTrue(uniquePersonList.contains(TypicalFoods.ALICE));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniquePersonList.add(TypicalFoods.ALICE);
        Assert.assertThrows(DuplicatePersonException.class, () -> uniquePersonList.add(TypicalFoods.ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.setPerson(null, TypicalFoods.ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.setPerson(TypicalFoods.ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        Assert.assertThrows(
            PersonNotFoundException.class, () -> uniquePersonList.setPerson(
            TypicalFoods.ALICE, TypicalFoods.ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniquePersonList.add(TypicalFoods.ALICE);
        uniquePersonList.setPerson(TypicalFoods.ALICE, TypicalFoods.ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(TypicalFoods.ALICE);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniquePersonList.add(TypicalFoods.ALICE);
        uniquePersonList.setPerson(TypicalFoods.ALICE, TypicalFoods.BOB);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(TypicalFoods.BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniquePersonList.add(TypicalFoods.ALICE);
        uniquePersonList.add(TypicalFoods.BOB);
        Assert.assertThrows(
            DuplicatePersonException.class, () -> uniquePersonList.setPerson(TypicalFoods.ALICE, TypicalFoods.BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        Assert.assertThrows(PersonNotFoundException.class, () -> uniquePersonList.remove(TypicalFoods.ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniquePersonList.add(TypicalFoods.ALICE);
        uniquePersonList.remove(TypicalFoods.ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((UniquePersonList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniquePersonList.add(TypicalFoods.ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(TypicalFoods.BOB);
        uniquePersonList.setPersons(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((List<jimmy.mcgymmy.model.person.Food>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniquePersonList.add(TypicalFoods.ALICE);
        List<jimmy.mcgymmy.model.person.Food> foodList = Collections.singletonList(TypicalFoods.BOB);
        uniquePersonList.setPersons(foodList);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(TypicalFoods.BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Food> listWithDuplicateFoods = Arrays.asList(TypicalFoods.ALICE, TypicalFoods.ALICE);
        Assert
            .assertThrows(DuplicatePersonException.class, () -> uniquePersonList.setPersons(listWithDuplicateFoods));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, ()
            -> uniquePersonList.asUnmodifiableObservableList().remove(0));
    }
}
