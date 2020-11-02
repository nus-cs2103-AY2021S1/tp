package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.food.MenuItem;
import seedu.address.model.menu.Menu;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Email;
import seedu.address.model.vendor.Name;
import seedu.address.model.vendor.Phone;
import seedu.address.model.vendor.Vendor;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
//todo: Add the image urls under the file path parameter here
public class SampleDataUtil {
    public static Vendor[] getSampleVendors() {
        HashSet<Tag> starters = new HashSet<>();
        starters.add(new Tag("Starter"));

        HashSet<Tag> drinks = new HashSet<>();
        drinks.add(new Tag("Drink"));

        HashSet<Tag> hotdogs = new HashSet<>();
        hotdogs.add(new Tag("HotDog"));

        HashSet<Tag> burgers = new HashSet<>();
        burgers.add(new Tag("Burger"));

        Menu menu1 = new Menu();
        menu1.add(new MenuItem("Butter Chicken", 7, new HashSet<>(),
                "resources/images/address_book_32.png"));
        menu1.add(new MenuItem("Pattaya", 5.5, new HashSet<>(), ""));
        HashSet<Tag> vegTags = new HashSet<>();
        HashSet<Tag> spicyTags = new HashSet<>();
        spicyTags.add(new Tag("Spicy"));
        vegTags.add(new Tag("Vegetarian"));
        menu1.add(new MenuItem("Veg Briyani", 5, new HashSet<>(vegTags), ""));
        menu1.add(new MenuItem("Sambal Chicken", 5.5, new HashSet<>(spicyTags), ""));
        menu1.add(new MenuItem("Cheese Fries", 4, new HashSet<>(), ""));
        menu1.add(new MenuItem("Kampong Style", 4.8, new HashSet<>(), ""));
        //note that this is fried rice
        menu1.add(new MenuItem("Sambal With Fried Sambal Chicken", 4.8, new HashSet<>(), ""));
        menu1.add(new MenuItem("Roti John", 4, new HashSet<>(), ""));

        // Add cold and hot
        menu1.add(new MenuItem("Milo Cold", 1.5, drinks, ""));
        menu1.add(new MenuItem("Milo Hot", 1.3, drinks, ""));

        menu1.add(new MenuItem("Milo Dinosaur", 2.5, drinks, ""));
        menu1.add(new MenuItem("Milo Godzilla", 3, drinks, ""));


        Menu menu2 = new Menu();
        HashSet<Tag> tagList = new HashSet<>();
        tagList.add(new Tag("Egg"));
        tagList.add(new Tag("Cheese"));
        menu2.add(new MenuItem("Prata", 1.0, tagList, ""));
        menu2.add(new MenuItem("Water", 3, drinks, ""));

        Menu menu3 = new Menu();
        menu3.add(new MenuItem("Cream of Mushroom Soup", 6.90, starters, ""));
        menu3.add(new MenuItem("Chicken Caesar Salad", 8.90, starters, ""));
        menu3.add(new MenuItem("Salmon Bonito Salad", 9.90, starters, ""));
        menu3.add(new MenuItem("Foie Gras Sliced Beef Roll", 18.90, hotdogs, ""));
        menu3.add(new MenuItem("Signatire Cheesy Spicy Coney Hotdog(Beef)", 13.90, hotdogs, ""));
        menu3.add(new MenuItem("Bacon & Cheese Hotdog", 13.90, hotdogs, ""));
        menu3.add(new MenuItem("Mozzarella Corn Dog (2pcs)", 12.90, hotdogs, ""));
        menu3.add(new MenuItem("Sauerkraut Hotdog", 11.90, hotdogs, ""));
        menu3.add(new MenuItem("Plain Hotdog", 9.90, hotdogs, ""));
        menu3.add(new MenuItem("Signature Ribeye Burger", 17.90, burgers, ""));
        menu3.add(new MenuItem("Chicken Cutlet Burger", 13.90, burgers, ""));
        menu3.add(new MenuItem("Breaded Fish Burger", 13.90, burgers, ""));
        menu3.add(new MenuItem("Cheese Steak Sliders (3pcs)", 16.90, burgers, ""));

        return new Vendor[]{
            new Vendor(new Name("Al Amaan Restaurant"), new Phone("67740637"),
                        new Email("alamaanrestaurant@gmail.com"),
                        new Address("12 Clementi Road, Singapore 129742"),
                        getTagSet("halal"), menu1),
            new Vendor(new Name("Local Indian Store"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("colleagues", "friends"), menu2),
            new Vendor(new Name("BeFrank"), new Phone("97652509"), new Email("charlotte@example.com"),
                        new Address("28 Clementi Road, Singapore 129754"),
                        getTagSet("western"), menu3)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Vendor sampleVendor : getSampleVendors()) {
            sampleAb.addVendor(sampleVendor);
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

}
