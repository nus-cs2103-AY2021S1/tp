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
            .withPhone("94351253").withId(1).build();
    public static final Seller BENSON = new SellerBuilder().withName("Benson Meier").withId(2).build();
    public static final Seller CARL = new SellerBuilder().withName("Carl Kurz")
            .withPhone("95352563").withPhone("9482442").withId(3).build();
    public static final Seller DANIEL = new SellerBuilder().withName("Daniel Meier")
            .withPhone("87652533").withId(4).build();
    public static final Seller ELLE = new SellerBuilder().withName("Elle Meyer")
            .withPhone("9482224").withId(5).build();
    public static final Seller FIONA = new SellerBuilder().withName("Fiona Kunz")
            .withPhone("9482427").withId(6).build();
    public static final Seller GEORGE = new SellerBuilder()
            .withName("George Best").withPhone("9482442").withId(7).build();

    // Manually added - Seller's details found in {@code CommandTestUtil}
    public static final Seller AMY = new SellerBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withId(12).build();
    public static final Seller BOB = new SellerBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withId(13).build();

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
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
