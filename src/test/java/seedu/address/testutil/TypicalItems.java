package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CHICKEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DUCK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_CHICKEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_DUCK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUPPLIER_CHICKEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUPPLIER_DUCK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MEAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_POULTRY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.item.Item;

/**
 * A utility class containing a list of {@code Item} objects to be used in tests.
 */
public class TypicalItems {

    public static final Item CHICKEN = new ItemBuilder().withName("Chicken")
            .withSupplier("NTUC")
            .withQuantity("12")
            .withTags("meat").build();
    public static final Item DUCK = new ItemBuilder().withName("Duck")
            .withSupplier("NTUC")
            .withQuantity("33")
            .withTags("meat").build();

    // Manually added
    public static final Item HOON = new ItemBuilder().withName("Hoon Meier").withQuantity("8482424")
            .withSupplier("little india").build();
    public static final Item IDA = new ItemBuilder().withName("Ida Mueller").withQuantity("8482131")
            .withSupplier("chicago ave").build();

    // Manually added - Item's details found in {@code CommandTestUtil}
    public static final Item CHICKEN_MANUAL = new ItemBuilder().withName(VALID_NAME_CHICKEN)
            .withQuantity(VALID_QUANTITY_CHICKEN)
           .withSupplier(VALID_SUPPLIER_CHICKEN).withTags(VALID_TAG_POULTRY).build();
    public static final Item DUCK_MANUAL = new ItemBuilder().withName(VALID_NAME_DUCK).withQuantity(VALID_QUANTITY_DUCK)
            .withSupplier(VALID_SUPPLIER_DUCK).withTags(VALID_TAG_MEAT, VALID_TAG_POULTRY)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalItems() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical items.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Item item : getTypicalItems()) {
            ab.addItem(item);
        }
        return ab;
    }

    public static List<Item> getTypicalItems() {
        return new ArrayList<>(Arrays.asList(CHICKEN, DUCK));
    }
}
