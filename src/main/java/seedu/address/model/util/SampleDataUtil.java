package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.delivery.Address;
import seedu.address.model.delivery.Delivery;
import seedu.address.model.delivery.DeliveryName;
import seedu.address.model.delivery.Order;
import seedu.address.model.delivery.Phone;
import seedu.address.model.delivery.Time;
import seedu.address.model.deliverymodel.DeliveryBook;
import seedu.address.model.deliverymodel.ReadOnlyDeliveryBook;
import seedu.address.model.inventorymodel.InventoryBook;
import seedu.address.model.inventorymodel.ReadOnlyInventoryBook;
import seedu.address.model.item.Item;
import seedu.address.model.item.Metric;
import seedu.address.model.item.Name;
import seedu.address.model.item.Quantity;
import seedu.address.model.item.Supplier;
import seedu.address.model.item.Tag;

/**
 * Contains utility methods for populating {@code InventoryBook} with sample data.
 */
public class SampleDataUtil {
    public static Item[] getSampleItems() {
        return new Item[] {
                new Item(new Name("Milk"),
                        new Quantity("5"),
                        new Supplier("SHENG SHIONG"),
                        getTagSet("Beverage"),
                        new Quantity("30"),
                        new Metric("L")),
                new Item(new Name("Tuna"),
                        new Quantity("24"),
                        new Supplier("NTUC"),
                        getTagSet("meat"),
                        new Quantity("50"),
                        new Metric("KG")),
                new Item(new Name("Broccoli"),
                        new Quantity("50"),
                        new Supplier("NTUC"),
                        getTagSet("Vegetable"),
                        new Quantity("80"),
                        new Metric("KG")),
                new Item(new Name("Chicken"),
                        new Quantity("72"),
                        new Supplier("NTUC"),
                        getTagSet("meat"),
                        new Quantity("50"),
                        new Metric("KG"))
        };
    }
//    public static Delivery[] getSampleDeliveries() {
//        return new Delivery[]{
//            new Delivery(new DeliveryName("Damith"),
//                new Phone("91231231"),
//                new Address("Blk 999 Bukit Batam Jln Pondok Indah No 55"),
//                new Order("1x Laksa, 1x Ice Kopi"),
//                new Time("1", "28 October 2020 00:00:00")),
//            new Delivery(new DeliveryName("Aileen"),
//                new Phone("81111111"),
//                new Address("The View Orchard Apartment No 12"),
//                new Order("5x Mee Goreng, 5x Prata plain"),
//                new Time("41", "28 October 2020 00:00:00"))
//        };
//    }

    public static Delivery[] getSampleDeliveries() {
        return new Delivery[]{
                new Delivery(new DeliveryName("Alex Yeoh"),
                        new Phone("87438807"),
                        new Address("Blk 30 Geylang Street 29, #06-40"),
                        new Order("2x Chicken Rice, 1x Ice Milo"),
                        new Time("15", "28 October 2020 00:00:00")),
                new Delivery(new DeliveryName("Bernice Yu"),
                        new Phone("99272758"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        new Order("2x Duck Rice, 1x Ayutaka Green Tea"),
                        new Time("45", "28 October 2020 00:00:00"))};
    }

    public static ReadOnlyDeliveryBook getSampleDeliveryBook() {
        DeliveryBook sampleDb = new DeliveryBook();
        for (Delivery sampleDelivery : getSampleDeliveries()) {
            sampleDb.addDelivery(sampleDelivery);
        }
        return sampleDb;
    }

    public static ReadOnlyInventoryBook getSampleInventoryBook() {
        InventoryBook sampleAb = new InventoryBook();
        for (Item sampleItem : getSampleItems()) {
            sampleAb.addItem(sampleItem);
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
