package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECIPE_IMAGE_SANDWICH;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalRecipes;

public class RecipeImageTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RecipeImage(null));
    }

    @Test
    public void constructor_invalidImage_throwsIllegalArgumentException() {
        String invalidImage = "";
        assertThrows(IllegalArgumentException.class, () -> new RecipeImage(invalidImage));
    }

    @Test
    public void isValidImage() {
        // null image
        assertThrows(NullPointerException.class, () -> Instruction.isValidInstruction(null));

        // invalid image
        assertFalse(RecipeImage.isValidImage("")); // empty string
        assertFalse(RecipeImage.isValidImage(" ")); // spaces only

        // valid image
        assertTrue(RecipeImage.isValidImage("images/healthy.jpg"));
        assertTrue(RecipeImage.isValidImage("images/healthy1.jpg")); // alphanumeric characters
        assertTrue(RecipeImage.isValidImage("images/heAlThy.jpg")); // with capital letters
        assertTrue(RecipeImage.isValidImage(
                "file:///C:/Users/Hi/data/effect-of-coronavirus-on-food.jpg")); //local file path
        assertTrue(RecipeImage.isValidImage(
                "https://cdn.cdnparenting.com/articles/2017/11/502828327-H-300x205.jpg")); //url
    }

    @Test
    public void getImagePath() {
        String imagePath = VALID_RECIPE_IMAGE_SANDWICH;
        assertTrue(TypicalRecipes.SANDWICH.getRecipeImage().getImagePath().equals(imagePath));
    }
}
