package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ItemList;
import seedu.address.model.LocationList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.ReadOnlyLocationList;
import seedu.address.model.ReadOnlyRecipeList;
import seedu.address.model.RecipeList;
import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;
import seedu.address.model.location.Location;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.IngredientList;
import seedu.address.model.recipe.ProductQuantity;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

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
