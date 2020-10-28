package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class SampleDataTest {

    @Test
    public void nonNullTests() {
        // sample tags
        assertNotNull(SampleDataUtil.getTagSet());

        // sample items
        assertNotNull(SampleDataUtil.getSampleItems());

        // sample itemList
        assertNotNull(SampleDataUtil.getSampleItemList());

        // sample locations
        assertNotNull(SampleDataUtil.getSampleLocations());

        // sample locationList
        assertNotNull(SampleDataUtil.getSampleLocationList());

        // sample recipes
        assertNotNull(SampleDataUtil.getSampleRecipes());

        // sample recipeList
        assertNotNull(SampleDataUtil.getSampleRecipeList());
    }
}
