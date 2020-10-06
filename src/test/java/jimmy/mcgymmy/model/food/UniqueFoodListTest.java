package jimmy.mcgymmy.model.food;

import static jimmy.mcgymmy.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import jimmy.mcgymmy.model.food.exceptions.DuplicateFoodException;
import jimmy.mcgymmy.model.food.exceptions.FoodNotFoundException;
import jimmy.mcgymmy.testutil.TypicalFoods;

public class UniqueFoodListTest {

    private final UniqueFoodList uniqueFoodList = new UniqueFoodList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueFoodList.contains(TypicalFoods.ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueFoodList.add(TypicalFoods.ALICE);
        assertTrue(uniqueFoodList.contains(TypicalFoods.ALICE));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueFoodList.add(TypicalFoods.ALICE);
        assertThrows(DuplicateFoodException.class, () -> uniqueFoodList.add(TypicalFoods.ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setPerson(null, TypicalFoods.ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setPerson(TypicalFoods.ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(
                FoodNotFoundException.class, () -> uniqueFoodList.setPerson(
                        TypicalFoods.ALICE, TypicalFoods.ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueFoodList.add(TypicalFoods.ALICE);
        uniqueFoodList.setPerson(TypicalFoods.ALICE, TypicalFoods.ALICE);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(TypicalFoods.ALICE);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueFoodList.add(TypicalFoods.ALICE);
        uniqueFoodList.setPerson(TypicalFoods.ALICE, TypicalFoods.BOB);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(TypicalFoods.BOB);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueFoodList.add(TypicalFoods.ALICE);
        uniqueFoodList.add(TypicalFoods.BOB);
        assertThrows(
                DuplicateFoodException.class, () -> uniqueFoodList.setPerson(TypicalFoods.ALICE, TypicalFoods.BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(FoodNotFoundException.class, () -> uniqueFoodList.remove(TypicalFoods.ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueFoodList.add(TypicalFoods.ALICE);
        uniqueFoodList.remove(TypicalFoods.ALICE);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setPersons((UniqueFoodList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueFoodList.add(TypicalFoods.ALICE);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(TypicalFoods.BOB);
        uniqueFoodList.setPersons(expectedUniqueFoodList);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setPersons((List<Food>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueFoodList.add(TypicalFoods.ALICE);
        List<Food> foodList = Collections.singletonList(TypicalFoods.BOB);
        uniqueFoodList.setPersons(foodList);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(TypicalFoods.BOB);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Food> listWithDuplicateFoods = Arrays.asList(TypicalFoods.ALICE, TypicalFoods.ALICE);
        assertThrows(DuplicateFoodException.class, () -> uniqueFoodList.setPersons(listWithDuplicateFoods));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
                UnsupportedOperationException.class, () -> uniqueFoodList
                        .asUnmodifiableObservableList().remove(0));
    }
}
