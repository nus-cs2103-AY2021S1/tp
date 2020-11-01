package seedu.address.model.ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIngredients.MANGO;
import static seedu.address.testutil.TypicalIngredients.POTATO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.ingredient.exceptions.DuplicateIngredientException;
import seedu.address.model.ingredient.exceptions.IngredientNotFoundException;
import seedu.address.testutil.IngredientBuilder;

public class UniqueIngredientListTest {

    private final UniqueIngredientList uniqueIngredientList = new UniqueIngredientList();

    @Test
    public void contains_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.contains(null));
    }

    @Test
    public void contains_ingredientNotInList_returnsFalse() {
        assertFalse(uniqueIngredientList.contains(POTATO));
    }

    @Test
    public void contains_ingredientInList_returnsTrue() {
        uniqueIngredientList.add(POTATO);
        assertTrue(uniqueIngredientList.contains(POTATO));
    }

    @Test
    public void contains_ingredientWithSameIdentityFieldsInList_returnsTrue() {
        uniqueIngredientList.add(POTATO);
        Ingredient editedAlice = new IngredientBuilder(POTATO)
                .build();
        assertTrue(uniqueIngredientList.contains(editedAlice));
    }

    @Test
    public void add_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.add(null));
    }

    @Test
    public void add_duplicateIngredient_throwsDuplicateIngredientException() {
        uniqueIngredientList.add(POTATO);
        assertThrows(DuplicateIngredientException.class, () -> uniqueIngredientList.add(POTATO));
    }

    @Test
    public void setIngredient_nullTargetIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.setIngredient(null, POTATO));
    }

    @Test
    public void setIngredient_nullEditedIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.setIngredient(POTATO, null));
    }

    @Test
    public void setIngredient_targetIngredientNotInList_throwsIngredientNotFoundException() {
        assertThrows(IngredientNotFoundException.class, () -> uniqueIngredientList.setIngredient(POTATO, POTATO));
    }

    @Test
    public void setIngredient_editedIngredientIsSameIngredient_success() {
        uniqueIngredientList.add(POTATO);
        uniqueIngredientList.setIngredient(POTATO, POTATO);
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        expectedUniqueIngredientList.add(POTATO);
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredient_editedIngredientHasSameIdentity_success() {
        uniqueIngredientList.add(POTATO);
        Ingredient editedAlice = new IngredientBuilder(POTATO)
                .build();
        uniqueIngredientList.setIngredient(POTATO, editedAlice);
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        expectedUniqueIngredientList.add(editedAlice);
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredient_editedIngredientHasDifferentIdentity_success() {
        uniqueIngredientList.add(POTATO);
        uniqueIngredientList.setIngredient(POTATO, MANGO);
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        expectedUniqueIngredientList.add(MANGO);
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredient_editedIngredientHasNonUniqueIdentity_throwsDuplicateIngredientException() {
        uniqueIngredientList.add(POTATO);
        uniqueIngredientList.add(MANGO);
        assertThrows(DuplicateIngredientException.class, () -> uniqueIngredientList.setIngredient(POTATO, MANGO));
    }

    @Test
    public void remove_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.remove(null));
    }

    @Test
    public void remove_ingredientDoesNotExist_throwsIngredientNotFoundException() {
        assertThrows(IngredientNotFoundException.class, () -> uniqueIngredientList.remove(POTATO));
    }

    @Test
    public void remove_existingIngredient_removesIngredient() {
        uniqueIngredientList.add(POTATO);
        uniqueIngredientList.remove(POTATO);
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredients_nullUniqueIngredientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueIngredientList.setIngredients((UniqueIngredientList) null));
    }

    @Test
    public void setIngredients_uniqueIngredientList_replacesOwnListWithProvidedUniqueIngredientList() {
        uniqueIngredientList.add(POTATO);
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        expectedUniqueIngredientList.add(MANGO);
        uniqueIngredientList.setIngredients(expectedUniqueIngredientList);
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredients_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.setIngredients((List<Ingredient>) null));
    }

    @Test
    public void setIngredients_list_replacesOwnListWithProvidedList() {
        uniqueIngredientList.add(POTATO);
        List<Ingredient> ingredientList = Collections.singletonList(MANGO);
        uniqueIngredientList.setIngredients(ingredientList);
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        expectedUniqueIngredientList.add(MANGO);
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredients_listWithDuplicateIngredients_throwsDuplicateIngredientException() {
        List<Ingredient> listWithDuplicateIngredients = Arrays.asList(POTATO, POTATO);
        assertThrows(DuplicateIngredientException.class, () ->
                uniqueIngredientList.setIngredients(listWithDuplicateIngredients));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueIngredientList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void clear_allIngredient_clearIngredient() {
        uniqueIngredientList.add(POTATO);
        uniqueIngredientList.add(MANGO);
        uniqueIngredientList.clear();
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);
    }
}
