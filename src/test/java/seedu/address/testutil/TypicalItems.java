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

import seedu.address.model.InventoryBook;
import seedu.address.model.item.Item;

/**
 * A utility class containing a list of {@code Item} objects to be used in tests.
 */
public class TypicalItems {

    public static final Item CHICKEN = new ItemBuilder().withName("Chicken")
            .withSupplier("Giant")
            .withQuantity("12")
            .withTags("meat").build();
    public static final Item DUCK = new ItemBuilder().withName("Duck")
            .withSupplier("Cold Storage")
            .withQuantity("33")
            .withTags("meat").build();
    public static final Item SALMON = new ItemBuilder().withName("Salmon")
            .withSupplier("No Supplier")
            .withQuantity("5")
            .withTags("fish").build();
    public static final Item BROCCOLI = new ItemBuilder().withName("Broccoli")
            .withSupplier("Sheng Siong")
            .withQuantity("3")
            .withTags("vegetable").build();
    public static final Item CRAB = new ItemBuilder().withName("Crab")
            .withSupplier("NTUC")
            .withQuantity("7")
            .withTags("seafood").build();
    public static final Item BEEF = new ItemBuilder().withName("Beef")
            .withSupplier("Cold Storage")
            .withQuantity("41")
            .withTags("meat").build();
    public static final Item PORK = new ItemBuilder().withName("Pork")
            .withSupplier("Sheng Siong")
            .withQuantity("2")
            .withTags("meat").build();

    // Manually added
    public static final Item TUNA = new ItemBuilder().withName("Tuna").withQuantity("10")
            .withSupplier("No Supplier").build();
    public static final Item LAMB = new ItemBuilder().withName("LAMB").withQuantity("4")
            .withSupplier("Sheng Siong").build();

    // Manually added - Item's details found in {@code CommandTestUtil}
    public static final Item CHICKEN_MANUAL = new ItemBuilder().withName(VALID_NAME_CHICKEN)
            .withQuantity(VALID_QUANTITY_CHICKEN)
           .withSupplier(VALID_SUPPLIER_CHICKEN).withTags(VALID_TAG_POULTRY).build();
    public static final Item DUCK_MANUAL = new ItemBuilder().withName(VALID_NAME_DUCK).withQuantity(VALID_QUANTITY_DUCK)
            .withSupplier(VALID_SUPPLIER_DUCK).withTags(VALID_TAG_MEAT, VALID_TAG_POULTRY)
            .build();


    private TypicalItems() {} // prevents instantiation

    /**
     * Returns an {@code InventoryBook} with all the typical items.
     */
    public static InventoryBook getTypicalInventoryBook() {
        InventoryBook ab = new InventoryBook();
        for (Item item : getTypicalItems()) {
            ab.addItem(item);
        }
        return ab;
    }

    public static List<Item> getTypicalItems() {
        return new ArrayList<>(Arrays.asList(CHICKEN, DUCK, SALMON, BROCCOLI, CRAB, BEEF, PORK));
    }
}
