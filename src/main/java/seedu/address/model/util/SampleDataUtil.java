package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.item.*;
import seedu.address.model.item.Quantity;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Item[] getSampleItems() {
        return new Item[] {
            new Item(new Name("Chicken"), new Quantity("12"),
                new Supplier("NTUC"),
                getTagSet("meat")),
            new Item(new Name("Duck"), new Quantity("33"),
                new Supplier("NTUC"),
                getTagSet("meat"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
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
