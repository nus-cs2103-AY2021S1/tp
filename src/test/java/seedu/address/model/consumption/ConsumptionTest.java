package seedu.address.model.consumption;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.TypicalRecipes.ALICE;
import static seedu.address.testutil.TypicalRecipes.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RecipeBuilder;

public class ConsumptionTest {

    private final Consumption alice = new Consumption(ALICE);

    @Test
    public void equals() {
        // same values -> returns true
        Consumption aliceCopy = new Consumption(new RecipeBuilder(ALICE).build());
        // assertTrue(alice.equals(aliceCopy));

        // same object -> returns true
        assertTrue(alice.equals(new Consumption(ALICE)));

        // null -> returns false
        assertFalse(alice.equals(null));

        // different type -> returns false
        assertFalse(alice.equals(5));

        // different recipe -> returns false
        assertFalse(alice.equals(BOB));

        // different name -> returns false
        Consumption editedAlice = new Consumption(new RecipeBuilder(ALICE).withName(VALID_NAME_BOB).build());
        assertFalse(alice.equals(editedAlice));

        // different ingredients -> returns false
        editedAlice = new Consumption(new RecipeBuilder(ALICE).withIngredient(VALID_INGREDIENT_BOB).build());
        assertFalse(alice.equals(editedAlice));

        // different email -> returns false
        editedAlice = new Consumption(new RecipeBuilder(ALICE).build());
        assertFalse(alice.equals(editedAlice));

        // different address -> returns false
        editedAlice = new Consumption(new RecipeBuilder(ALICE).build());
        assertFalse(alice.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new Consumption(new RecipeBuilder(ALICE).build());
        assertFalse(alice.equals(editedAlice));
    }
}
