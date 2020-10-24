package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalIngredients.MANGO;
import static seedu.address.testutil.TypicalIngredients.POTATO;

import org.junit.jupiter.api.Test;

public class UniqueIngredientListTest {

    private final UniqueIngredientList uniqueIngredientList = new UniqueIngredientList();

    @Test
    public void clear_allIngredient_clearIngredient() {
        uniqueIngredientList.add(POTATO);
        uniqueIngredientList.add(MANGO);
        uniqueIngredientList.clear();
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);
    }
}
