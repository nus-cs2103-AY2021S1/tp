package seedu.address.testutil;

import seedu.address.model.recipe.RecipePrecursor;

public class TypicalRecipePrecursors {
    public static final RecipePrecursor APPLE_PIE_PRECURSOR = new RecipePrecursorBuilder().withId(1)
            .withDescription("Apple-y!")
            .withQuantity("1").build();
    public static final RecipePrecursor BANANA_PIE_PRECURSOR = new RecipePrecursorBuilder().withId(2)
            .withDescription("Banana-y!")
            .withQuantity("1").build();
}
