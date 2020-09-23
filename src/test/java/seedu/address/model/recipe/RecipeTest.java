package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
//import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecipes.ALICE;
import static seedu.address.testutil.TypicalRecipes.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RecipeBuilder;

public class RecipeTest {

    @Test
    public void isSameRecipe() {
        // same object -> returns true
        assertTrue(ALICE.isSameRecipe(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameRecipe(null));

        // different ingredients and email -> returns false
        Recipe editedAlice = new RecipeBuilder(ALICE).withIngredient(VALID_INGREDIENT_BOB).build();
        assertFalse(ALICE.isSameRecipe(editedAlice));

        // different name -> returns false
        editedAlice = new RecipeBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameRecipe(editedAlice));

        // same name, same ingredients, different attributes -> returns true
        editedAlice = new RecipeBuilder(ALICE)
               .build();
        // assertTrue(ALICE.isSameRecipe(editedAlice));

        // same name, same email, different attributes -> returns true
        editedAlice = new RecipeBuilder(ALICE).withIngredient(VALID_INGREDIENT_BOB).build();
        // assertTrue(ALICE.isSameRecipe(editedAlice));

        // same name, same ingredients, same email, different attributes -> returns true
        editedAlice = new RecipeBuilder(ALICE).build();
        // assertTrue(ALICE.isSameRecipe(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Recipe aliceCopy = new RecipeBuilder(ALICE).build();
        // assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different recipe -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Recipe editedAlice = new RecipeBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different ingredients -> returns false
        editedAlice = new RecipeBuilder(ALICE).withIngredient(VALID_INGREDIENT_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new RecipeBuilder(ALICE).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new RecipeBuilder(ALICE).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new RecipeBuilder(ALICE).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
