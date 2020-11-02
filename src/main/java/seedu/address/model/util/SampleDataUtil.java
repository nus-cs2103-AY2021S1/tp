package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.food.Food;
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
public class SampleDataUtil {
    public static Vendor[] getSampleVendors() {
        Menu menu1 = new Menu();

        menu1.add(new Food("Butter Chicken", 7, new HashSet<>()));
        menu1.add(new Food("Pattaya", 5.5, new HashSet<>()));
        HashSet<Tag> vegTags = new HashSet<>();
        HashSet<Tag> spicyTags = new HashSet<>();
        spicyTags.add(new Tag("Spicy"));
        vegTags.add(new Tag("Vegetarian"));
        menu1.add(new Food("Veg Briyani", 5, new HashSet<>(vegTags)));
        menu1.add(new Food("Sambal Chicken", 5.5, new HashSet<>(spicyTags)));
        menu1.add(new Food("Cheese Fries", 4, new HashSet<>()));
        menu1.add(new Food("Kampong Style", 4.8, new HashSet<>()));
        //note that this is fried rice
        menu1.add(new Food("Sambal With Fried Sambal Chicken", 4.8, new HashSet<>()));
        menu1.add(new Food("Roti John", 4, new HashSet<>()));

        // Add cold and hot
        menu1.add(new Food("Milo Cold", 1.5, new HashSet<>()));
        menu1.add(new Food("Milo Hot", 1.3, new HashSet<>()));

        menu1.add(new Food("Milo Dinosaur", 2.5, new HashSet<>()));
        menu1.add(new Food("Milo Godzilla", 3, new HashSet<>()));


        Menu menu2 = new Menu();
        HashSet<Tag> tagList = new HashSet<>();
        tagList.add(new Tag("Egg"));
        tagList.add(new Tag("Cheese"));
        menu2.add(new Food("Prata", 1.0, tagList));
        menu2.add(new Food("Water", 3, new HashSet<>()));
        Menu menu3 = new Menu();
        HashSet<Tag> tagList2 = new HashSet<>();
        tagList2.add(new Tag("No ice"));
        menu3.add(new Food("Steak", 16.90, new HashSet<>()));
        menu3.add(new Food("Hamburger", 4.20, new HashSet<>()));
        menu3.add(new Food("Lemon tea", 1.2, tagList2));

        return new Vendor[]{
            new Vendor(new Name("Al Amaan Restaurant"), new Phone("67770555"),
                        new Email("alamaanrestaurant@gmail.com"),
                        new Address("12 Clementi Road, Singapore 129742"),
                        getTagSet("halal"), menu1),
            new Vendor(new Name("Local Indian Store"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("colleagues", "friends"), menu2),
            new Vendor(new Name("Ameens"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        getTagSet("neighbours"), menu3)
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
