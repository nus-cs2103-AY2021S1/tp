package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ItemList;
import seedu.address.model.LocationList;
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.ReadOnlyLocationList;
import seedu.address.model.ReadOnlyRecipeList;
import seedu.address.model.RecipeList;
import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;
import seedu.address.model.location.Location;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.IngredientList;
import seedu.address.model.recipe.ProductQuantity;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Item[] getSampleItems() {
        return new Item[] {
            new Item(0, "Apple", new Quantity("99"), "Delicious",
                        new HashSet<>(Arrays.asList(0)),
                        new HashSet<>(), getTagSet("Consummable"), false),
            new Item(1, "Banana", new Quantity("99"), "Delicious",
                        new HashSet<>(Arrays.asList(0)),
                        new HashSet<>(), getTagSet("Consummable"), false),
            new Item(2, "Fruit basket", new Quantity("99"), "Delicious",
                        new HashSet<>(Arrays.asList(0)),
                        new HashSet<>(Arrays.asList(0)), getTagSet("Consummable"), false)
        };
    }

    public static ReadOnlyItemList getSampleItemList() {
        ItemList sampleIl = new ItemList();
        for (Item sampleItem : getSampleItems()) {
            sampleIl.addItem(sampleItem);
        }
        return sampleIl;
    }

    public static Location[] getSampleLocations() {
        return new Location[] {
            new Location("Town")
        };
    }

    public static ReadOnlyLocationList getSampleLocationList() {
        LocationList sampleLl = new LocationList();
        for (Location sampleLocation : getSampleLocations()) {
            sampleLl.addLocation(sampleLocation);
        }
        return sampleLl;
    }

    public static Recipe[] getSampleRecipes() {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(new Ingredient(0, 2));
        ingredientList.add(new Ingredient(1, 3));
        IngredientList ingredients = new IngredientList();
        ingredients.setItems(ingredientList);

        return new Recipe[] {
            new Recipe(0, ingredients, 2, "Fruit basket", new ProductQuantity("1"), "Recipe 1", false)
        };
    }

    public static ReadOnlyRecipeList getSampleRecipeList() {
        RecipeList sampleRl = new RecipeList();
        for (Recipe sampleRecipe : getSampleRecipes()) {
            sampleRl.addRecipe(sampleRecipe);
        }
        return sampleRl;
    }
}
