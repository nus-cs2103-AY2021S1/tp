package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.delivery.Delivery;
import seedu.address.model.deliverymodel.DeliveryBook;

/**
 * A utility class containing a list of {@code Delivery} objects to be used in tests.
 */
public class TypicalDeliveries {

    public static final Delivery KELVIN = new DeliveryBuilder().withName("Kelvin")
            .withPhone("91234332")
            .withAddress("Clementi Blk 235 #11-111")
            .withOrder("Char Kway Teow")
            .build();
    public static final Delivery MARCUS = new DeliveryBuilder().withName("Marcus")
            .withPhone("8198264")
            .withAddress("Jurong Blk 231 #15-123")
            .withOrder("Seafood Hor Fun x5")
            .build();

    private TypicalDeliveries() {} // prevents instantiation

    /**
     * Returns an {@code InventoryBook} with all the typical items.
     */
    public static DeliveryBook getTypicalDeliveryBook() {
        DeliveryBook deliveryBook = new DeliveryBook();
        for (Delivery delivery : getTypicalDeliveries()) {
            deliveryBook.addDelivery(delivery);
        }
        return deliveryBook;
    }

    public static List<Delivery> getTypicalDeliveries() {
        return new ArrayList<>(Arrays.asList(KELVIN, MARCUS));
    }
}

