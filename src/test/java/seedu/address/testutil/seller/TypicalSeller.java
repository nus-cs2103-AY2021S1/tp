package seedu.address.testutil.seller;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.person.seller.Seller;
import seedu.address.model.selleraddressbook.SellerAddressBook;

/**
 * A utility class containing a list of {@code Seller} objects to be used in tests.
 */
public class TypicalSeller {

    public static final Seller ALICE = new SellerBuilder().withName("Alice Pauline")
            .withPhone("1111").withId(1).build();
    public static final Seller SELLER_ALICE = new SellerBuilder().withName("Alice Pauline")
            .withPhone("2222").withId(1).build();
    public static final Seller SELLER_BENSON = new SellerBuilder().withName("Benson Meier")
            .withPhone("3333").withId(2).build();
    public static final Seller CARL = new SellerBuilder().withName("Carl Kurz")
            .withPhone("4444").withId(3).build();
    public static final Seller DANIEL = new SellerBuilder().withName("Daniel Meier")
            .withPhone("5555").withId(4).build();
    public static final Seller ELLE = new SellerBuilder().withName("Elle Meyer")
            .withPhone("6666").withId(5).build();
    public static final Seller FIONA = new SellerBuilder().withName("Fiona Kunz")
            .withPhone("7777").withId(6).build();
    public static final Seller GEORGE = new SellerBuilder().withName("George Best")
            .withPhone("8888").withId(7).build();

    // Manually added - Seller's details found in {@code CommandTestUtil}
    public static final Seller AMY = new SellerBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withId(12).build();
    public static final Seller BOB = new SellerBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withId(13).build();

    // Manually added
    public static final Seller HOON = new SellerBuilder().withName("Hoon Meier").withPhone("8482424").build();
    public static final Seller IDA = new SellerBuilder().withName("Ida Mueller").withPhone("8482131").build();

    /**
     * Returns an {@code SellerAddressBook} with all the typical persons.
     */
    public static SellerAddressBook getTypicalSellerAddressBook() {
        SellerAddressBook ab = new SellerAddressBook();
        for (Seller bidder : getTypicalSellers()) {
            ab.addSeller(bidder);
        }
        return ab;
    }

    public static List<Seller> getTypicalSellers() {
        return new ArrayList<>(Arrays.asList(ALICE, SELLER_BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
