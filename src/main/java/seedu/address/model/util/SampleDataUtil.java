package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.food.MenuItem;
import seedu.address.model.menu.Menu;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Email;
import seedu.address.model.vendor.Name;
import seedu.address.model.vendor.Phone;
import seedu.address.model.vendor.ReadOnlyVendorManager;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorManager;



/**
 * Contains utility methods for populating {@code VendorManager} with sample data.
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
        menu1.add(new MenuItem("Butter Chicken", 7, new HashSet<>(), "al_amaan/butter_chicken.png"));
        menu1.add(new MenuItem("Pattaya", 5.5, new HashSet<>(), "al_amaan/pattaya.png"));

        HashSet<Tag> vegTags = new HashSet<>();
        HashSet<Tag> spicyTags = new HashSet<>();
        spicyTags.add(new Tag("Spicy"));
        vegTags.add(new Tag("Vegetarian"));

        menu1.add(new MenuItem("Veg Briyani", 5, new HashSet<>(vegTags),
                "al_amaan/veg_briyani.png"));
        menu1.add(new MenuItem("Cheese Fries", 4, new HashSet<>(),
                "al_amaan/cheese_fries.png"));
        menu1.add(new MenuItem("Kampong Style Fried Rice", 4.8, new HashSet<>(),
                "al_amaan/kampong.png"));
        //note that this is fried rice
        menu1.add(new MenuItem("Sambal Chicken Fried Rice", 4.8, new HashSet<>(),
                "al_amaan/sambal_chicken.png"));
        menu1.add(new MenuItem("Roti John", 4, new HashSet<>(), "al_amaan/roti_john.png"));

        // Add cold and hot
        menu1.add(new MenuItem("Milo Cold", 1.5, drinks, "al_amaan/milo.png"));
        menu1.add(new MenuItem("Milo Hot", 1.3, drinks, "invalid.png"));

        menu1.add(new MenuItem("Milo Dinosaur", 2.5, drinks, "al_amaan/milo_dino.png"));
        menu1.add(new MenuItem("Milo Godzilla", 3, drinks,
                "al_amaan/milo_god.png"));


        Menu menu2 = new Menu();
        HashSet<Tag> tagList = new HashSet<>();
        tagList.add(new Tag("Egg"));
        tagList.add(new Tag("Cheese"));
        menu2.add(new MenuItem("Pattaya", 6.5, new HashSet<>(), "makan_xpress/pattaya.png"));
        menu2.add(new MenuItem("Roti John Chicken", 5.5, new HashSet<>(),
                "al_amaan/roti_john.png"));
        menu2.add(new MenuItem("Meatball Spaghetti", 7.7, new HashSet<>(),
                "makan_xpress/meatball_spagetti.png"));
        menu2.add(new MenuItem("Naan", 1.7, new HashSet<>(), "makan_xpress/naan.png"));
        menu2.add(new MenuItem("Tandoori", 6.5, new HashSet<>(), "makan_xpress/tandoori.png"));
        menu2.add(new MenuItem("Plain Prata", 1.2, new HashSet<>(), "makan_xpress/prata.png"));
        menu2.add(new MenuItem("Tom Yam", 6.5, new HashSet<>(), "makan_xpress/tomyam.png"));
        menu2.add(new MenuItem("Nasi Goreng Ikan Bilis", 4.5, new HashSet<>(),
                "makan_xpress/nasi_goreng.png"));
        menu2.add(new MenuItem("Milo Cold", 2, drinks, "al_amaan/milo.png"));
        menu2.add(new MenuItem("Teh Ping Cold", 2, drinks, "al_amaan/teh_ping.png"));


        Menu menu3 = new Menu();
        menu3.add(new MenuItem("Bacon & Cheese Hotdog", 13.90, hotdogs,
                "de_frank/bacon_cheese_hotdog.png"));
        menu3.add(new MenuItem("Mozzarella Corn Dog (2pcs)", 12.90, hotdogs,
                "de_frank/corn_dog.png"));
        menu3.add(new MenuItem("Sauerkraut Hotdog", 11.90, hotdogs,
                "de_frank/sauerkraut_hotdog.png"));
        menu3.add(new MenuItem("Signature Ribeye Burger", 17.90, burgers,
                "de_frank/ribeye_burger.png"));
        menu3.add(new MenuItem("Chicken Cutlet Burger", 13.90, burgers,
                "de_frank/chicken_burger.png"));
        menu3.add(new MenuItem("Breaded Fish Burger", 13.90, burgers,
                "de_frank/fish_burger.png"));
        menu3.add(new MenuItem("Foie Gras Sliced Beef Roll", 18.90, hotdogs,
                "invalid.png"));
        menu3.add(new MenuItem("Cheese Steak Sliders (3pcs)", 16.90, burgers,
                "de_frank/cheese_steak_slider.png"));

        Menu menu4 = new Menu();
        menu4.add(new MenuItem("Ayam Taliwang Dada/Taliwang Chicken Breast", 9.20, spicyTags,
                "ayam_tali_wang/ayam_taliwang_dada.png"));
        menu4.add(new MenuItem("Ayam Taliwang Peha/ Taliwang Chicken Thigh", 9.80, spicyTags,
                "ayam_tali_wang/ayam_taliwang_peha.png"));
        menu4.add(new MenuItem("Taliwang Dory/ Taliwang Pan Fried Fillet Dory", 9.50, spicyTags,
                "ayam_tali_wang/taliwang_dory.png"));
        menu4.add(new MenuItem("Tahu & Tempeh Goreng/ Fried Tofu & Tempeh", 4.70, starters,
                "ayam_tali_wang/tahu_tempeh.png"));
        menu4.add(new MenuItem("Keropok", 1.50, starters,
                "ayam_tali_wang/keropok.png"));
        menu4.add(new MenuItem("Jus Jeruk Nipis / Lime Juice", 2.50, drinks,
                "ayam_tali_wang/lime_juice.png"));
        menu4.add(new MenuItem("Teh Botol Sosro / Indonesian Bottled Tea Original", 2.50, drinks,
                "ayam_tali_wang/teh_botol.png"));
        menu4.add(new MenuItem("Minuman Kaleng / Canned Drink Coke", 2.30, drinks,
                "ayam_tali_wang/coke.png"));

        return new Vendor[]{
            new Vendor(new Name("Al Amaan Restaurant"), new Phone("67740637"),
                        new Email("alamaanrestaurant@gmail.com"),
                        new Address("12 Clementi Road, Singapore 129742"),
                        getTagSet("halal"), menu1),
            new Vendor(new Name("Xpress Makan Avenue"), new Phone("91076367"), new Email("xpressmakanavenue@gmail.com"),
                        new Address("14 Clementi Road, Singapore 129743"),
                        getTagSet("halal"), menu2),
            new Vendor(new Name("BeFrank"), new Phone("97652509"), new Email("beFrank99@gmail.com"),
                        new Address("28 Clementi Road, Singapore 129754"),
                        getTagSet("western"), menu3),
            new Vendor(new Name("Ayam Taliwang"), new Phone("69048773"),
                    new Email("taliwangIndonesianrestaurant@gmail.com"),
                    new Address("440 Pasir Panjang Road 118782 Singapore"),
                    getTagSet("indonesian"), menu4)
        };
    }

    public static ReadOnlyVendorManager getSampleVendorManager() {
        VendorManager sampleAb = new VendorManager();
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
