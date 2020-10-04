package chopchop.model;

import static chopchop.logic.commands.CommandTestUtil.VALID_INGREDIENT_EXPIRY_BANANA;
import static chopchop.logic.commands.CommandTestUtil.VALID_INGREDIENT_QTY_BANANA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static chopchop.testutil.Assert.assertThrows;
import static chopchop.testutil.TypicalIngredients.APRICOT;
import static chopchop.testutil.TypicalIngredients.getTypicalIngredientBook;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.ingredient.IngredientBook;
import chopchop.model.ingredient.ReadOnlyIngredientBook;
import chopchop.model.ingredient.exceptions.DuplicateIngredientException;
import chopchop.testutil.IngredientBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class IngredientBookTest {

    private final IngredientBook ingredientBook = new IngredientBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), ingredientBook.getFoodEntryList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ingredientBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyIngredientBook_replacesData() {
        IngredientBook newData = getTypicalIngredientBook();
        ingredientBook.resetData(newData);
        assertEquals(newData, ingredientBook);
    }

    @Test
    public void resetData_withDuplicateIngredients_throwsDuplicateIngredientException() {
        // Two persons with the same identity fields
        Ingredient editedAlice = new IngredientBuilder(APRICOT).withQuantity(VALID_INGREDIENT_QTY_BANANA)
            .build();
        List<Ingredient> newIngredients = Arrays.asList(APRICOT, editedAlice);
        IngredientBookTest.IngredientBookStub newData = new IngredientBookTest.IngredientBookStub(newIngredients);

        assertThrows(DuplicateIngredientException.class, () -> ingredientBook.resetData(newData));
    }

    @Test
    public void hasIngredient_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ingredientBook.hasIngredient(null));
    }

    @Test
    public void hasIngredient_personNotInIngredientBook_returnsFalse() {
        assertFalse(ingredientBook.hasIngredient(APRICOT));
    }

    @Test
    public void hasIngredient_personInIngredientBook_returnsTrue() {
        ingredientBook.addIngredient(APRICOT);
        assertTrue(ingredientBook.hasIngredient(APRICOT));
    }

    @Test
    public void hasIngredient_personWithSameIdentityFieldsInIngredientBook_returnsTrue() {
        ingredientBook.addIngredient(APRICOT);
        Ingredient editedAlice = new IngredientBuilder(APRICOT).withQuantity(VALID_INGREDIENT_QTY_BANANA)
            .withDate(VALID_INGREDIENT_EXPIRY_BANANA)
            .build();
        assertFalse(ingredientBook.hasIngredient(editedAlice)); //Both identity fields must be equal
    }

    @Test
    public void getIngredientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> ingredientBook.getFoodEntryList().remove(0));
    }

    /**
     * A stub ReadOnlyIngredientBook whose persons list can violate interface constraints.
     */
    private static class IngredientBookStub implements ReadOnlyIngredientBook {
        private final ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();

        IngredientBookStub(Collection<Ingredient> ingredients) {
            this.ingredients.setAll(ingredients);
        }

        @Override
        public ObservableList<Ingredient> getFoodEntryList() {
            return ingredients;
        }
    }

}
